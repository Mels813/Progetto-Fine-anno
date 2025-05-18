package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuUtente2 extends JPanel{
    private JPanel menuLaterale;
    private boolean menuVisibile = false;
    private JButton btnUtente = new JButton("☰");
    private JPanel pannelloContenuti = new JPanel(new BorderLayout());
    private boolean ristorantiVisibili = false;
    private Delivery delivery;
    private Cliente cliente;

    public MenuUtente2(JFrame frame, Cliente c, ArrayList<Restaurant> listaRistoranti, Delivery delivery, String url, String user, String password) {
        this.delivery = delivery;
        this.cliente = c;
        setLayout(new BorderLayout());

        // Menu laterale
        menuLaterale = new JPanel();
        menuLaterale.setLayout(new GridLayout(4, 1)); // Aggiungiamo una riga per l'Area Personale
        menuLaterale.setPreferredSize(new Dimension(200, 700));
        menuLaterale.setVisible(true);

        // Pulsanti del menu
        JButton btnProfilo = new JButton("👤 Area Personale");
        JButton btnCarrello = new JButton("🛒 Carrello");
        JButton btnConsegne = new JButton("🚚 Consegne");
        JButton btnRistoranti = new JButton("🍽️ Ristoranti");

        // Azioni dei pulsanti
        btnRistoranti.addActionListener(e -> mostraRistoranti(listaRistoranti,url,user,password));

        btnCarrello.addActionListener(e -> mostraCarrello(url, user, password));

        // Azione per il pulsante "👤 Area Personale"
        btnProfilo.addActionListener(e -> mostraAreaPersonale(c,url,user,password));

        // Azione per il pulsante "🚚 Consegne"
        btnConsegne.addActionListener(e -> mostraConsegne(c.getConsegne(url,user,password)));

        // Aggiungi i pulsanti al menu
        menuLaterale.add(btnProfilo);
        menuLaterale.add(btnCarrello);
        menuLaterale.add(btnConsegne);
        menuLaterale.add(btnRistoranti);

        // Messaggio di benvenuto
        JLabel benvenuto = new JLabel("Benvenuto nel menu!", SwingConstants.CENTER);
        benvenuto.setFont(new Font("Arial", Font.BOLD, 24));
        pannelloContenuti.add(benvenuto, BorderLayout.CENTER);

        // Pulsante ☰
        btnUtente.addActionListener(e -> {
            menuVisibile = !menuVisibile;
            menuLaterale.setVisible(menuVisibile);
        });

        // Pannello in alto
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(btnUtente);
        add(topPanel, BorderLayout.NORTH);

        // Aggiunta componenti principali
        add(pannelloContenuti, BorderLayout.CENTER);
        add(menuLaterale, BorderLayout.EAST);

        frame.add(this);
    }

    public void mostraAreaPersonale(Cliente cliente, String url, String user, String password){
        pannelloContenuti.removeAll();

        pannelloContenuti.setLayout(new GridLayout(7,1));

        pannelloContenuti.add(new customerLabel("nome",cliente.getNome(),cliente,url,user,password));
        pannelloContenuti.add(new customerLabel("mail",cliente.getMail(),cliente,url,user,password));
        pannelloContenuti.add(new customerLabel("password",cliente.getMail(),cliente,url,user,password));
        pannelloContenuti.add(new customerLabel("numero di telefono",cliente.getTelefono(),cliente,url,user,password));
        pannelloContenuti.add(new customerLabel("citta'",cliente.getCity(),cliente,url,user,password));
        pannelloContenuti.add(new customerLabel("indirizzo",cliente.getAddress(),cliente,url,user,password));
        pannelloContenuti.add(new customerLabel("saldo",String.valueOf(cliente.getSaldo()),cliente,url,user,password));

        pannelloContenuti.revalidate();
        pannelloContenuti.repaint();
    }


    // Metodo per creare una JLabel formattata per ogni informazione
    private JLabel creaLabel(String testo) {
        JLabel label = new JLabel(testo);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setPreferredSize(new Dimension(400, 30));  // Puoi regolare la dimensione come preferisci
        return label;
    }

    public void mostraRistoranti(ArrayList<Restaurant> lista, String url, String user, String password) {
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

            //mostra del menu' del ristorante quando si preme il bottone
            menuButton.addActionListener(e -> mostraMenuRistorante(r.getProducts(url, user, password)));

            restaurantPanel.add(nome);
            restaurantPanel.add(Box.createVerticalStrut(5));
            restaurantPanel.add(menuButton);

            centro.add(restaurantPanel);
            centro.add(Box.createVerticalStrut(20));
        }

        JScrollPane scrollPane = new JScrollPane(centro);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        pannelloContenuti.add(scrollPane, BorderLayout.CENTER);
        pannelloContenuti.revalidate();
        pannelloContenuti.repaint();
        ristorantiVisibili = true;
    }

    private void mostraMenuRistorante(ArrayList<Prodotto> products) {
        pannelloContenuti.removeAll();

        JPanel menuPanel = new JPanel(new BorderLayout());

        JLabel immagineLabel = new JLabel(new ImageIcon("path_to_image.jpg")); // Cambia con percorso valido
        menuPanel.add(immagineLabel, BorderLayout.WEST);

        JPanel textAndButtonsPanel = new JPanel();
        textAndButtonsPanel.setLayout(new BoxLayout(textAndButtonsPanel, BoxLayout.Y_AXIS));

        for (Prodotto pd : products) {
            JPanel prodottoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel nomeProdotto = new JLabel(pd.getNomeProd() + " - " + pd.getPrezzo() + "€");

            JButton btnDettagli = new JButton("Dettagli");
            btnDettagli.addActionListener(e -> {
                JOptionPane.showMessageDialog(this,
                        "Categoria: " + pd.getCategoria() +
                                "\nDescrizione: " + pd.getDescrizione(),
                        "Dettagli Prodotto",
                        JOptionPane.INFORMATION_MESSAGE);
            });

            JButton btnAggiungi = new JButton("+");
            JButton btnRimuovi = new JButton("-");
            JLabel quantitàLabel = new JLabel("Quantità: " + pd.getQuantity());

            btnAggiungi.addActionListener(e -> {
                if (delivery.addProduct(pd)) {
                    quantitàLabel.setText("Quantità: " + pd.getQuantity());
                } else {
                    JOptionPane.showMessageDialog(this, "Non puoi aggiungere prodotti da ristoranti diversi.", "Errore", JOptionPane.WARNING_MESSAGE);
                }
            });

            btnRimuovi.addActionListener(e -> {
                delivery.removeProduct(pd);
                quantitàLabel.setText("Quantità: " + pd.getQuantity());
            });

            prodottoPanel.add(nomeProdotto);
            prodottoPanel.add(btnDettagli);
            prodottoPanel.add(btnAggiungi);
            prodottoPanel.add(btnRimuovi);
            prodottoPanel.add(quantitàLabel);

            textAndButtonsPanel.add(prodottoPanel);
        }

        JScrollPane scrollPane = new JScrollPane(textAndButtonsPanel);
        menuPanel.add(scrollPane, BorderLayout.CENTER);

        pannelloContenuti.add(menuPanel, BorderLayout.CENTER);
        pannelloContenuti.revalidate();
        pannelloContenuti.repaint();
    }

    private void mostraCarrello(String url, String user, String password){
        pannelloContenuti.removeAll(); // Rimuove tutto dal pannello contenitore per aggiornare la vista

        JPanel carrelloPanel = new JPanel();
        carrelloPanel.setLayout(new BoxLayout(carrelloPanel, BoxLayout.Y_AXIS));
        carrelloPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        carrelloPanel.setBackground(Color.WHITE);

        // Verifica se il carrello è vuoto
        if (delivery.getProducts() == 0) {
            JLabel messaggio = new JLabel("Il tuo carrello è vuoto.", SwingConstants.CENTER);
            messaggio.setFont(new Font("Arial", Font.ITALIC, 18));
            carrelloPanel.add(messaggio);
        }
        else{
            // Aggiungi ogni prodotto al pannello
            for (Prodotto p : delivery.getCart()) {
                JPanel prodottoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                prodottoPanel.setBackground(new Color(245, 245, 245));
                prodottoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                // Nome e prezzo del prodotto
                JLabel nome = new JLabel(p.getNomeProd() + " - " + String.format("%.2f", p.getPrezzo()) + "€");
                // Quantità del prodotto
                JLabel quantita = new JLabel(" x " + p.getQuantity());

                // Bottone per rimuovere il prodotto dal carrello
                JButton btnRimuovi = new JButton("-");
                btnRimuovi.addActionListener(e -> {
                    delivery.removeProduct(p);  // Rimuove il prodotto dal carrello
                    mostraCarrello(url, user, password);  // Rende di nuovo visibile il carrello aggiornato
                });

                // Aggiungi il nome, la quantità e il bottone al pannello del prodotto
                prodottoPanel.add(nome);
                prodottoPanel.add(quantita);
                prodottoPanel.add(btnRimuovi);
                carrelloPanel.add(prodottoPanel);
            }

            // Etichetta per il totale
            JLabel totale = new JLabel("Totale: " + String.format("%.2f", delivery.sumCart()) + "€");
            totale.setFont(new Font("Arial", Font.BOLD, 18));
            totale.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Aggiungi il totale alla fine
            carrelloPanel.add(Box.createVerticalStrut(20)); // Aggiunge uno spazio tra i prodotti e il totale
            carrelloPanel.add(totale);

            // Bottone "Ordina"
            JButton btnOrdina = new JButton("Ordina");
            btnOrdina.setFont(new Font("Arial", Font.BOLD, 16));
            btnOrdina.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnOrdina.addActionListener(e -> {
                // Conferma dell'ordine
                if (delivery.getProducts()>0){
                    if(cliente.getSaldo()<delivery.sumCart()){
                        JOptionPane.showInputDialog(null,"saldo insufficiente per pagare il conto");
                    }
                    else{
                        int tmp = delivery.findRider(url, user, password);
                        //controllo ricerca del rider
                        switch(tmp){
                            case -1:
                                JOptionPane.showMessageDialog(null,"errore di comunicazione");
                                break;
                            case 0:
                                JOptionPane.showMessageDialog(null,"nessun rider disponibile per effettuare la consegna");
                                break;
                            case 1:
                                //pagamento del servizio
                                delivery.payService(url,user,password);

                                //aggiunta della delivery al database
                                delivery.addDeliverytoDB(url,user,password);
                                JOptionPane.showMessageDialog(null,"consegna completata con successo");
                                delivery.clearCart();   //eliminazione di tutti i prodotti dal carrello
                                break;
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Il carrello è vuoto!");
                }
            });

            carrelloPanel.add(btnOrdina); // Aggiungi il bottone "Ordina" al pannello
        }

        // Aggiungi il pannello del carrello alla vista principale
        pannelloContenuti.add(new JScrollPane(carrelloPanel), BorderLayout.CENTER);
        pannelloContenuti.revalidate(); // Rende effettive le modifiche
        pannelloContenuti.repaint();    // Rende visibile il carrello aggiornato
    }

    private void mostraConsegne(ArrayList<Consegna> consegne){
        pannelloContenuti.removeAll();
        if(consegne.isEmpty()){
            JLabel emptyLabel = new JLabel("non ci sono consegne in attesa");
            pannelloContenuti.add(emptyLabel);
        }
        else{
            JPanel scrollPanel = new JPanel();
            JLabel title = new JLabel("storico consegne");
            pannelloContenuti.add(title);
            for(Consegna c : consegne){
                JPanel orderPanel = new JPanel();
                JPanel headPanel = new JPanel();
                JPanel contentPanel = new JPanel();
                JPanel btnPanel = new JPanel();
                headPanel.setLayout(new GridLayout(1,3));
                contentPanel.setLayout(new GridLayout(1,3));
                orderPanel.setLayout(new GridLayout(3,1));


                //aggiunta titoli
                headPanel.add(new JLabel("ordine"));
                headPanel.add(new JLabel("ristornate"));
                headPanel.add(new JLabel("costo"));

                //aggiunta content
                contentPanel.add(new JLabel(String.valueOf(c.getId())));
                contentPanel.add(new JLabel(c.getRsName()));
                contentPanel.add(new JLabel(String.valueOf(c.getCost())));

                //aggiunta bottone
                JButton detailsBtn = new JButton("dettagli");
                btnPanel.add(detailsBtn);

                //aggiunta pannelli secondari al principale
                orderPanel.add(headPanel);
                orderPanel.add(contentPanel);
                orderPanel.add(detailsBtn);


                //popup con tutti i prodotti quando viene premuto il bottone
                detailsBtn.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        String details = "";
                        for(int i=0;i<c.getLength();i++){
                            details += "\n"+c.getProduct(i).getNomeProd()+" x"+c.getProduct(i).getQuantity();
                        }
                        JOptionPane.showMessageDialog(pannelloContenuti,details);
                    }
                });

                //aggiunta pannello allo scroll pane
                scrollPanel.add(orderPanel);
            }
            pannelloContenuti.add(new JScrollPane(scrollPanel),BorderLayout.CENTER);
        }

        //aggiornamento frame
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
}