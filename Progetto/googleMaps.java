import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;


public class googleMaps{
    public static void main(String[] args){
        JFrame frame = new JFrame("google Maps");

        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("click here");
        JTextField street = new JTextField();
        JTextField nField = new JTextField();
        JTextField city = new JTextField();

        JPanel streetPanel = new JPanel();
        streetPanel.add(new JLabel("street: "));
        streetPanel.add(street);
        street.setColumns(15);

        JPanel nPanel = new JPanel();
        nPanel.add(new JLabel("numero civico: "));
        nPanel.add(nField);
        nField.setColumns(5);

        JPanel cityPanel = new JPanel();
        cityPanel.add(new JLabel("city: "));
        cityPanel.add(city);
        city.setColumns(15);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);
        button.setPreferredSize(new Dimension(100, 50));
        button.setForeground(Color.white);
        button.setBackground(new Color(252,138,15));

        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

                try{
                    String address = street.getText() + " " + nField.getText() + " " + city.getText();
                    String encodedAddress = address.replace(" ", "+");
                    String apiUrl = "https://www.google.com/maps/search/?api=1&query="+encodedAddress;

                    Desktop desktop = Desktop.getDesktop();
                    desktop.browse(new URI(apiUrl));
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        frame.setLayout(new GridLayout(4,1));
        frame.add(streetPanel);
        frame.add(nPanel);
        frame.add(cityPanel);
        frame.add(buttonPanel);
        frame.setVisible(true);
    }
}