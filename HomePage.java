import java.awt.*;
import javax.swing.*;

public class HomePage extends JPanel{
    JPanel northPanel;
    JPanel panel1;
    JPanel totalPanel;
    JPanel btnPanel;

    HomePage(JFrame frame){

        // Pannel North
        northPanel = new JPanel();
        panel1 = new JPanel();
        totalPanel = new JPanel();

        totalPanel.setBackground(new Color(255, 132, 0));
        totalPanel.setLayout(new BorderLayout());

        northPanel.setLayout(new BorderLayout());
        northPanel.setBackground(new Color(255, 132, 0));

        panel1.setLayout(new GridLayout(2, 1));
        panel1.setBackground(new Color(255, 132, 0));
        JLabel loginLabel = new JLabel("Home Page", SwingConstants.CENTER);
        loginLabel.setForeground(Color.BLACK);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 60));

        JLabel nameLabel = new JLabel("SanPaolo GO", SwingConstants.CENTER);
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(new Font("TimesRoman", Font.PLAIN, 100));

        panel1.add(nameLabel);
        // Gradient effect from orange to white
        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                Color startColor = new Color(255, 132, 0);
                Color endColor = Color.WHITE;
                GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, height, endColor);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, width, height);
            }
        };
        gradientPanel.setLayout(new BorderLayout());
        gradientPanel.add(loginLabel, BorderLayout.CENTER);
        panel1.add(gradientPanel);
        northPanel.add(panel1);

        // Adding buttons with gradient backgrounds
        btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(3, 1, 10, 10)); // One button per row with spacing
        btnPanel.setBackground(new Color(255, 255, 255));

        // Gradient Button Class
        class GradientButton extends JButton {
            private Color startColor;
            private Color endColor;

            public GradientButton(String text, Color startColor, Color endColor) {
                super(text);
                this.startColor = startColor;
                this.endColor = endColor;
                setContentAreaFilled(false);
                setFocusPainted(false);
                setFont(new Font("Arial", Font.PLAIN, 20));
                setHorizontalAlignment(SwingConstants.CENTER);
                setVerticalAlignment(SwingConstants.CENTER);
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                int width = getWidth();
                int height = getHeight();
                GradientPaint gradient = new GradientPaint(0, 0, startColor, width, height, endColor);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, width, height);
                g2d.dispose();
                super.paintComponent(g);
            }
        }

        // Ristorante Button
        JButton ristoranteButton = new GradientButton("Ristorante", new Color(255, 132, 0), Color.WHITE);
        ristoranteButton.setBorder(null);
        btnPanel.add(ristoranteButton);

        // Delivery Button
        JButton deliveryButton = new GradientButton("Delivery", new Color(255, 132, 0), Color.WHITE);
        deliveryButton.setBorder(null);
        btnPanel.add(deliveryButton);

        // User Button
        JButton userButton = new GradientButton("Client", new Color(255, 132, 0), Color.WHITE);
        userButton.setBorder(null);
        btnPanel.add(userButton);

        ristoranteButton.addActionListener(e -> {
            // Open the delivery page when the button is clicked
            frame.getContentPane().removeAll(); // Remove all components from the frame
            new LoginPage(frame); // Assuming DeliveryPage is another class for the delivery screen
            frame.revalidate();
            frame.repaint();
        });

        deliveryButton.addActionListener(e -> {
            // Open the login page when the button is clicked
            frame.getContentPane().removeAll(); // Remove all components from the frame
            new DeliveryLoginPage(frame);
            frame.revalidate();
            frame.repaint();
        });

        userButton.addActionListener(e -> {
            // Open the login page when the button is clicked
            frame.getContentPane().removeAll(); // Remove all components from the frame
            new LoginPage(frame);
            frame.revalidate();
            frame.repaint();
        });

        // Add padding around the buttons
        btnPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // Top, Left, Bottom, Right
        

        totalPanel.add(northPanel, BorderLayout.NORTH);
        totalPanel.add(btnPanel, BorderLayout.CENTER);
        frame.add(totalPanel);
    }
}


