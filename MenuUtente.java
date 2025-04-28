import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuUtente extends JFrame {
    private JPanel menuLaterale;
    private boolean menuVisibile = false;
    private JButton btnUtente = new JButton();

    public MenuUtente(){

        setTitle("Menu Utente");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        ImageIcon icon = new ImageIcon("user.png");
        Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);

        btnUtente = new JButton(new ImageIcon(img));
        btnUtente.setBounds(790, 10, 80, 80);
        
        menuLaterale = new JPanel();
        menuLaterale.setLayout(new GridLayout(4, 1));
        menuLaterale.setBackground(Color.ORANGE);
        menuLaterale.setBounds(700, 0, 200, 600);
        menuLaterale.setVisible(false);

        JButton btnProfilo = new JButton("👤 Area Personale");
        JButton btnCarrello = new JButton("🛒 Carrello");
        JButton btnConsegne = new JButton("🚚 Consegne");
        JButton btnRistoranti = new JButton("🍽️ Ristoranti");

        btnProfilo.setIcon(new ImageIcon(new ImageIcon("user.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        btnProfilo.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnProfilo.setHorizontalAlignment(SwingConstants.LEFT);
        btnProfilo.setIconTextGap(10);

        btnCarrello.setIcon(new ImageIcon(new ImageIcon("shopping-cart-add.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        btnCarrello.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnCarrello.setHorizontalAlignment(SwingConstants.LEFT);
        btnCarrello.setIconTextGap(10);

        btnConsegne.setIcon(new ImageIcon(new ImageIcon("delivery-man.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        btnConsegne.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnConsegne.setHorizontalAlignment(SwingConstants.LEFT);
        btnConsegne.setIconTextGap(10);

        btnRistoranti.setIcon(new ImageIcon(new ImageIcon("restaurant.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        btnRistoranti.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnRistoranti.setHorizontalAlignment(SwingConstants.LEFT);
        btnRistoranti.setIconTextGap(10);

        menuLaterale.add(btnProfilo);
        menuLaterale.add(btnCarrello);
        menuLaterale.add(btnConsegne);
        menuLaterale.add(btnRistoranti);

        add(btnUtente);
        add(menuLaterale);
        btnUtente.addActionListener(e -> {
            menuVisibile = !menuVisibile;
            menuLaterale.setVisible(menuVisibile);
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        new MenuUtente();
    }
}
