/*
 * Classe per rappresentare cliente consumatore
 */

package org.example;
import java.sql.*;
import java.util.ArrayList;

public class Cliente extends User{
   private String address;
   private String city;

    Cliente(String name, String email, String phone, String pswrd, float saldo, String address, String city){
        super(name,email,phone,pswrd,saldo);
        this.address = address;
        this.city = city;
    }

    String getAddress(){
        return address;
    }

    String getName(){
        return super.getNome();
    }

    String getMail(){
        return super.getEmail();
    }

    String getCell(){
        return super.getTelefono();
    }

    float getMoney(){
        return super.getSaldo();
    }

    String getCity(){
        return city;
    }

    String getpassword(){
        return super.getPassword();
    }

    void setMoney(float money){
        super.setSaldo(money);
    }

    //controllo credenziali corrette per l'accesso
   public int rightPassword(String url, String user, String password){
        return super.checkPassword(url,user,password);
   }

   //controllo che la mail non sia' gia' in utilizzo
    public int availableMail(String url, String name, String password){
        return super.availableDatas(url,name,password);
    }

    //modifica dei dati del cliente oppure aggiunta di uno nuovo
   public boolean addtoDB(String url, String user, String password){
        String query = "";
        int tmp = availableMail(url,user,password);

        //elemento da aggiungere alla tabella
        if(tmp==1){
            query = "INSERT INTO CLIENTE (name,password,mail,saldo,city,address,telephone) values (?,?,?,?,?,?,?)";
        }
        else if(tmp==0){
            //elemento da modificare nella tabella
            query = "UPDATE CLIENTE SET name = ?, password = ?, mail = ?, saldo = ?, city = ?, address = ?, telephone = ? WHERE mail = '"+super.getEmail()+"'";
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

   //recezione dei dati dal database
   public boolean getDatasFromDB(String url, String user, String password){
        String query = "SELECT * FROM CLIENTE WHERE mail = '"+super.getEmail()+"'";

        //chiamata al DB e presa dei dati
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                //popolamento dell'oggetto
                super.setEmail(resultSet.getString("mail"));
                super.setPasword(resultSet.getString("password"));
                super.setSaldo(resultSet.getFloat("saldo"));
                super.setTelefono(resultSet.getString("telephone"));
                super.setNome(resultSet.getString("name"));
                city = resultSet.getString("city");
                address =resultSet.getString("address");

                return true;
            }
            return false;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
   }

   //recezione di tutte le delivery del cliente
   public ArrayList<Delivery> getDeliveries(String url, String user, String password){
        String query = "SELECT * FROM DELIVERY WHERE customerMail = '"+super.getEmail()+"'";
        ArrayList<Delivery>deliveries = new ArrayList<>();

        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();

            //restituzione array di consegne
            if(set.next()){
                while(set.next()){
                    //creazione nuova consegna
                    Delivery tmp = new Delivery(this);

                    //settaggio costo, e ristorante
                    tmp.setRestaurantName(set.getString("restaurantName"));
                    tmp.setCost(set.getFloat("cost"));

                    //aggiunta della delivery all'array
                    deliveries.add(tmp);
                }
                //restituzione pending delivery del cliente
                return deliveries;
            }
            return null;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
   }
}
