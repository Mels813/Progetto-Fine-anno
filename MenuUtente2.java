package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuUtente2 extends JPanel {
    private JPanel menuLaterale;
    private boolean menuVisibile = false;
    private JButton btnUtente = new JButton("☰");
    private JPanel pannelloContenuti = new JPanel(new BorderLayout());
    private boolean ristorantiVisibili = false;

    public MenuUtente2(JFrame frame, Cliente c, ArrayList<Restaurant> listaRistoranti) {
        setLayout(new BorderLayout());

        menuLaterale = new JPanel();
        menuLaterale.setLayout(new GridLayout(4, 1));
        menuLaterale.setBackground(Color.ORANGE);
        menuLaterale.setPreferredSize(new Dimension(200, 700));
        menuLaterale.setVisible(true);

        JButton btnProfilo = new JButton("👤 Area Personale");
        JButton btnCarrello = new JButton("🛒 Carrello");
        JButton btnConsegne = new JButton("🚚 Consegne");
        JButton btnRistoranti = new JButton("🍽️ Ristoranti");

        btnRistoranti.addActionListener(e -> {
            if (ristorantiVisibili) {
                tornaAlMenuIniziale();
            } else {
                mostraRistoranti(listaRistoranti);
            }
        });

        menuLaterale.add(btnProfilo);
        menuLaterale.add(btnCarrello);
        menuLaterale.add(btnConsegne);
        menuLaterale.add(btnRistoranti);

        JLabel benvenuto = new JLabel("Benvenuto nel menu!", SwingConstants.CENTER);
        benvenuto.setFont(new Font("Arial", Font.BOLD, 24));
        pannelloContenuti.add(benvenuto, BorderLayout.CENTER);

        btnUtente.addActionListener(e -> {
            menuVisibile = !menuVisibile;
            menuLaterale.setVisible(menuVisibile);
        });

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(btnUtente,BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        add(pannelloContenuti, BorderLayout.CENTER);
        add(menuLaterale, BorderLayout.EAST);

        frame.add(this);
    }

    public void mostraRistoranti(ArrayList<Restaurant> lista) {
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

        // Pannello per il menu
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());  // Usando BorderLayout per separare immagine e testo

        // Aggiungi immagine a sinistra
        JLabel immagineLabel = new JLabel(new ImageIcon("path_to_image.jpg"));
        menuPanel.add(immagineLabel, BorderLayout.WEST);  // Posizionata a sinistra

        // Pannello a destra per il testo e i pulsanti
        JPanel textAndButtonsPanel = new JPanel();
        textAndButtonsPanel.setLayout(new BoxLayout(textAndButtonsPanel, BoxLayout.Y_AXIS));  // Gestione verticale

        for (Prodotto prodotto : r.getMenu()) {
            JPanel prodottoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
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

            textAndButtonsPanel.add(prodottoPanel);
        }

        JScrollPane scrollPane = new JScrollPane(textAndButtonsPanel);
        menuPanel.add(scrollPane, BorderLayout.CENTER);  // Aggiungi a destra

        pannelloContenuti.add(menuPanel, BorderLayout.CENTER);

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

        Restaurant r1 = new Restaurant("Risto Roma", "", "", "", 0, "", "");
        r1.addProdotto(new Prodotto("Carbonara", 12.5, "Primo", "Piatto tipico romano", r1));
        r1.addProdotto(new Prodotto("Amatriciana", 12.5, "Primo", "Pasta con guanciale e pomodoro", r1));

        Restaurant r2 = new Restaurant("Pizza Milano", "", "", "", 0, "", "");
        r2.addProdotto(new Prodotto("Pizza Margherita", 8.0, "Pizza", "Pomodoro, mozzarella, basilico", r2));
        r2.addProdotto(new Prodotto("Pizza Diavola", 9.0, "Pizza", "Salame piccante, mozzarella", r2));

        lista.add(r1);
        lista.add(r2);

        Cliente c = new Cliente("Mario Rossi", "mario@mail.com", "password", "3330001111", 344, "address", "city");

        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new MenuUtente2(frame, c, lista);
        frame.setVisible(true);
    }

}
