package org.example;

import javax.swing.*;
import java.awt.*;

public class labelRestaurant extends JPanel {
    JButton modifyBtn;

    labelRestaurant(String head, String content, JPanel panel, Restaurant restaurant, String url, String user, String password) {
        // Settaggio del bottone
        modifyBtn = new JButton("modifica");
        modifyBtn.setPreferredSize(new Dimension(120, 60));
        modifyBtn.setBackground(new Color(234, 92, 4));
        modifyBtn.setForeground(Color.white);
        modifyBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        modifyBtn.setBorder(null);

        // Settaggio della label
        JLabel label = new JLabel(head + ": " + content);
        setBackground(Color.white);
        label.setFont(new Font("Arial", Font.BOLD, 20));

        // Aggiunta degli elementi al pannello
        add(label);
        if (!head.equals("orario apertura") && !head.equals("indirizzo")) {
            add(modifyBtn);

            // Posizionamento del bottone
            modifyBtn.setVerticalAlignment(SwingConstants.CENTER);
        }

        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setAlignmentY(Component.CENTER_ALIGNMENT);

        modifyBtn.addActionListener(e -> {
            // Aggiunta del popup per il cambio delle informazioni del ristorante
            String input = JOptionPane.showInputDialog(panel, "Cambia " + head, JOptionPane.QUESTION_MESSAGE);

            if (input.isEmpty()) {
                // Banner di errore
                JOptionPane.showMessageDialog(panel, "Input non valido");
            } else {
                // Azione da eseguire alla pressione del bottone
                if (head.equals("nome")) {
                    // Settaggio del nome del ristorante
                    restaurant.setNome(input);
                    label.setText("Nome: " + input);
                } else if (head.equals("telefono")) {
                    // Settaggio del numero di telefono
                    restaurant.setTelefono(input);
                    label.setText("Telefono: " + input);
                } else if (head.equals("indirizzo")) {
                    // Settaggio dell'indirizzo
                    restaurant.setAddress(input);
                    label.setText("Indirizzo: " + input);
                }

                // Stampa del messaggio di successo
                JOptionPane.showMessageDialog(panel, "Dati aggiornati con successo!");
            }
        });
    }

    // Creazione linea colorata per separare i campi
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
