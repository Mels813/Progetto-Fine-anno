//interfaccia utente per il rider

package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class riderGui extends JPanel{
    riderGui(Rider rider, JFrame frame, String url, String user, String password){
        JPanel menuPanel = new JPanel();
        JPanel centralPanel = new JPanel();

        //bottoni menu
        JButton accountBtn = new JButton("account");
        JButton deliveryBtn = new JButton("delivery");

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
        menuPanel.setLayout(new GridLayout(2,1));

        //aggiunta componenti
        add(centralPanel);
        add(menuPanel);

        accountBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                centralPanel.removeAll();

                //elementi tutti in colonna
                centralPanel.setLayout(new GridLayout(5,1));

                //aggiunta delle sezioni al cliente
                centralPanel.add(new labelAccount("nome",rider.getNome(),centralPanel,rider,url,user,password));
                centralPanel.add(new labelAccount("mail",rider.getEmail(),centralPanel,rider,url,user,password));
                centralPanel.add(new labelAccount("password",rider.getPassword(),centralPanel,rider,url,user,password));
                centralPanel.add(new labelAccount("telefono",rider.getTelefono(),centralPanel,rider,url,user,password));
                centralPanel.add(new labelAccount("saldo",String.valueOf(rider.getSaldo()),centralPanel,rider,url,user,password));

                //refresh frame
                frame.repaint();
                frame.revalidate();
            }
        });
        deliveryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                centralPanel.removeAll();
                ArrayList<String> info = new ArrayList<>();
                info = rider.getDeliveryFromDB(url,user,password);  //controllo se ci sono consegne che devono essere svolte dal rider

                if(info==null) {
                    JLabel label = new JLabel("nessuna consegna da svolgere");
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setVerticalAlignment(SwingConstants.CENTER);
                    label.setFont(new Font("Arial",Font.PLAIN,25));
                    centralPanel.setBackground(Color.white);

                    centralPanel.add(label);
                }
                else{
                    JPanel datasPanel = new JPanel();
                    datasPanel.setLayout(new GridLayout(3,2));
                    ArrayList<String> deliveryInfo = new ArrayList<>();

                    //show info
                    datasPanel.add(new DeliveryLabel("","conto",info.get(0)));
                    datasPanel.add(new DeliveryLabel("","nome ristorante",info.get(5)));
                    datasPanel.add(new DeliveryLabel("","indirizzo cliente",info.get(1)));
                    datasPanel.add(new DeliveryLabel("","indirizzo ristorante",info.get(3)));
                    datasPanel.add(new DeliveryLabel("","città cliente",info.get(2)));
                    datasPanel.add(new DeliveryLabel("","città ristorante",info.get(4)));
                    JPanel btnPanel = new JPanel();

                    JButton customerBtn = new JButton("raggiungi il cliente");
                    JButton restaurantBtn = new JButton("raggiungi ristorante");
                    JButton finalBtn = new JButton("consegna completata");
                    btnPanel.setBackground(Color.white);

                    //settaggio colore e dimensione bottoni
                    customerBtn.setBackground(new Color(234, 92, 4));
                    restaurantBtn.setBackground(customerBtn.getBackground());
                    finalBtn.setBackground(customerBtn.getBackground());

                    customerBtn.setForeground(Color.white);
                    restaurantBtn.setForeground(customerBtn.getForeground());
                    finalBtn.setForeground(customerBtn.getForeground());

                    customerBtn.setPreferredSize(new Dimension(180,50));
                    restaurantBtn.setPreferredSize(customerBtn.getPreferredSize());
                    finalBtn.setPreferredSize(customerBtn.getPreferredSize());

                    btnPanel.add(restaurantBtn);
                    btnPanel.add(customerBtn);
                    btnPanel.add(finalBtn);

                    //settaggio proporzioni per i pannelli
                    centralPanel.setLayout(null);
                    datasPanel.setBounds(0,0,centralWidth,(int)(centralPanel.getHeight()*0.7));
                    btnPanel.setBounds(0,datasPanel.getHeight(),centralWidth,(int)(centralPanel.getWidth()*0.3));

                    centralPanel.add(datasPanel);
                    centralPanel.add(btnPanel);

                    //aggiunta delle info delivery nell'array
                    deliveryInfo.add(info.get(1));
                    deliveryInfo.add(info.get(2));
                    deliveryInfo.add(info.get(3));
                    deliveryInfo.add(info.get(4));

                    //azioni per ogni bottone
                    customerBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            rider.gotoDestination(deliveryInfo.get(0),deliveryInfo.get(1));

                            //eliminazione dei prodotti dalla tabella del ristorante
                            rider.deleteOrder(url,user,password);
                        }
                    });

                    restaurantBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            rider.gotoDestination(deliveryInfo.get(2),deliveryInfo.get(3));
                        }
                    });

                    deliveryBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            rider.deleteDelivery(url,user,password);

                            JOptionPane.showMessageDialog(datasPanel,"consegna completata con successo");
                        }
                    });
                }

                //refresh frame
                frame.revalidate();
                frame.repaint();
            }
        });
    }
}
