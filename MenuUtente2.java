package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



public class MenuUtente extends JPanel {
    private JPanel menuLaterale;
    private boolean menuVisibile = false;
    private JButton btnUtente = new JButton("☰"); // Aggiungiamo un'icona o testo visibile
    private JPanel pannelloContenuti = new JPanel(new BorderLayout());
    private boolean ristorantiVisibili = false;



    public MenuUtente(JFrame frame, Cliente c) {
        setLayout(new BorderLayout());

        // Impostiamo il layout del menu laterale
        menuLaterale = new JPanel();
        menuLaterale.setLayout(new GridLayout(4, 1));
        menuLaterale.setBackground(Color.ORANGE);
        menuLaterale.setPreferredSize(new Dimension(200, 700));
        menuLaterale.setVisible(true);

        JButton btnProfilo = new JButton("👤 Area Personale");
        JButton btnCarrello = new JButton("🛒 Carrello");
        JButton btnConsegne = new JButton("🚚 Consegne");
        JButton btnRistoranti = new JButton("🍽️ Ristoranti");

        // Aggiungiamo l'azione per il pulsante "Ristoranti"
        btnRistoranti.addActionListener(e -> {
            ArrayList<Restaurant>restaurants = new ArrayList<>();
            if (ristorantiVisibili) {
                System.out.println("Tornando al menu iniziale...");
                tornaAlMenuIniziale();
            } else {
                System.out.println("Mostrando i ristoranti...");
                mostraRistoranti(c.getRestaurant());
            }
        });

        menuLaterale.add(btnProfilo);
        menuLaterale.add(btnCarrello);
        menuLaterale.add(btnConsegne);
        menuLaterale.add(btnRistoranti);

        // Pannello dei contenuti centrali
        JLabel benvenuto = new JLabel("Benvenuto nel menu!", SwingConstants.CENTER);
        benvenuto.setFont(new Font("Arial", Font.BOLD, 24));
        pannelloContenuti.add(benvenuto, BorderLayout.CENTER);

        // Aggiungiamo un'azione al pulsante "Utente" per visualizzare il menu laterale
        btnUtente.addActionListener(e -> {
            menuVisibile = !menuVisibile;
            menuLaterale.setVisible(menuVisibile);
            System.out.println("Menu visibile: " + menuVisibile); // Debug per capire se il menu laterale viene visualizzato
        });

        // Aggiungiamo il pulsante "Utente" sopra il contenuto
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(btnUtente);
        add(topPanel, BorderLayout.NORTH);

        add(pannelloContenuti, BorderLayout.CENTER);
        add(menuLaterale, BorderLayout.EAST);

        frame.add(this);
    }

    public void mostraRistoranti(ArrayList<Restaurant> lista) {
        System.out.println("Mostrando la lista dei ristoranti...");

        pannelloContenuti.removeAll();


        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        centro.setBackground(Color.WHITE);

        for (Restaurant r : lista) {
            JPanel restaurantPanel = new JPanel();
            restaurantPanel.setLayout(new BoxLayout(restaurantPanel, BoxLayout.Y_AXIS));
            restaurantPanel.setBackground(new Color(230, 230, 230));
            restaurantPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel nome = new JLabel(r.getName());
            nome.setFont(new Font("Arial", Font.BOLD, 16));
            nome.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton menuButton = new JButton("Menu");
            menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            menuButton.addActionListener(e -> mostraMenuRistorante(r));

            restaurantPanel.add(nome);
            restaurantPanel.add(Box.createVerticalStrut(5));
            restaurantPanel.add(menuButton);

            centro.add(restaurantPanel);
            centro.add(Box.createVerticalStrut(20));
        }

        JScrollPane scrollPane = new JScrollPane(centro);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(700, 500));

        pannelloContenuti.add(scrollPane, BorderLayout.CENTER);
        pannelloContenuti.revalidate();
        pannelloContenuti.repaint();
        ristorantiVisibili = true;
    }

    private void mostraMenuRistorante(Restaurant r) {
        pannelloContenuti.removeAll();

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        for (Prodotto prodotto : r.getMenu()) {
            JPanel prodottoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel nomeProdotto = new JLabel(prodotto.getNomeProd() + " - " + prodotto.getPrezzo() + "€");

            JButton btnDettagli = new JButton("Dettagli");
            btnDettagli.addActionListener(e -> {
                JOptionPane.showMessageDialog(this,
                        "Categoria: " + prodotto.getCategoria() +
                                "\nDescrizione: " + prodotto.getDescrizione(),
                        "Dettagli Prodotto",
                        JOptionPane.INFORMATION_MESSAGE);
            });

            JButton btnAggiungi = new JButton("+");
            JButton btnRimuovi = new JButton("-");
            JLabel quantitàLabel = new JLabel("Quantità: " + prodotto.getQuantity());

            btnAggiungi.addActionListener(e -> {
                prodotto.setQuantity(prodotto.getQuantity() + 1);
                quantitàLabel.setText("Quantità: " + prodotto.getQuantity());
            });

            btnRimuovi.addActionListener(e -> {
                if (prodotto.getQuantity() > 0) {
                    prodotto.setQuantity(prodotto.getQuantity() - 1);
                    quantitàLabel.setText("Quantità: " + prodotto.getQuantity());
                }
            });

            prodottoPanel.add(nomeProdotto);
            prodottoPanel.add(btnDettagli);
            prodottoPanel.add(btnAggiungi);
            prodottoPanel.add(btnRimuovi);
            prodottoPanel.add(quantitàLabel);

            menuPanel.add(prodottoPanel);
        }

        JScrollPane scrollPane = new JScrollPane(menuPanel);
        pannelloContenuti.add(scrollPane, BorderLayout.CENTER);

        // Assicurarsi che il pannello si aggiorni
        pannelloContenuti.revalidate();
        pannelloContenuti.repaint();
    }


    private void tornaAlMenuIniziale() {
        pannelloContenuti.removeAll();
        JLabel benvenuto = new JLabel("Benvenuto nel menu!", SwingConstants.CENTER);
        benvenuto.setFont(new Font("Arial", Font.BOLD, 24));
        pannelloContenuti.add(benvenuto, BorderLayout.CENTER);
        pannelloContenuti.revalidate();
        pannelloContenuti.repaint();
        ristorantiVisibili = false;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ArrayList<Restaurant> lista = new ArrayList<>();
        lista.add(new Restaurant("Risto Roma", "roma@mail.com", "pass1", "3331112222", 0, "Roma", "Via A"));
        lista.add(new Restaurant("Pizza Milano", "milano@mail.com", "pass2", "3333334444", 0, "Milano", "Via B"));
        lista.add(new Restaurant("La Trattoria", "trattoria@mail.com", "pass3", "3335556666", 0, "Napoli", "Via C"));
        lista.add(new Restaurant("Sushi Giapponese", "sushi@mail.com", "pass4", "3337778888", 0, "Torino", "Via D"));
        lista.add(new Restaurant("Pasta e Basta", "pastabasta@mail.com", "pass5", "3339990000", 0, "Firenze", "Via E"));
        lista.add(new Restaurant("Burger House", "burgerhouse@mail.com", "pass6", "3332224444", 0, "Genova", "Via F"));
        lista.add(new Restaurant("Vino e Cucina", "vinoecucina@mail.com", "pass7", "3338887777", 0, "Bologna", "Via G"));
        lista.add(new Restaurant("Trattoria al Pescatore", "pescatore@mail.com", "pass8", "3334445555", 0, "Venezia", "Via H"));
        lista.add(new Restaurant("Osteria dei Sapori", "osteriasapori@mail.com", "pass9", "3336667777", 0, "Verona", "Via I"));
        lista.add(new Restaurant("Ristorante Da Luigi", "luigi@mail.com", "pass10", "3332223333", 0, "Bari", "Via L"));
        lista.add(new Restaurant("Pizzeria Bella Napoli", "bellanapoli@mail.com", "pass11", "3334446666", 0, "Salerno", "Via M"));
        lista.add(new Restaurant("Cucina Regionale", "regionalecucina@mail.com", "pass12", "3337775555", 0, "Palermo", "Via N"));
        lista.add(new Restaurant("Ristorante Le Delizie", "delizie@mail.com", "pass13", "3331114444", 0, "Catania", "Via O"));
        lista.add(new Restaurant("Osteria Vecchia", "vecchiaosteria@mail.com", "pass14", "3335558888", 0, "Pescara", "Via P"));
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new MenuUtente(frame,c);
        frame.setVisible(true);
    }
}
