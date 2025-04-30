import java.awt.*;
import javax.swing.*;

public class HomePage extends JFrame {
    JFrame frame;
    JPanel northPanel;
    JPanel panel1;
    JPanel totalPanel;
    JPanel btnPanel;

    HomePage() {
        frame = new JFrame("Home Page");

        // Pannel North
        northPanel = new JPanel();
        panel1 = new JPanel();
        totalPanel = new JPanel();

        totalPanel.setBackground(new Color(255, 132, 0));
        totalPanel.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(255, 132, 0));

        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(new Font("Serif", Font.ITALIC, 20));
        fileMenu.setForeground(Color.BLACK);

        JMenu helpMenu = new JMenu("Home");
        helpMenu.setFont(new Font("Serif", Font.ITALIC, 20));
        helpMenu.setForeground(Color.BLACK);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setFont(new Font("Arial", Font.PLAIN, 15));
        exitItem.setForeground(Color.BLACK);
        fileMenu.addActionListener(e -> System.exit(0));

        JMenuItem aboutItem = new JMenuItem("Version");
        aboutItem.setFont(new Font("Arial", Font.PLAIN, 15));
        aboutItem.setForeground(Color.BLACK);
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "HomePage Application v1.0"));

        fileMenu.add(exitItem);
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        frame.setJMenuBar(menuBar);

        menuBar.setBorder(BorderFactory.createEmptyBorder());

        northPanel.setLayout(new BorderLayout());
        northPanel.setBackground(new Color(255, 132, 0));

        panel1.setLayout(new GridLayout(2, 1));
        panel1.setBackground(new Color(255, 132, 0));
        JLabel loginLabel = new JLabel("Home Page", SwingConstants.CENTER);
        loginLabel.setForeground(Color.BLACK);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 40));

        JLabel nameLabel = new JLabel("SanPaolo GO", SwingConstants.CENTER);
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(new Font("TimesRoman", Font.PLAIN, 18));

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
        btnPanel.add(ristoranteButton);

        // Delivery Button
        JButton deliveryButton = new GradientButton("Delivery", new Color(255, 132, 0), Color.WHITE);
        btnPanel.add(deliveryButton);

        // User Button
        JButton userButton = new GradientButton("Login", new Color(255, 132, 0), Color.WHITE);
        btnPanel.add(userButton);

        userButton.addActionListener(e -> {
            // Open the login page when the button is clicked
            new LoginPage();
            frame.dispose(); // Close the home page
        });

        // Add padding around the buttons
        btnPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // Top, Left, Bottom, Right
        

        totalPanel.add(northPanel, BorderLayout.NORTH);
        totalPanel.add(btnPanel, BorderLayout.CENTER);
        frame.add(totalPanel);
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}


