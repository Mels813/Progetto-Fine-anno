/*
 * Classe per rappresentare un soggetto generale, utilizzatore dell'app
 */

package org.example;

import java.sql.*;

public class User{
    private String nome;
    private String email;
    private String telefono;
    private String password;
    private float saldo;

    public User(String nome, String email, String telefono, String password, float saldo){
        this.nome = nome;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
        this.saldo = saldo;
    }

    //getter
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getPassword() {
        return password;
    }
    public float getSaldo(){
        return saldo;
    }

    //setter
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setPasword(String password){
        this.password = password;
    }
    public void setSaldo(float saldo){
        this.saldo = saldo;
    }

    //controllo che mail e password siano corrette
    public int checkPassword(String url, String user, String password){
        String query = "SELECT COUNT(*) FROM CLIENTE WHERE mail = '"+email+"'";

        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();

            if(set.next()){
                System.out.println(set.getFloat("saldo"));
                return set.getInt(1);
            }
            return 0;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return -1;
        }
    }

    //controllo mail non ancora in uso
    public int availableDatas(String url, String name, String password){
        String query = "SELECT COUNT(*) FROM CLIENTE WHERE mail = '"+email+"'";
        ResultSet set = null;

        try{
            Connection connection = DriverManager.getConnection(url,name,password);
            PreparedStatement statement = connection.prepareStatement(query);
            set = statement.executeQuery();

           if(set.next() && set.getInt(1)==0){
               return 1;
           }
           return 0;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Override
    public String toString(){
        String str = "nome: "+nome+" password: "+password+" mail: "+email+" telefono: "+telefono+" saldo: "+saldo;
        return str;
    }
}