//interfaccia utente per il rider

package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class restaurantGUI extends JPanel{
    restaurantGUI(Restaurant restaurant, JFrame frame, String url, String user, String password){
        JPanel menuPanel = new JPanel();
        JPanel centralPanel = new JPanel();

        //bottoni menu
        JButton accountBtn = new JButton("account");
        JButton deliveryBtn = new JButton("ordini");
        JButton prodottiBtn = new JButton("prodotti");

        //settaggio bottoni
        accountBtn.setBackground(Color.white);
        deliveryBtn.setBackground(Color.white);
        accountBtn.setFont(new Font("Arial",Font.BOLD,20));
        deliveryBtn.setFont(accountBtn.getFont());

        frame.setLayout(null);

        //settaggio dimensioni main panel e pannelli secondari
        setSize(frame.getWidth(),frame.getHeight());
        setBounds(0,0,frame.getWidth(),frame.getHeight());
        setLayout(null);

        //calcolo dimensioni
        int centralWidth = (int)(getWidth()*0.7);
        int menuWidth = (int)(getWidth()*0.3);

        //settaggio dimensione pannelli
        setBounds(0,0,frame.getWidth(),frame.getHeight());
        centralPanel.setBounds(0,0,centralWidth,getHeight());
        menuPanel.setBounds(centralWidth,0,menuWidth,getHeight());

        //aggiunta bottoni al menu
        menuPanel.add(accountBtn);
        menuPanel.add(deliveryBtn);
        menuPanel.add(prodottiBtn);
        menuPanel.setLayout(new GridLayout(3,1));

        //aggiunta componenti
        add(centralPanel);
        add(menuPanel);

        //btn account
        accountBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                centralPanel.removeAll();

                //elementi tutti in colonna
                centralPanel.setLayout(new GridLayout(5,1));

                //aggiunta delle sezioni al cliente
                centralPanel.add(new labelAccount("nome",restaurant.getNome(),centralPanel,restaurant,url,user,password));
                centralPanel.add(new labelAccount("mail",restaurant.getEmail(),centralPanel,restaurant,url,user,password));
                centralPanel.add(new labelAccount("password",restaurant.getPassword(),centralPanel,restaurant,url,user,password));
                centralPanel.add(new labelAccount("telefono",restaurant.getTelefono(),centralPanel,restaurant,url,user,password));
                centralPanel.add(new labelAccount("saldo",String.valueOf(restaurant.getSaldo()),centralPanel,restaurant,url,user,password));

                //refresh frame
                frame.repaint();
                frame.revalidate();
            }
        });
        //btn delivery
        deliveryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                centralPanel.removeAll();
                ArrayList<String> info = new ArrayList<>();
                //info = restaurant.getDeliveryFromDB(url,user,password);  //controllo se ci sono consegne che devono essere svolte dal rider

                if(info==null) {
                    JLabel label = new JLabel("nessuna consegna da svolgere");
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setVerticalAlignment(SwingConstants.CENTER);
                    label.setFont(new Font("Arial",Font.PLAIN,25));
                    centralPanel.setBackground(Color.white);

                    centralPanel.add(label);
                }
                else {

                }

                //refresh frame
                frame.revalidate();
                frame.repaint();
            }
        });
    }
}

