import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerHome extends JPanel {

    private JPanel cardPanel;  // Panel for switching between different pages (cards)
    private CardLayout cardLayout;  // Layout manager to handle page transitions

    public CustomerHome(MainFrame mainFrame) {
        // Set the layout for the main panel
        setLayout(new BorderLayout());  // Use BorderLayout for top-bottom structure

        // Create a panel to hold the top buttons (Dashboard, Packages, Calculator)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 4, 10, 10));  // 1 row, 4 columns, with 10px gap between buttons

        // Create the top panel buttons
        JButton dashboardButton = new JButton("Dashboard");
        JButton packagesButton = new JButton("Packages");
        JButton calculatorButton = new JButton("Calculator");

        // Add action listeners to the buttons
        dashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Dashboard");
            }
        });
        packagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Packages");
            }
        });
        calculatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Calculator");
            }
        });

        // Add the buttons to the top panel
        topPanel.add(dashboardButton);
        topPanel.add(packagesButton);
        topPanel.add(calculatorButton);

        // Create a CardLayout to switch between different panels
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);  // This will hold all the "pages"

        // Create the individual panels for Dashboard, Packages, and Calculator
        JPanel dashboardPanel = createDashboardPanel();
        JPanel packagesPanel = createPackagesPanel();
        JPanel calculatorPanel = createCalculatorPanel();

        // Add all panels to the cardPanel
        cardPanel.add(dashboardPanel, "Dashboard");
        cardPanel.add(packagesPanel, "Packages");
        cardPanel.add(calculatorPanel, "Calculator");

        // Add the top panel and the card panel to the main panel
        add(topPanel, BorderLayout.NORTH);  // Add topPanel to the North section
        add(cardPanel, BorderLayout.CENTER);  // Add cardPanel to the Center section
    }

    // Method to create the Dashboard page
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);  // Example background color
        panel.add(new JLabel("Welcome to the Dashboard"));
        return panel;
    }

    // Method to create the Packages page
    private JPanel createPackagesPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK);  // Example background color
        panel.add(new JLabel("Here are your packages"));
        return panel;
    }

    // Method to create the Calculator page
    private JPanel createCalculatorPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);  // Example background color
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  // Arrange vertically

        // First horizontal panel
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(250, 50));  // Set width and height
        topPanel.setBackground(Color.LIGHT_GRAY);  // Example background color
        topPanel.add(new JLabel("Top Panel"));

        // Second horizontal panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(250, 50));  // Set width and height
        bottomPanel.setBackground(Color.GRAY);  // Example background color
        bottomPanel.add(new JLabel("Bottom Panel"));

        // Add the panels to the main calculator panel with a small gap between them
        panel.add(topPanel);
        panel.add(Box.createVerticalStrut(10));  // Add some space between the panels
        panel.add(bottomPanel);

        return panel;
    }

    public static void main(String[] args) {
        // Create a frame to test the layout
        JFrame frame = new JFrame("Customer Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);  // Set the frame size
        frame.add(new CustomerHome(new MainFrame()));  // Add CustomerHome panel
        frame.setVisible(true);  // Make the frame visible
    }
} 
