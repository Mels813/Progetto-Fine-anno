package org.example;

import javax.naming.spi.ResolveResult;
import java.sql.*;
import java.util.ArrayList;

public class Restaurant extends User{
    private String city;
    private String address;

    Restaurant(String name, String mail, String pswrd,String phone,float saldo, String city, String address){
        super(name,mail,phone,pswrd,saldo);
        this.address = address;
        this.city = city;
    }

    String getName(){
        return super.getNome();
    }

    void setMoney(float saldo){
        super.setSaldo(saldo);
    }

    float getsaldo(){
        return super.getSaldo();
    }

    String getPhone(){
        return super.getTelefono();
    }

    String getCity(){
        return  city;
    }
    String getAddress(){
        return address;
    }
    void setCity(String city){
        this.city = city;
    }
    void setAddress(String address){
        this.address = address;
    }

    public int availableDatas(String url,String name,String pswrd){
        return super.availableDatas(url,name,pswrd,false);
    }
    public int checkPassword(String url, String name, String password){
        return super.checkPassword(url,name,password,false);
    }

    public int checkPhone(String url, String user, String password){
        String query = "SELECT COUNT(*) FROM RISTORANTE WHERE telephone = '"+getPhone()+"'";
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();

            if(set.next()){
                if(set.getInt(1)>0){
                    return 0;
                }
                return 1;
            }
            return 1;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public boolean addtoDB(String url, String user, String password){
        String query = "";
        int tmp = availableDatas(url,user,password);

        //elemento da aggiungere alla tabella
        if(tmp==1){
            query = "INSERT INTO RISTORANTE (name,password,mail,saldo,city,address,telephone) values (?,?,?,?,?,?,?)";
        }
        else if(tmp==0){
            //elemento da modificare nella tabella
            query = "UPDATE RISTORANTE SET name = ?, password = ?, mail = ?, saldo = ?, city = ?, address = ?, telephone = ? WHERE mail = '"+super.getEmail()+"'";
        }
        //errore nella chiamata al db
        else{
            return false;
        }

        //creazione richiesta
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1,super.getNome());
            statement.setString(2,super.getPassword());
            statement.setString(3,super.getEmail());
            statement.setFloat(4,super.getSaldo());
            statement.setString(5,city);
            statement.setString(6,address);
            statement.setString(7,super.getTelefono());

            //esecuzione query e chiusura
            statement.executeUpdate();
            connection.close();
            System.out.println("controlla il db");
            return true;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean getDatasFromDB(String url, String user, String password){
        String query = "SELECT * FROM RISTORANTE WHERE mail = '" + super.getEmail() + "'";

        //chiamata al DB e presa dei dati
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                //popolamento dell'oggetto
                super.setEmail(resultSet.getString("mail"));
                super.setPasword(resultSet.getString("password"));
                super.setSaldo(resultSet.getFloat("saldo"));
                super.setTelefono(resultSet.getString("telephone"));
                super.setNome(resultSet.getString("name"));
                city = resultSet.getString("city");
                address = resultSet.getString("address");

                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //controllo se un prodotto e' gia' presente nel listino del ristorante
    public int checkProduct(String url, String user, String password, Prodotto pd){
        String query = "SELECT COUNT(*) FROM PRODOTTO WHERE name = '"+
                pd.getNomeProd()+"' and restaurantPhone = '"+super.getTelefono()+"'";

        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement statement = connection.prepareStatement(query);
            //statement.setString(1,pd.getNomeProd());
            //statement.setString(2,pd.getRestaurant().getTelefono());
            ResultSet rs = statement.executeQuery();

            //controllo se c'e' un elemento nel db
            if(rs.next() && rs.getInt(1)>0){
                return 1;
            }
            return 0;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return -1;
        }
    }

    //aggiunta nuovo prodotto alla lista del ristorante
    public int addProduct(String url, String name, String pswrd, Prodotto pd){
        int tmp = checkProduct(url,name,pswrd,pd), result;
        String query;

        if(tmp==1){
            //aggiornamento del prodotto
            query = "UPDATE PRODOTTO " +
                    "SET name = ?, price = ?, descrizione = ?, categoria = ?, restaurantPhone = ? " +
                    "WHERE name = '"+pd.getNomeProd()+"'and restaurantPhone = '"+this.getPhone()+"'";
            result = 1;
        }
        else if(tmp==0){
            query = "INSERT INTO PRODOTTO (name,price,descrizione,categoria,restaurantPhone) values(?,?,?,?,?)";
            result = 0;
        }
        else{
            return -1;
        }
        try{
            //aggiunta o modifica al database
            Connection connection = DriverManager.getConnection(url,name,pswrd);
            PreparedStatement statement = connection.prepareStatement(query);

            //settaggio degli attributi ed esecuzione query
            statement.setString(1,pd.getNomeProd());
            statement.setFloat(2,pd.getPrezzo());
            statement.setString(3,pd.getDescrizione());
            statement.setString(4,pd.getCategoria());
            statement.setString(5,this.getPhone());
            statement.executeUpdate();
            return result;
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    //recapito dei prodotti del ristorante dal db
    public ArrayList<Prodotto>getProducts(String url, String user, String password){
        String query = "SELECT * FROM PRODOTTO WHERE restaurantPhone = '"+super.getTelefono()+"'";
        ArrayList<Prodotto>products = new ArrayList<>();
        int i = 0;

        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();

            while(set.next()){
                Prodotto tmp = new Prodotto("",0,"","",this);
                //popolamento dell'oggetto
                tmp.setPrezzo(set.getFloat("price"));
                tmp.setNomeProd(set.getString("name"));
                tmp.setDescrizione(set.getString("descrizione"));
                tmp.setCategoria(set.getString("categoria"));

                //aggiunta prodotto all'array
                products.add(tmp);
            }
            return products;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ArrayList<Consegna>getOrders(String url, String user, String password){
        ArrayList<Consegna> orders = new ArrayList<>();
        String query = "SELECT id FROM delivery WHERE restaurantMail = ?";

        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,getEmail());
            ResultSet set = statement.executeQuery();

            while(set.next()){
                int id = set.getInt("id");
                Consegna tmp = new Consegna("",0f,id);

                //recezione dettagli ordine
                String orderQuery = "SELECT name, quantity FROM ORDINI WHERE orderID = ?";
                PreparedStatement stmt = connection.prepareStatement(orderQuery);
                stmt.setInt(1,id);
                ResultSet resultSet = stmt.executeQuery();

                while(resultSet.next()){
                   //aggiunta dati alla consegna
                    Prodotto pd = new Prodotto(resultSet.getString("name"),0f,"","",null);
                    pd.setQuantity(resultSet.getInt("quantity"));

                    tmp.addProduct(pd);
                }
                //aggiunta consegna all'array
                orders.add(tmp);
            }
            if(orders.isEmpty()){
                return null;
            }
            return orders;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
