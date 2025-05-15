package org.example;

import javax.swing.*;
import java.awt.*;

public class Main2 {
    public static void main(String[] args) {
        // Dati di esempio
        String nome = "PizzaExpress";
        String mail = "info@pizzaexpress.com";
        String password = "1234";
        String telefono = "3331234567";
        float saldo = 100.0f;
        String city = "Milano";
        String address = "Via Roma 10";

        // Connessione finta (da cambiare con connessione reale)
        String url = "jdbc:mysql://localhost:3306/tuoDB";
        String user = "root";
        String dbPassword = "root";

        // Creazione oggetto Restaurant
        Restaurant ristorante = new Restaurant(nome, mail, password, telefono, saldo, city, address);

        // Finestra principale
        JFrame frame = new JFrame("Profilo Ristorante");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 800);
        frame.setLayout(new GridLayout(0, 1));

        // Pannello principale
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.white);

        // Aggiunta di labelRestaurant per vari campi
        mainPanel.add(new labelRestaurant("nome", nome, mainPanel, ristorante, url, user, dbPassword));
        mainPanel.add(new labelRestaurant("mail", mail, mainPanel, ristorante, url, user, dbPassword));
        mainPanel.add(new labelRestaurant("telefono", telefono, mainPanel, ristorante, url, user, dbPassword));
        mainPanel.add(new labelRestaurant("password", password, mainPanel, ristorante, url, user, dbPassword));
        mainPanel.add(new labelRestaurant("saldo", Float.toString(saldo), mainPanel, ristorante, url, user, dbPassword));
        mainPanel.add(new labelRestaurant("città", city, mainPanel, ristorante, url, user, dbPassword));
        mainPanel.add(new labelRestaurant("indirizzo", address, mainPanel, ristorante, url, user, dbPassword));

        frame.add(mainPanel);

        frame.setVisible(true);

    }
}

