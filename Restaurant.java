package org.example;

import java.sql.*;
import java.util.ArrayList;

public class Restaurant extends Cliente{

    Restaurant(String name, String mail, String pswrd,String phone,float saldo, String city, String address){
        super(name,mail,phone,pswrd,saldo,city,address);
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

    public int availableDatas(String url,String name,String pswrd){
        return super.availableMail(url,name,pswrd);
    }
    public int checkPassword(String url, String name, String password){
        return super.rightPassword(url,name,password);
    }

    public void insertDB(String url, String user, String password){
        super.addtoDB(url,user,password);
    }
    public void getDatas(String url, String user, String password){
        super.getDatasFromDB(url,user,password);
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
}
