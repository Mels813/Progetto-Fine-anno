/*
 * rappresentazione di una delivery, con cliente, ristorate e rider coinvolti
 */
package org.example;

import java.sql.*;
import java.util.ArrayList;

public class Delivery{
    private Cliente client;
    private Rider rider;
    private Restaurant restaurant;
    private float cost;
    //lista di prodotti per la consegna
    private ArrayList<Prodotto>cart;

    Delivery(Cliente client){
        cart = new ArrayList<>();
        this.client = client;
        this.rider = null;
        this.restaurant = null;
        cost = 0f;
    }
    
    float getCost(){
        return cost;
    }

    void setCost(float cost){
        this.cost = cost;
    }

    void setRestaurantName(String name){
        this.restaurant.setNome(name);
    }

    Rider getRider(){
        return rider;
    }


    public boolean addProduct(Prodotto prodotto){
        if(cart.isEmpty()){
            //aggiunta ristorante
            prodotto.setQuantity(1);
            cart.add(prodotto);
            this.restaurant = prodotto.getRestaurant();
            return true;
        }
        else{
            if(prodotto.getRestaurant().getTelefono().equals(restaurant.getTelefono())){    //stesso numero di cellulare
                for(int i=0;i<cart.size();i++){
                    //se il prodotto e' gia' presente nel carrello
                    if(cart.get(i).getNomeProd().equals(prodotto.getNomeProd())){
                        cart.get(i).setQuantity(cart.get(i).getQuantity()+1);
                        return true;
                    }
                }
                //elemento da aggiungere al carrello
                prodotto.setQuantity(1);
                cart.add(prodotto);
                return true;   //prodotto aggiunto
            }
            return false;   //elemento da aggiungere presente in un altro ristorante
        }
    }

    public boolean checkMoney(){
        float total = sumCart();

        if(total > client.getMoney()){
            return false;
        }
        client.setMoney(client.getMoney()-total);
        return true;
    }

    public int getProducts(){
        return cart.size();
    }

    public void removeProduct(Prodotto prodotto){
        //ciclo per trovare il prodotto da rimuovere
        for (int i = 0; i < cart.size(); i++){
            if (cart.get(i).getNomeProd().equals(prodotto.getNomeProd())){
                cart.get(i).setQuantity(cart.get(i).getQuantity() - 1);
                if (cart.get(i).getQuantity() == 0) {
                    cart.remove(cart.get(i));
                }
                break;
            }
        }
    }

    public String getRestaurantName(){
        return restaurant.getName();
    }

    //conto totale
    public float sumCart(){
        int i;
        float sum = 0f;

        for(i=0;i<cart.size();i++){
            sum += cart.get(i).getPrezzo()*cart.get(i).getQuantity();
        }
        cost = sum;
        return sum;
    }

    //suddivisione soldi tra rider e ristorante
    public void payService(String url, String user, String password){
        float total = sumCart();
        client.setSaldo(client.getSaldo()-total);

        //20% commissione al rider
        rider.setSaldo(rider.getSaldo()+(total*0.2f));
        restaurant.setMoney(restaurant.getsaldo()+(total*0.8f));

        //modifiche sulle rispettive tabelle, con cambio dei dati
        rider.addtoDB(url,user,password);
        restaurant.addtoDB(url,user,password);
        client.addtoDB(url,user,password);
    }

    //scelta del rider dal DB
    public int findRider(String url, String user, String password){
        String query = "SELECT * FROM RIDER WHERE state = true LIMIT 1";    //selezione del primo rider disponibile

        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();

            if(set.next()){
                //popolamento rider con i suoi attributi
                rider = new Rider(set.getString("name"),set.getString("mail"),set.getString("phone"),set.getString("password"),set.getFloat("saldo"));

                //settaggio attributo free->false
                rider.setFree(false);

                //modifica nel db
                rider.addtoDB(url,user,password);
                return 1;
            }
            return 0;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public boolean addDeliverytoDB(String url, String user, String pswrd){
        String query = "INSERT INTO DELIVERY (cost,customerAddress,customerCity,restaurantCity,restaurantAddress,riderMail,customerMail,restaurantName)" +
                "values (?,?,?,?,?,?,?,?)";

        try{
            Connection connection = DriverManager.getConnection(url,user,pswrd);
            PreparedStatement statement = connection.prepareStatement(query);

            //preparazione dei dati per l'esecuzione
            statement.setFloat(1,sumCart());
            statement.setString(2,client.getAddress());
            statement.setString(3,client.getCity());
            statement.setString(4,restaurant.getCity());
            statement.setString(5,restaurant.getAddress());
            statement.setString(6,rider.getEmail());
            statement.setString(7,client.getMail());
            statement.setString(8,restaurant.getName());
            statement.executeUpdate();
            return true;

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    //cambio stato della consegna
    public int changeState(String url, String user, String password, int state){
        String query = "UPDATE DELIVERY SET state = ? WHERE riderMail = '"+rider.getEmail()+"'";
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,state);
            ResultSet set = statement.executeQuery();

            if(set.next()){
                return 1;
            }
            return 0;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return -1;
        }
    }
}
