package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class labelRestaurant extends JPanel {
    private JButton modifyBtn;
    private static JButton prodottiBtn; // static per bottone unico condiviso

    public labelRestaurant(String head,
                           String content,
                           JPanel panel,
                           Restaurant restaurant,
                           String url,
                           String user,
                           String password) {

        setLayout(new BorderLayout());
        setBackground(Color.white);

        // label con testo centrato a sinistra
        JLabel label = new JLabel(head + ": " + content);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.LEFT);

        // pannello centrale con label e bottone modifica (centrati)
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.white);
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        centerPanel.add(label);

        // setup bottone modifica
        modifyBtn = new JButton("modifica");
        modifyBtn.setPreferredSize(new Dimension(120, 60));
        modifyBtn.setBackground(new Color(234, 92, 4));
        modifyBtn.setForeground(Color.white);
        modifyBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        modifyBtn.setBorder(null);

        // aggiungo modifica solo se non mail o saldo
        if (!head.equalsIgnoreCase("mail") && !head.equalsIgnoreCase("saldo")) {
            centerPanel.add(modifyBtn);

            modifyBtn.addActionListener(e -> {
                String input = JOptionPane.showInputDialog(
                        panel,
                        "Cambia il/la tuo/a " + head,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (input == null || input.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Input non valido");
                    return;
                }

                switch (head.toLowerCase()) {
                    case "nome":
                        restaurant.setNome(input);
                        break;
                    case "telefono":
                        restaurant.setTelefono(input);
                        break;
                    case "password":
                        restaurant.setPasword(input);
                        break;
                    case "città":
                    case "city":
                        restaurant.setCity(input);
                        break;
                    case "indirizzo":
                    case "address":
                        restaurant.setIndirizzo(input);
                        break;
                }

                restaurant.addtoDB(url, user, password);
                label.setText(head + ": " + input);
                JOptionPane.showMessageDialog(panel, "Dati aggiornati con successo!");
            });
        }

        add(centerPanel, BorderLayout.CENTER);

        // --- Bottone Prodotti unico a destra ---
        if (prodottiBtn == null) {
            prodottiBtn = new JButton("Prodotti");
            prodottiBtn.setPreferredSize(new Dimension(120, 60));
            prodottiBtn.setBackground(new Color(30, 144, 255));  // blu
            prodottiBtn.setForeground(Color.white);
            prodottiBtn.setFont(new Font("Arial", Font.PLAIN, 20));
            prodottiBtn.setBorder(null);

            prodottiBtn.addActionListener(e -> {
                mostraGestioneProdotti(panel, restaurant, url, user, password);
            });
        }
        // Lo aggiungo solo nel primo labelRestaurant (oppure su ogni istanza ma sarà visibile 1 volta)
        // Qui lo mettiamo sempre ma è statico, quindi unico.
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        rightPanel.setBackground(Color.white);
        rightPanel.add(prodottiBtn);
        add(rightPanel, BorderLayout.EAST);
    }

    private void mostraGestioneProdotti(JPanel parentPanel, Restaurant restaurant, String url, String user, String password) {
        ArrayList<Prodotto> prodotti = restaurant.getProducts(url, user, password);
        if (prodotti == null) {
            JOptionPane.showMessageDialog(parentPanel, "Errore nel caricamento dei prodotti");
            return;
        }

        DefaultListModel<Prodotto> listModel = new DefaultListModel<>();
        for (Prodotto p : prodotti) listModel.addElement(p);

        JList<Prodotto> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(10);
        JScrollPane scrollPane = new JScrollPane(list);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Color.white);
        JButton btnAggiungi = new JButton("Aggiungi");
        JButton btnModifica = new JButton("Modifica");
        JButton btnElimina = new JButton("Elimina");
        btnPanel.add(btnAggiungi);
        btnPanel.add(btnModifica);
        btnPanel.add(btnElimina);

        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(parentPanel), "Gestione Prodotti", true);
        dialog.setContentPane(panel);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(parentPanel);

        btnAggiungi.addActionListener(e -> {
            Prodotto nuovo = inputProdotto(null, restaurant, parentPanel);
            if (nuovo != null) {
                int res = restaurant.addProduct(url, user, password, nuovo);
                if (res >= 0) {
                    listModel.addElement(nuovo);
                    JOptionPane.showMessageDialog(parentPanel, "Prodotto aggiunto/modificato con successo");
                } else {
                    JOptionPane.showMessageDialog(parentPanel, "Errore nell'aggiunta/modifica prodotto");
                }
            }
        });

        btnModifica.addActionListener(e -> {
            Prodotto selezionato = list.getSelectedValue();
            if (selezionato == null) {
                JOptionPane.showMessageDialog(parentPanel, "Seleziona un prodotto da modificare");
                return;
            }
            Prodotto modificato = inputProdotto(selezionato, restaurant, parentPanel);
            if (modificato != null) {
                int res = restaurant.addProduct(url, user, password, modificato);
                if (res >= 0) {
                    int idx = list.getSelectedIndex();
                    listModel.setElementAt(modificato, idx);
                    JOptionPane.showMessageDialog(parentPanel, "Prodotto modificato con successo");
                } else {
                    JOptionPane.showMessageDialog(parentPanel, "Errore nella modifica prodotto");
                }
            }
        });

        btnElimina.addActionListener(e -> {
            Prodotto selezionato = list.getSelectedValue();
            if (selezionato == null) {
                JOptionPane.showMessageDialog(parentPanel, "Seleziona un prodotto da eliminare");
                return;
            }
            int conferma = JOptionPane.showConfirmDialog(parentPanel,
                    "Sei sicuro di voler eliminare il prodotto: " + selezionato.getNomeProd() + "?",
                    "Conferma eliminazione",
                    JOptionPane.YES_NO_OPTION);
            if (conferma == JOptionPane.YES_OPTION) {
                boolean eliminato = eliminaProdottoDB(restaurant, url, user, password, selezionato);
                if (eliminato) {
                    listModel.removeElement(selezionato);
                    JOptionPane.showMessageDialog(parentPanel, "Prodotto eliminato con successo");
                } else {
                    JOptionPane.showMessageDialog(parentPanel, "Errore durante l'eliminazione del prodotto");
                }
            }
        });

        dialog.setVisible(true);
    }

    private boolean eliminaProdottoDB(Restaurant restaurant, String url, String user, String password, Prodotto prodotto) {
        String query = "DELETE FROM PRODOTTO WHERE name = ? AND restaurantPhone = ?";
        try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
             java.sql.PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, prodotto.getNomeProd());
            ps.setString(2, restaurant.getPhone());
            int deleted = ps.executeUpdate();
            return deleted > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private Prodotto inputProdotto(Prodotto oldProdotto, Restaurant restaurant, Component parent) {
        JTextField nomeField = new JTextField();
        JTextField prezzoField = new JTextField();
        JTextField categoriaField = new JTextField();
        JTextField descrizioneField = new JTextField();

        if (oldProdotto != null) {
            nomeField.setText(oldProdotto.getNomeProd());
            prezzoField.setText(String.valueOf(oldProdotto.getPrezzo()));
            categoriaField.setText(oldProdotto.getCategoria());
            descrizioneField.setText(oldProdotto.getDescrizione());
        }

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nome prodotto:"));
        panel.add(nomeField);
        panel.add(new JLabel("Prezzo:"));
        panel.add(prezzoField);
        panel.add(new JLabel("Categoria:"));
        panel.add(categoriaField);
        panel.add(new JLabel("Descrizione:"));
        panel.add(descrizioneField);

        int result = JOptionPane.showConfirmDialog(parent, panel,
                oldProdotto == null ? "Aggiungi Prodotto" : "Modifica Prodotto",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String nome = nomeField.getText().trim();
                float prezzo = Float.parseFloat(prezzoField.getText().trim());
                String categoria = categoriaField.getText().trim();
                String descrizione = descrizioneField.getText().trim();

                if (nome.isEmpty() || categoria.isEmpty() || descrizione.isEmpty()) {
                    JOptionPane.showMessageDialog(parent, "Tutti i campi devono essere compilati");
                    return null;
                }
                return new Prodotto(nome, prezzo, categoria, descrizione, restaurant);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(parent, "Prezzo non valido");
                return null;
            }
        }
        return null;
    }

    // linea colorata di separazione
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        int margin = (int) (width * 0.10);
        int lineWidth = (int) (width * 0.80);
        int yPosition = height - 1;

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(234, 92, 4));
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(margin, yPosition, margin + lineWidth, yPosition);
    }
}
