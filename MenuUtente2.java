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
    private Delivery delivery;

    public MenuUtente2(JFrame frame, Cliente c, ArrayList<Restaurant> listaRistoranti, Delivery delivery) {
        this.delivery = delivery;
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
        btnRistoranti.addActionListener(e -> {
            if (ristorantiVisibili) {
                tornaAlMenuIniziale();
            } else {
                mostraRistoranti(listaRistoranti);
            }
        });

        btnCarrello.addActionListener(e -> mostraCarrello());

        // Azione per il pulsante "👤 Area Personale"
        btnProfilo.addActionListener(e -> mostraAreaPersonale(c));

        // Azione per il pulsante "🚚 Consegne"
        btnConsegne.addActionListener(e -> mostraConsegne());

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

    public void mostraAreaPersonale(Cliente cliente) {
        pannelloContenuti.removeAll();

        // Crea il pannello principale con un layout BoxLayout per allineare verticalmente i pannelli
        JPanel areaPersonalePanel = new JPanel();
        areaPersonalePanel.setLayout(new BoxLayout(areaPersonalePanel, BoxLayout.Y_AXIS));

        // Crea il pannello per ogni campo con tasto "Modifica"
        JPanel pannelloNome = new JPanel();
        pannelloNome.setLayout(new BoxLayout(pannelloNome, BoxLayout.X_AXIS)); // Allineamento orizzontale
        JLabel lblNome = new JLabel("Nome: ");
        JLabel lblNomeValore = new JLabel(cliente.getNome());
        JButton btnModificaNome = new JButton("Modifica");
        btnModificaNome.addActionListener(e -> {
            String nuovoNome = JOptionPane.showInputDialog(null, "Modifica Nome", cliente.getNome());
            if (nuovoNome != null && !nuovoNome.isEmpty()) {
                lblNomeValore.setText(nuovoNome);
                cliente.setNome(nuovoNome); // Aggiorna il cliente
            }
        });
        pannelloNome.add(lblNome);
        pannelloNome.add(lblNomeValore);
        pannelloNome.add(Box.createHorizontalStrut(10)); // Spazio tra il valore e il pulsante
        pannelloNome.add(btnModificaNome);

        JPanel pannelloEmail = new JPanel();
        pannelloEmail.setLayout(new BoxLayout(pannelloEmail, BoxLayout.X_AXIS));
        JLabel lblEmail = new JLabel("Email: ");
        JLabel lblEmailValore = new JLabel(cliente.getEmail());
        JButton btnModificaEmail = new JButton("Modifica");
        btnModificaEmail.addActionListener(e -> {
            String nuovaEmail = JOptionPane.showInputDialog(null, "Modifica Email", cliente.getEmail());
            if (nuovaEmail != null && !nuovaEmail.isEmpty()) {
                lblEmailValore.setText(nuovaEmail);
                cliente.setEmail(nuovaEmail); // Aggiorna il cliente
            }
        });
        pannelloEmail.add(lblEmail);
        pannelloEmail.add(lblEmailValore);
        pannelloEmail.add(Box.createHorizontalStrut(10));
        pannelloEmail.add(btnModificaEmail);

        JPanel pannelloTelefono = new JPanel();
        pannelloTelefono.setLayout(new BoxLayout(pannelloTelefono, BoxLayout.X_AXIS));
        JLabel lblTelefono = new JLabel("Telefono: ");
        JLabel lblTelefonoValore = new JLabel(cliente.getTelefono());
        JButton btnModificaTelefono = new JButton("Modifica");
        btnModificaTelefono.addActionListener(e -> {
            String nuovoTelefono = JOptionPane.showInputDialog(null, "Modifica Telefono", cliente.getTelefono());
            if (nuovoTelefono != null && !nuovoTelefono.isEmpty()) {
                lblTelefonoValore.setText(nuovoTelefono);
                cliente.setTelefono(nuovoTelefono); // Aggiorna il cliente
            }
        });
        pannelloTelefono.add(lblTelefono);
        pannelloTelefono.add(lblTelefonoValore);
        pannelloTelefono.add(Box.createHorizontalStrut(10));
        pannelloTelefono.add(btnModificaTelefono);

        JPanel pannelloPassword = new JPanel();
        pannelloPassword.setLayout(new BoxLayout(pannelloPassword, BoxLayout.X_AXIS));
        JLabel lblPassword = new JLabel("Password: ");
        JLabel lblPasswordValore = new JLabel(cliente.getPassword());
        JButton btnModificaPassword = new JButton("Modifica");
        btnModificaPassword.addActionListener(e -> {
            String nuovaPassword = JOptionPane.showInputDialog(null, "Modifica Password", cliente.getPassword());
            if (nuovaPassword != null && !nuovaPassword.isEmpty()) {
                lblPasswordValore.setText(nuovaPassword);
                cliente.setPasword(nuovaPassword); // Aggiorna il cliente
            }
        });
        pannelloPassword.add(lblPassword);
        pannelloPassword.add(lblPasswordValore);
        pannelloPassword.add(Box.createHorizontalStrut(10));
        pannelloPassword.add(btnModificaPassword);

        JPanel pannelloSaldo = new JPanel();
        pannelloSaldo.setLayout(new BoxLayout(pannelloSaldo, BoxLayout.X_AXIS));
        JLabel lblSaldo = new JLabel("Saldo: ");
        JLabel lblSaldoValore = new JLabel(cliente.getSaldo() + "€");
        JButton btnModificaSaldo = new JButton("Modifica");
        btnModificaSaldo.addActionListener(e -> {
            String nuovoSaldo = JOptionPane.showInputDialog(null, "Modifica Saldo", cliente.getSaldo());
            if (nuovoSaldo != null && !nuovoSaldo.isEmpty()) {
                lblSaldoValore.setText(nuovoSaldo + "€");
                cliente.setSaldo(Float.parseFloat(nuovoSaldo)); // Aggiorna il cliente
            }
        });
        pannelloSaldo.add(lblSaldo);
        pannelloSaldo.add(lblSaldoValore);
        pannelloSaldo.add(Box.createHorizontalStrut(10));
        pannelloSaldo.add(btnModificaSaldo);

        JPanel pannelloIndirizzo = new JPanel();
        pannelloIndirizzo.setLayout(new BoxLayout(pannelloIndirizzo, BoxLayout.X_AXIS));
        JLabel lblIndirizzo = new JLabel("Indirizzo: ");
        JLabel lblIndirizzoValore = new JLabel(cliente.getAddress());
        JButton btnModificaIndirizzo = new JButton("Modifica");
        btnModificaIndirizzo.addActionListener(e -> {
            String nuovoIndirizzo = JOptionPane.showInputDialog(null, "Modifica Indirizzo", cliente.getAddress());
            if (nuovoIndirizzo != null && !nuovoIndirizzo.isEmpty()) {
                lblIndirizzoValore.setText(nuovoIndirizzo);
                cliente.setAddress(nuovoIndirizzo); // Aggiorna il cliente
            }
        });
        pannelloIndirizzo.add(lblIndirizzo);
        pannelloIndirizzo.add(lblIndirizzoValore);
        pannelloIndirizzo.add(Box.createHorizontalStrut(10));
        pannelloIndirizzo.add(btnModificaIndirizzo);

        JPanel pannelloCitta = new JPanel();
        pannelloCitta.setLayout(new BoxLayout(pannelloCitta, BoxLayout.X_AXIS));
        JLabel lblCitta = new JLabel("Città: ");
        JLabel lblCittaValore = new JLabel(cliente.getCity());
        JButton btnModificaCitta = new JButton("Modifica");
        btnModificaCitta.addActionListener(e -> {
            String nuovaCitta = JOptionPane.showInputDialog(null, "Modifica Città", cliente.getCity());
            if (nuovaCitta != null && !nuovaCitta.isEmpty()) {
                lblCittaValore.setText(nuovaCitta);
                cliente.setCity(nuovaCitta); // Aggiorna il cliente
            }
        });
        pannelloCitta.add(lblCitta);
        pannelloCitta.add(lblCittaValore);
        pannelloCitta.add(Box.createHorizontalStrut(10));
        pannelloCitta.add(btnModificaCitta);

        // Aggiungi tutti i pannelli al pannello principale
        areaPersonalePanel.add(pannelloNome);
        areaPersonalePanel.add(pannelloEmail);
        areaPersonalePanel.add(pannelloTelefono);
        areaPersonalePanel.add(pannelloPassword);
        areaPersonalePanel.add(pannelloSaldo);
        areaPersonalePanel.add(pannelloIndirizzo);
        areaPersonalePanel.add(pannelloCitta);

        // Aggiungi il pannello al contenitore principale
        pannelloContenuti.add(areaPersonalePanel, BorderLayout.CENTER);

        // Rende visibile il pannello
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

        JPanel menuPanel = new JPanel(new BorderLayout());

        JLabel immagineLabel = new JLabel(new ImageIcon("path_to_image.jpg")); // Cambia con percorso valido
        menuPanel.add(immagineLabel, BorderLayout.WEST);

        JPanel textAndButtonsPanel = new JPanel();
        textAndButtonsPanel.setLayout(new BoxLayout(textAndButtonsPanel, BoxLayout.Y_AXIS));

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
                if (delivery.addProduct(prodotto)) {
                    quantitàLabel.setText("Quantità: " + prodotto.getQuantity());
                } else {
                    JOptionPane.showMessageDialog(this, "Non puoi aggiungere prodotti da ristoranti diversi.", "Errore", JOptionPane.WARNING_MESSAGE);
                }
            });

            btnRimuovi.addActionListener(e -> {
                delivery.removeProduct(prodotto);
                quantitàLabel.setText("Quantità: " + prodotto.getQuantity());
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

    private void mostraCarrello() {
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
        } else {
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
                    mostraCarrello();  // Rende di nuovo visibile il carrello aggiornato
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
                if (delivery.getProducts() > 0) {
                    delivery.confermaOrdine();  // Conferma l'ordine
                    mostraCarrello();  // Rende visibile l'aggiornamento del carrello
                    mostraConsegne();  // Mostra l'aggiornamento delle consegne
                    JOptionPane.showMessageDialog(null, "Ordine confermato!");  // Messaggio di conferma
                } else {
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

    private void mostraConsegne() {
        pannelloContenuti.removeAll();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        panel.setBackground(Color.WHITE);

        JLabel titolo = new JLabel("Storico Consegne");
        titolo.setFont(new Font("Arial", Font.BOLD, 22));
        titolo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titolo);
        panel.add(Box.createVerticalStrut(20));

        ArrayList<Consegna> listaConsegne = delivery.getConsegne();

        if (listaConsegne.isEmpty()) {
            JLabel nessuna = new JLabel("Nessuna consegna effettuata.");
            nessuna.setFont(new Font("Arial", Font.ITALIC, 18));
            nessuna.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(nessuna);
        } else {
            for (Consegna c : listaConsegne) {
                JPanel consegnaPanel = new JPanel(new GridLayout(2, 1));
                consegnaPanel.setBackground(new Color(245, 245, 245));
                consegnaPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.GRAY),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));

                JLabel ristorante = new JLabel("Ristorante: " + c.getRistorante());
                JLabel totale = new JLabel("Totale: " + String.format("%.2f", c.getTotale()) + "€");

                ristorante.setFont(new Font("Arial", Font.BOLD, 16));
                totale.setFont(new Font("Arial", Font.PLAIN, 14));


                consegnaPanel.add(ristorante);
                consegnaPanel.add(totale);


                panel.add(consegnaPanel);
                panel.add(Box.createVerticalStrut(15));
            }
        }

        pannelloContenuti.add(new JScrollPane(panel), BorderLayout.CENTER);
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
        Delivery d = new Delivery(c);

        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new MenuUtente2(frame, c, lista, d);
        frame.setVisible(true);
    }
    }
