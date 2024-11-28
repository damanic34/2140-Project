import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {
    public HomePage(MainFrame mainFrame) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Add a company logo or image (replace with actual path)
        JLabel logo = new JLabel(new ImageIcon("IMG_3227.jpeg"));
        add(logo, BorderLayout.NORTH);

        // Add buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);

        // Home Button
        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(e -> mainFrame.showPage("Home"));
        homeButton.setBackground(Color.BLUE); // Change button color to blue
        buttonPanel.add(homeButton);

        // Register Button
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> mainFrame.showPage("Register"));
        registerButton.setBackground(Color.BLUE); // Change button color to blue
        buttonPanel.add(registerButton);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> mainFrame.showPage("Login"));
        loginButton.setBackground(Color.BLUE); // Change button color to blue
        buttonPanel.add(loginButton);

        // Delivery Info Button
        JButton deliveryInfoButton = new JButton("Delivery Info");
        deliveryInfoButton.addActionListener(e -> mainFrame.showPage("Delivery"));
        deliveryInfoButton.setBackground(Color.BLUE); // Change button color to blue
        buttonPanel.add(deliveryInfoButton);

        // Shipping Rates Button
        JButton shippingRatesInfoButton = new JButton("Shipping Rates");
        shippingRatesInfoButton.addActionListener(e -> mainFrame.showPage("Shipping Rates"));
        shippingRatesInfoButton.setBackground(Color.BLUE); // Change button color to blue
        buttonPanel.add(shippingRatesInfoButton);

        // More Button
        JButton moreInfoButton = new JButton("More ");
        moreInfoButton.addActionListener(e -> mainFrame.showPage("More "));
        moreInfoButton.setBackground(Color.BLUE); // Change button color to blue
        buttonPanel.add(moreInfoButton);

        add(buttonPanel, BorderLayout.NORTH);

        // Add background image (replace with actual path)
        JLabel backgroundImage = new JLabel(new ImageIcon("IMG_3231.jpeg"));
        // JLabel backgroundImage = new ImageIcon("IMG_3231.jpeg").getImage();

        add(backgroundImage, BorderLayout.CENTER);
    }
    
        @Override
        protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Always call super.paintComponent first.
        ImageIcon background = new ImageIcon("IMG_3231.jpeg");
        g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}


