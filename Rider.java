/*
 * Classe utente per le consegne
 */

package org.example;

import java.sql.*;
import java.util.ArrayList;

public class Rider extends User{
    private boolean free;   //in consegna oppure libero

    Rider(String name, String mail, String phone, String pswrd, float saldo) {
        super(name, mail, phone, pswrd, saldo);
        this.free = true;
    }

    void setMail(String mail) {
        super.setEmail(mail);
    }

    boolean getFree() {
        return free;
    }

    void setFree(boolean free) {
        this.free = free;
    }

    public void goToDestination(String addressString) {
        //integrazione google maps per raggiungere la destinazione
    }

    public int checkMail(String url, String user, String pswrd) {
        String query = "SELECT COUNT(*) FROM RIDER WHERE mail = '" + super.getEmail() + "'";
        try {
            Connection connection = DriverManager.getConnection(url, user, pswrd);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();

            if (set.next() && set.getInt(1) > 0) {  //mail gia' in utilizzo
                return 0;
            }
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public int checkPassword(String url, String user, String password) {
        String query = "SELECT password FROM RIDER WHERE mail = ?";

        if (checkMail(url, user, password) == 0) {
            //utente esistente nel db
            try {
                Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, super.getEmail());
                ResultSet set = statement.executeQuery();

                if (set.next() && set.getString("password").equals(this.getPassword())) {
                    return 1;
                }
                return 2;   //password non corretta
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return -1;  //errore comunicazione
            }
        }
        return 0;   //utente inesistente nel db
    }

    //aggiunta o modifica rider nel database
    public int addtoDB(String url, String user, String password) {
        int tmp = checkMail(url, user, password), result;
        String query;

        if (tmp == 0) {
            //utente gia' presente nel db, aggiornamento dei dati
            query = "UPDATE RIDER SET name = ?, mail = ?, password = ?, saldo = ?, state = ?, phone = ? WHERE mail = '" + super.getEmail() + "'";
            result = 0;
        } else if (tmp == 1) {
            //utente da aggiungere al db
            query = "INSERT INTO RIDER (name,mail,password,saldo,state,phone) values(?,?,?,?,?,?)";
            result = 1;
        } else {
            return -1;
        }
        //aggiornamento db
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(query);

            //preparazione dati
            statement.setString(1, super.getNome());
            statement.setString(2, super.getEmail());
            statement.setString(3, super.getPassword());
            statement.setFloat(4, super.getSaldo());
            statement.setBoolean(5, free);
            statement.setString(6, super.getTelefono());
            statement.executeUpdate();
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public boolean getDatasFromDB(String url, String user, String password) {
        String query = "SELECT name,mail,password,saldo,state,phone FROM RIDER WHERE mail = '" + super.getEmail() + "'";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                super.setNome(set.getString("name"));
                super.setEmail(set.getString("mail"));
                super.setPasword(set.getString("password"));
                super.setSaldo(set.getFloat("saldo"));
                free = set.getBoolean("state");
                super.setTelefono(set.getString("phone"));
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<Delivery> getDeliveryFromDB(String url, String user, String password) {
        //ogni rider ha solo una consegna per volta
        String query = "SELECT COUNT(*) FROM DELIVERY WHERE riderMail = '" + super.getEmail() + "' LIMIT 1";
        try{
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                while (set.next()) {
                    String tmp = set.getString("email");
                }
            }
            return null;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int deleteDelivery(String url, String user, String password){
        String query = "DELETE FROM DELIVERY WHERE riderMail = '"+super.getEmail()+"'";

        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();

            //cambio dello stato del rider
            free = true;
            this.addtoDB(url,user,password);
            return 1;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return -1;
        }
    }
}