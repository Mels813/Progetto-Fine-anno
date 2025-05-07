import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeliveryLoginPage {
    public DeliveryLoginPage(JFrame frame) {
        /*JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700); // Increased frame size/* */
        frame.setLayout(new BorderLayout(20, 20)); // Add more spacing around the edges

        JPanel panel = new JPanel() {
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
        panel.setLayout(new GridBagLayout()); // Use GridBagLayout for alignment
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Add more spacing between components

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Increased font size
        userLabel.setForeground(Color.BLACK); // Set text color
        JTextField userText = new JTextField(15); // Increased text field size
        userText.setFont(new Font("Arial", Font.PLAIN, 24)); // Increased font size
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Increased font size
        passLabel.setForeground(Color.BLACK); // Set text color
        JPasswordField passText = new JPasswordField(15); // Increased text field size
        passText.setFont(new Font("Arial", Font.PLAIN, 24)); // Increased font size
        JButton loginButton = new JButton("Login");
        JButton returnButton = new JButton("Return");
        JButton signUpButton = new JButton("Sign Up");

        // Add Username label and text field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(userLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(userText, gbc);

        // Add Password label and text field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(passLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(passText, gbc);

        // Add buttons side by side
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0)); // Increased spacing between buttons
        buttonPanel.setOpaque(false); // Make button panel transparent
        loginButton.setPreferredSize(new Dimension(120, 40)); // Larger button dimensions
        loginButton.setFont(new Font("Arial", Font.BOLD, 20)); // Increased font size
        returnButton.setPreferredSize(new Dimension(120, 40)); // Larger button dimensions
        returnButton.setFont(new Font("Arial", Font.BOLD, 20)); // Increased font size
        buttonPanel.add(loginButton);
        buttonPanel.add(returnButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        // Add Sign Up button below Login and Exit buttons
        signUpButton.setPreferredSize(new Dimension(120, 40)); // Larger button dimensions
        signUpButton.setFont(new Font("Arial", Font.BOLD, 20)); // Increased font size
        gbc.gridy = 3;
        panel.add(signUpButton, gbc);

        frame.add(panel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passText.getPassword());
                if (username.equals("admin") && password.equals("password")) {
                    JOptionPane.showMessageDialog(frame, "Login Successful!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
                }
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the home page when the button is clicked
                frame.getContentPane().removeAll();
                new HomePage(frame); // Assuming you have a HomePage class
                frame.revalidate();
                frame.repaint();
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the sign-up page when the button is clicked
                frame.getContentPane().removeAll();
                new DeliverySignUp(frame); // Assuming you have a SignUpPage class
                frame.revalidate();
                frame.repaint();
            }
        });

        frame.setVisible(true);
    }
}