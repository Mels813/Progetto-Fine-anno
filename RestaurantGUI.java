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
        btnProdotti.addActionListener(e -> mostraProdotti(restaurant.getMenu()));
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
        JPanel accountPanel=new JPanel();
        accountPanel.setLayout(new BoxLayout(accountPanel,BoxLayout.Y_AXIS));
        accountPanel.setBackground(new Color(236,240,241));

        //nome ristorante
        JPanel nomePanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel nomeLabel=new JLabel("Nome Ristorante: "+restaurant.getName());
        nomeLabel.setFont(new Font("Arial",Font.PLAIN,18));
        JButton modificaNome=new JButton("Modifica");
        modificaNome.setBackground(new Color(255,165,0));
        modificaNome.setForeground(Color.white);
        modificaNome.setFocusPainted(false);
        modificaNome.addActionListener(e->{
            String nuovoNome=JOptionPane.showInputDialog(null,"Modifica Nome Ristorante",restaurant.getName());
            if(nuovoNome!=null&&!nuovoNome.isEmpty()){
                restaurant.setName(nuovoNome);
                nomeLabel.setText("Nome Ristorante: "+nuovoNome);
            }
        });
        nomePanel.add(nomeLabel);
        nomePanel.add(modificaNome);
        accountPanel.add(nomePanel);

        //indirizzo
        JPanel indirizzoPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel indirizzoLabel=new JLabel("Indirizzo: "+restaurant.getAddress());
        indirizzoLabel.setFont(new Font("Arial",Font.PLAIN,18));
        JButton modificaIndirizzo=new JButton("Modifica");
        modificaIndirizzo.setBackground(new Color(255,165,0));
        modificaIndirizzo.setForeground(Color.white);
        modificaIndirizzo.setFocusPainted(false);
        modificaIndirizzo.addActionListener(e->{
            String nuovo=JOptionPane.showInputDialog(null,"Modifica Indirizzo",restaurant.getAddress());
            if(nuovo!=null&&!nuovo.isEmpty()){
                restaurant.setAddress(nuovo);
                indirizzoLabel.setText("Indirizzo: "+nuovo);
            }
        });
        indirizzoPanel.add(indirizzoLabel);
        indirizzoPanel.add(modificaIndirizzo);
        accountPanel.add(indirizzoPanel);

        //email
        JPanel emailPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel emailLabel=new JLabel("Email: "+restaurant.getEmail());
        emailLabel.setFont(new Font("Arial",Font.PLAIN,18));
        JButton modificaEmail=new JButton("Modifica");
        modificaEmail.setBackground(new Color(255,165,0));
        modificaEmail.setForeground(Color.white);
        modificaEmail.setFocusPainted(false);
        modificaEmail.addActionListener(e->{
            String nuova=JOptionPane.showInputDialog(null,"Modifica Email",restaurant.getEmail());
            if(nuova!=null&&!nuova.isEmpty()){
                restaurant.setEmail(nuova);
                emailLabel.setText("Email: "+nuova);
            }
        });
        emailPanel.add(emailLabel);
        emailPanel.add(modificaEmail);
        accountPanel.add(emailPanel);

        //password
        JPanel passwordPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel passwordLabel=new JLabel("Password: ********");
        passwordLabel.setFont(new Font("Arial",Font.PLAIN,18));
        JButton modificaPassword=new JButton("Modifica");
        modificaPassword.setBackground(new Color(255,165,0));
        modificaPassword.setForeground(Color.white);
        modificaPassword.setFocusPainted(false);
        modificaPassword.addActionListener(e->{
            String nuova=JOptionPane.showInputDialog(null,"Modifica Password","********");
            if(nuova!=null&&!nuova.isEmpty()){
                restaurant.setPassword(nuova);
            }
        });
        passwordPanel.add(passwordLabel);
        passwordPanel.add(modificaPassword);
        accountPanel.add(passwordPanel);

        pannelloContenuti.add(accountPanel,"Account");
        switchPanel("Account");
    }

    public void mostraProdotti(ArrayList<Prodotto> prodotti) {
        pannelloContenuti.removeAll();

        JPanel prodottiPanel = new JPanel();
        prodottiPanel.setLayout(new BoxLayout(prodottiPanel, BoxLayout.Y_AXIS));
        prodottiPanel.setBackground(new Color(236, 240, 241));

        // Pulsante aggiungi prodotto
        JButton aggiungiProdotto = new JButton("Aggiungi Prodotto");
        aggiungiProdotto.setFont(new Font("Arial", Font.PLAIN, 16));
        aggiungiProdotto.addActionListener(e -> aggiungiProdotto(prodotti));
        prodottiPanel.add(aggiungiProdotto);

        for (Prodotto prodotto : prodotti) {
            JPanel prodottoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            prodottoPanel.setBackground(new Color(236, 240, 241));

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
                prodotti.remove(prodotto);
                mostraProdotti(prodotti); // aggiorna la lista
            });
            prodottoPanel.add(eliminaProdotto);

            prodottiPanel.add(prodottoPanel);
        }

        pannelloContenuti.add(prodottiPanel, "Prodotti");
        switchPanel("Prodotti");
        pannelloContenuti.revalidate();
        pannelloContenuti.repaint();
    }

    private void aggiungiProdotto(ArrayList<Prodotto> prodotti) {
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
            double prezzo;
            try {
                prezzo = Double.parseDouble(prezzoField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Prezzo non valido!", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String descrizione = descrizioneField.getText();

            Prodotto nuovoProdotto = new Prodotto(nome, prezzo, "Categoria", descrizione, restaurant);
            prodotti.add(nuovoProdotto);

            mostraProdotti(prodotti);
        }
    }


    public void mostraOrdini() {
        JPanel ordiniPanel = new JPanel();
        ordiniPanel.setLayout(new BoxLayout(ordiniPanel, BoxLayout.Y_AXIS));
        ordiniPanel.setBackground(new Color(236,240,241));

        ArrayList<Consegna> ordini = delivery.getConsegne();
        if(ordini.isEmpty()){
            JLabel noOrdersLabel = new JLabel("Nessun ordine effettuato.");
            ordiniPanel.add(noOrdersLabel);
        } else {
            for(Consegna ordine : ordini){
                JPanel ordineItemPanel = new JPanel(new BorderLayout());
                ordineItemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
                ordineItemPanel.setBackground(Color.WHITE);
                ordineItemPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

                JLabel ordineLabel = new JLabel("Ordine ID: "+ordine.getId()+" - Totale: "+ordine.getCost()+"€");
                ordineLabel.setFont(new Font("Arial", Font.PLAIN, 16));

                JButton dettagliBtn = new JButton("Dettagli");
                dettagliBtn.setBackground(new Color(255,165,0)); // arancione bottone
                dettagliBtn.setForeground(Color.WHITE);
                dettagliBtn.setFocusPainted(false);
                dettagliBtn.addActionListener(e -> mostraDettagliProdotti(ordine));

                ordineItemPanel.add(ordineLabel, BorderLayout.WEST);
                ordineItemPanel.add(dettagliBtn, BorderLayout.EAST);

                ordiniPanel.add(ordineItemPanel);
                ordiniPanel.add(Box.createRigidArea(new Dimension(0,5))); // spazio tra ordini
            }
        }

        pannelloContenuti.add(ordiniPanel, "Ordini");
        switchPanel("Ordini");
    }

    private void mostraDettagliProdotti(Consegna ordine){
        // crea una finestra modale per mostrare i prodotti
        JDialog dialog = new JDialog((Frame)null, "Prodotti da preparare", true);
        dialog.setSize(400,300);
        dialog.setLocationRelativeTo(null);

        JPanel prodottiPanel = new JPanel();
        prodottiPanel.setLayout(new BoxLayout(prodottiPanel, BoxLayout.Y_AXIS));

        for(Prodotto p :  ordine.getProducts()){
            JLabel prodottoLabel = new JLabel(p.getNomeProd() + " - Quantità: " + p.getQuantity());
            prodottoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            prodottiPanel.add(prodottoLabel);
        }

        JScrollPane scrollPane = new JScrollPane(prodottiPanel);
        dialog.add(scrollPane);

        dialog.setVisible(true);
    }

    private void switchPanel(String cardName) {
        CardLayout cardLayout = (CardLayout) pannelloContenuti.getLayout();
        cardLayout.show(pannelloContenuti, cardName);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ArrayList<Prodotto> menu = new ArrayList<>();

        Restaurant ristorante = new Restaurant("Pizza Napoli", "napoli@mail.com", "napoli123", "", 0, "Via Roma 12", "");
        ristorante.addProdotto(new Prodotto("Margherita", 8.5, "Pizza", "Pomodoro e mozzarella", ristorante));
        ristorante.addProdotto(new Prodotto("Diavola", 9.0, "Pizza", "Salame piccante", ristorante));
        Delivery delivery = new Delivery(ristorante);

        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new RestaurantGUI(frame, ristorante, delivery);
        frame.setVisible(true);
    }
}
