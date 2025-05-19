package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RestaurantGUI extends JPanel {
    private JPanel menuLaterale;
    private boolean menuVisibile = false;
    private JButton btnRistorante = new JButton("☰");
    private JPanel pannelloContenuti = new JPanel(new CardLayout());
    private boolean prodottiVisibili = false;
    private Restaurant restaurant;
    private Delivery delivery;

    public RestaurantGUI(JFrame frame, Restaurant ristorante, Delivery delivery) {
        this.restaurant = ristorante;
        this.delivery = delivery;
        setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255));

        // Menu laterale
        menuLaterale = new JPanel();
        menuLaterale.setLayout(new GridLayout(3, 1)); // 3 voci nel menu laterale
        menuLaterale.setPreferredSize(new Dimension(200, 700));
        menuLaterale.setBackground(new Color(44, 62, 80)); // Colore scuro per il menu laterale
        menuLaterale.setVisible(true);

        // Pulsanti del menu con icone
        JButton btnAccount = new JButton("👤 Area Personale");
        JButton btnOrdine = new JButton("📦 Ordini");
        JButton btnProdotti = new JButton("🍽️ Prodotti");

        // Personalizzazione dei pulsanti
        customizeButton(btnAccount);
        customizeButton(btnOrdine);
        customizeButton(btnProdotti);

        // Azioni dei pulsanti
        btnProdotti.addActionListener(e -> mostraProdotti());
        btnOrdine.addActionListener(e -> mostraOrdini());
        btnAccount.addActionListener(e -> mostraAccount());

        // Aggiungi i pulsanti al menu
        menuLaterale.add(btnAccount);
        menuLaterale.add(btnOrdine);
        menuLaterale.add(btnProdotti);

        // Messaggio di benvenuto
        JLabel benvenuto = new JLabel("Benvenuto nel ristorante!", SwingConstants.CENTER);
        benvenuto.setFont(new Font("Arial", Font.BOLD, 24));
        benvenuto.setForeground(new Color(255,136,0)); // Colore arancione
        JPanel benvenutoPanel = new JPanel();
        benvenutoPanel.add(benvenuto);
        pannelloContenuti.add(benvenutoPanel, "Home");

        // Pulsante ☰ per aprire/chiudere il menu laterale
        btnRistorante.addActionListener(e -> {
            menuVisibile = !menuVisibile;
            menuLaterale.setVisible(menuVisibile);
        });

        // Pannello in alto
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(255,136,0)); // Colore scuro per il top
        topPanel.add(btnRistorante);
        add(topPanel, BorderLayout.NORTH);

        // Aggiunta componenti principali
        add(pannelloContenuti, BorderLayout.CENTER);
        add(menuLaterale, BorderLayout.EAST);

        frame.add(this);
    }

    private void customizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(255,136,0)); // Blu chiaro
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(180, 50));
        button.setOpaque(true);
    }

    public void mostraAccount() {
        JPanel accountPanel = new JPanel();
        accountPanel.setLayout(new BoxLayout(accountPanel, BoxLayout.Y_AXIS));
        accountPanel.setBackground(new Color(236, 240, 241)); // Colore di sfondo per la sezione

        JLabel nomeLabel = new JLabel("Nome Ristorante: " + restaurant.getName());
        nomeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        accountPanel.add(nomeLabel);

        JButton modificaNome = new JButton("Modifica Nome");
        modificaNome.setFont(new Font("Arial", Font.PLAIN, 16));
        modificaNome.addActionListener(e -> {
            String nuovoNome = JOptionPane.showInputDialog(null, "Modifica Nome Ristorante", restaurant.getName());
            if (nuovoNome != null && !nuovoNome.isEmpty()) {
                restaurant.getName();
                nomeLabel.setText("Nome Ristorante: " + nuovoNome);
            }
        });
        accountPanel.add(modificaNome);

        JLabel indirizzoLabel = new JLabel("Indirizzo: " + restaurant.getAddress());
        indirizzoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        accountPanel.add(indirizzoLabel);

        JButton modificaIndirizzo = new JButton("Modifica Indirizzo");
        modificaIndirizzo.setFont(new Font("Arial", Font.PLAIN, 16));
        modificaIndirizzo.addActionListener(e -> {
            String nuovoIndirizzo = JOptionPane.showInputDialog(null, "Modifica Indirizzo Ristorante", restaurant.getAddress());
            if (nuovoIndirizzo != null && !nuovoIndirizzo.isEmpty()) {
                restaurant.setAddress(nuovoIndirizzo);
                indirizzoLabel.setText("Indirizzo: " + nuovoIndirizzo);
            }
        });
        accountPanel.add(modificaIndirizzo);

        pannelloContenuti.add(accountPanel, "Account");
        switchPanel("Account");
    }

    public void mostraProdotti() {
        JPanel prodottiPanel = new JPanel();
        prodottiPanel.setLayout(new BoxLayout(prodottiPanel, BoxLayout.Y_AXIS));
        prodottiPanel.setBackground(new Color(236, 240, 241)); // Colore di sfondo per la sezione

        // Aggiungi il pulsante "Aggiungi Prodotto"
        JButton aggiungiProdotto = new JButton("Aggiungi Prodotto");
        aggiungiProdotto.setFont(new Font("Arial", Font.PLAIN, 16));
        aggiungiProdotto.addActionListener(e -> aggiungiProdotto());
        prodottiPanel.add(aggiungiProdotto);

        for (Prodotto prodotto : restaurant.getMenu()) {
            JPanel prodottoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            prodottoPanel.setBackground(new Color(236, 240, 241)); // Colore di sfondo per ogni prodotto

            JLabel nomeProdotto = new JLabel(prodotto.getNomeProd() + " - " + prodotto.getPrezzo() + "€");
            nomeProdotto.setFont(new Font("Arial", Font.PLAIN, 16));
            prodottoPanel.add(nomeProdotto);

            JButton modificaProdotto = new JButton("Modifica");
            modificaProdotto.setFont(new Font("Arial", Font.PLAIN, 14));
            modificaProdotto.addActionListener(e -> {
                String nuovoNome = JOptionPane.showInputDialog(null, "Modifica Nome Prodotto", prodotto.getNomeProd());
                if (nuovoNome != null && !nuovoNome.isEmpty()) {
                    prodotto.setNomeProd(nuovoNome);
                    nomeProdotto.setText(nuovoNome + " - " + prodotto.getPrezzo() + "€");
                }
            });
            prodottoPanel.add(modificaProdotto);

            JButton eliminaProdotto = new JButton("Elimina");
            eliminaProdotto.setFont(new Font("Arial", Font.PLAIN, 14));
            eliminaProdotto.addActionListener(e -> {
                restaurant.getMenu().remove(prodotto);
                mostraProdotti(); // Rende visibile l'aggiornamento
            });
            prodottoPanel.add(eliminaProdotto);

            prodottiPanel.add(prodottoPanel);
        }

        pannelloContenuti.add(prodottiPanel, "Prodotti");
        switchPanel("Prodotti");
    }

    // Nuovo metodo per aggiungere un prodotto
    private void aggiungiProdotto() {
        // Finestra di input per i dettagli del nuovo prodotto
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));

        JTextField nomeField = new JTextField();
        JTextField prezzoField = new JTextField();
        JTextField descrizioneField = new JTextField();

        inputPanel.add(new JLabel("Nome Prodotto:"));
        inputPanel.add(nomeField);
        inputPanel.add(new JLabel("Prezzo (€):"));
        inputPanel.add(prezzoField);
        inputPanel.add(new JLabel("Descrizione:"));
        inputPanel.add(descrizioneField);

        int option = JOptionPane.showConfirmDialog(null, inputPanel, "Aggiungi Prodotto", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText();
            double prezzo = 0;
            try {
                prezzo = Double.parseDouble(prezzoField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Prezzo non valido!", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String descrizione = descrizioneField.getText();

            // Aggiungi il nuovo prodotto al menu
            Prodotto nuovoProdotto = new Prodotto(nome, prezzo, "Categoria", descrizione, restaurant);
            restaurant.addProdotto(nuovoProdotto);

            mostraProdotti(); // Rende visibile l'aggiornamento
        }
    }


    public void mostraOrdini() {
        JPanel ordiniPanel = new JPanel();
        ordiniPanel.setLayout(new BoxLayout(ordiniPanel, BoxLayout.Y_AXIS));
        ordiniPanel.setBackground(new Color(236, 240, 241)); // Colore di sfondo per la sezione

        ArrayList<Consegna> ordini = delivery.getConsegne();
        if (ordini.isEmpty()) {
            JLabel noOrdersLabel = new JLabel("Nessun ordine effettuato.");
            ordiniPanel.add(noOrdersLabel);
        } else {
            for (Consegna ordine : ordini) {
                JLabel ordineLabel = new JLabel("Ordine da: " + ordine.getId() + " - Totale: " + ordine.getCost() + "€");
                ordineLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                ordiniPanel.add(ordineLabel);
            }
        }

        pannelloContenuti.add(ordiniPanel, "Ordini");
        switchPanel("Ordini");
    }

    private void switchPanel(String cardName) {
        CardLayout cardLayout = (CardLayout) pannelloContenuti.getLayout();
        cardLayout.show(pannelloContenuti, cardName);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ArrayList<Prodotto> menu = new ArrayList<>();

        // Aggiungi alcuni prodotti di esempio al menu
        Restaurant ristorante = new Restaurant("Pizza Napoli", "Via Roma 12", "napoli@mail.com", "napoli123", 0, "", "");
        ristorante.addProdotto(new Prodotto("Margherita", 8.5, "Pizza", "Pomodoro e mozzarella", ristorante));
        ristorante.addProdotto(new Prodotto("Diavola", 9.0, "Pizza", "Salame piccante", ristorante));
        Delivery delivery = new Delivery(ristorante);

        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new RestaurantGUI(frame, ristorante, delivery);
        frame.setVisible(true);
    }
}
