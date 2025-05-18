package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class customerLabel extends JPanel{

    customerLabel(String head, String content, Cliente customer, String url, String user, String password){
        JLabel label = new JLabel(head+": "+content);
        JButton modifybtn = new JButton("modifica");

        add(label);
        setBackground(Color.white);
        label.setFont(new Font("Arial",Font.ITALIC,18));

        if(!head.equals("mail")){
            add(modifybtn);

            modifybtn.setPreferredSize(new Dimension(120,60));
            modifybtn.setFont(new Font("Arial",Font.ITALIC,18));
            modifybtn.setForeground(Color.white);
            modifybtn.setBackground(new Color(234, 92, 4));
            modifybtn.setBorder(null);
        }

        modifybtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String input = JOptionPane.showInputDialog("inserisci il nuovo valore: ");

                if(!input.isEmpty()){
                    //modifica del dato ed aggiornamento nel database
                    if(head.equals("nome")){
                        customer.setNome(input);
                    }
                    else if(head.equals("password")){
                        customer.setPasword(input);
                    }
                    else if(head.equals("indirizzo")){
                        customer.setAddress(input);
                    }
                    else if(head.equals("citta'")){
                        customer.setCity(input);
                    }
                    else if(head.equals("telefono")){
                        customer.setTelefono(input);
                    }
                    else{
                        //modifica del saldo
                        float tmp = Float.parseFloat(input);
                        customer.setSaldo(customer.getSaldo()+tmp);
                    }
                    label.setText(head+": "+input);

                    //modificare dei dati sul database
                    customer.addtoDB(url,user,password);
                }
            }
        });
    }


}
