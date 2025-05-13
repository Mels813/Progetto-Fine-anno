package org.example;

import javax.swing.*;
import java.awt.*;

public class labelAccount extends JPanel{
    JButton modifyBtn;

    labelAccount(String head, String content, JPanel panel, Rider rider, String url, String user, String password){

        //settaggio del bottone
        modifyBtn = new JButton("modifica");
        modifyBtn.setPreferredSize(new Dimension(120,60));
        modifyBtn.setBackground(new Color(234, 92, 4));
        modifyBtn.setForeground(Color.white);
        modifyBtn.setFont(new Font("Arial",Font.PLAIN,20));
        modifyBtn.setBorder(null);

        //settaggio label
        JLabel label = new JLabel(head + ": " + content);
        setBackground(Color.white);

        label.setFont(new Font("Arial",Font.BOLD,20));

        //aggiunta elementi al pannello
        add(label);
        if(!head.equals("saldo") && !head.equals("mail")){
            add(modifyBtn);

            //posizionamento del bottone
            modifyBtn.setVerticalAlignment(SwingConstants.CENTER);
        }

        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setAlignmentY(Component.CENTER_ALIGNMENT);

        modifyBtn.addActionListener(e -> {
            //aggiunta del popup per il cambio credenziali
            String input = JOptionPane.showInputDialog(panel,"cambia la tua "+head,JOptionPane.QUESTION_MESSAGE);

            if(input.isEmpty()){
                //banner di errore
                JOptionPane.showMessageDialog(panel,"input non valido");
            }
            else{
                //azione da eseguire alla pressione del bottone
                if(head.equals("nome")){
                    //settaggio del nome
                    rider.setNome(input);
                    label.setText("nome: "+input);
                }
                else if(head.equals("telefono")){
                    rider.setTelefono(input);
                    label.setText("telefono: "+input);
                }
                else if(head.equals("password")){
                    rider.setPasword(input);
                    label.setText("password: "+input);
                }

                //riscrittura dei dati nel database
                rider.addtoDB(url,user,password);

                //stampa del messaggio di successo
                JOptionPane.showMessageDialog(panel,"dati aggiornati con successo!");
            }
        });
    }

    //creazione linea colorata per separare i campi
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        int width = getWidth();
        int heigth = getHeight();

        int margin = (int) (width * 0.10);
        int lineWidth = (int) (width * 0.80);
        int yPosition = heigth - 1;

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(234, 92, 4));
        g2.setStroke(new BasicStroke(2));

        g2.drawLine(margin, yPosition, margin + lineWidth, yPosition);
    }
}