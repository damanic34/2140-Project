import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private Map<String, User> userDatabase = new HashMap<>(); // Use userDatabase to store user data


    public MainFrame() {
        setTitle("Shipping Company System");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Card layout for page switching
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        // Add all pages to the cardPanel
        cardPanel.add(new HomePage(this), "Home");
        cardPanel.add(new RegistrationPage(this), "Register");
        cardPanel.add(new LoginPage(this), "Login");
        // cardPanel.add(new DeliveryPage(), "Delivery");  // Add DeliveryPage 
        cardPanel.add(new ShippingRatesPage(), "Shipping Rates");  // Add ShippingRatesPage
        // Add the dashboards to the card panel
        cardPanel.add(new AdminDashboard(this), "AdminDashboard");
        cardPanel.add(new EmployeeDashboard(this), "EmployeeDashboard");
        cardPanel.add(new CustomerHome(this), "CustomerDashboard");
        
        // Show Home Page initially
        cardLayout.show(cardPanel, "Home");

        
        add(cardPanel);

        // More Button Menu (dropdown)
        JMenu moreMenu = new JMenu("More");
        JMenuItem faqsItem = new JMenuItem("FAQs");
        JMenuItem termsItem = new JMenuItem("Terms & Conditions");

        // FAQs item listener
        faqsItem.addActionListener(e -> {
            FAQsPage faqPage = new FAQsPage();
            faqPage.setVisible(true);
        });

        // Terms and Conditions item listener
        termsItem.addActionListener(e -> {
            TermsAndConditionsPage termsPage = new TermsAndConditionsPage();
            termsPage.setVisible(true);
        });

        // Add items to the "More" menu
        moreMenu.add(faqsItem);
        moreMenu.add(termsItem);

        // Add the "More" menu to the menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(moreMenu);

        // Set the menu bar for the frame
        setJMenuBar(menuBar);   
    }


    

     // Method to register a user in the userDatabase
    public void registerUser(String email, String password, String role) {
        userDatabase.put(email, new User(email, password, role)); // Add user to the database
        // Write user data to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt", true))) {
            // Format: email,password,role
            writer.write(email + "," + password + "," + role);
            writer.newLine();  // Add new line after each user
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving user data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to check login credentials
    public String checkLogin(String email, String password, String role) {
        try (BufferedReader reader = new BufferedReader(new FileReader("user.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 3) {
                    String storedEmail = userData[0];
                    String storedPassword = userData[1];
                    String storedRole = userData[2];

                        if (storedEmail.equals(email) && storedPassword.equals(password) && storedRole.equalsIgnoreCase(role)) {
                            return storedRole; // Return the role
                        }
                    
                    
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading user data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null; // No matching user found
    }
    // Method to get the role of a user based on email
    public String getUserRole(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader("user.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 3) {
                    String storedEmail = userData[0];
                    String storedRole = userData[2];

                    if (storedEmail.equals(email)) {
                        return storedRole; // Return the role if email matches
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading user data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null; // Return null if no role found
    }

    
    public void showPage(String pageName) {
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), pageName);
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}



class RegistrationPage extends JPanel {

    //private JComboBox<String> branchComboBox;

    public RegistrationPage(MainFrame mainFrame) {
        // Set the layout for the entire page to BorderLayouts
        setLayout(new BorderLayout());

        // Left Panel (for the logo)
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(Color.WHITE);

        // Add logo 
        JLabel logoLabel = new JLabel("IMG_3227.jpeg", SwingConstants.CENTER);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        logoLabel.setForeground(new Color(0, 102, 204));
        leftPanel.add(logoLabel, BorderLayout.CENTER);

        // Right Panel (for the registration form)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Title
        JLabel title = new JLabel("Create Your Account", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(0, 102, 204));
        rightPanel.add(title);

        // Dropdown for role selection
        JComboBox<String> roleDropdown = new JComboBox<>(new String[]{"Customer", "Admin", "Employee"});
        roleDropdown.setBorder(BorderFactory.createTitledBorder("Select Role"));
        rightPanel.add(roleDropdown);

        // First Name
        JPanel firstNamePanel = new JPanel();
        firstNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel firstNameLabel = new JLabel("First Name");
        JTextField firstNameField = new JTextField(20);
        firstNamePanel.add(firstNameLabel);
        firstNamePanel.add(firstNameField);
        rightPanel.add(firstNamePanel);

        // Last Name
        JPanel lastNamePanel = new JPanel();
        lastNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lastNameLabel = new JLabel("Last Name");
        JTextField lastNameField = new JTextField(20);
        lastNamePanel.add(lastNameLabel);
        lastNamePanel.add(lastNameField);
        rightPanel.add(lastNamePanel);

        // Email
        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel emailLabel = new JLabel("Email ");
        JTextField emailField = new JTextField(20);
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);
        rightPanel.add(emailPanel);

        // Password
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel passwordLabel = new JLabel("Password ");
        JPasswordField passwordField = new JPasswordField(20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        rightPanel.add(passwordPanel);

        // TRN
        JPanel trnPanel = new JPanel();
        trnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel trnLabel = new JLabel("TRN (Taxpayer Registration Number) ");
        JTextField trnField = new JTextField(20);
        trnPanel.add(trnLabel);
        trnPanel.add(trnField);
        rightPanel.add(trnPanel);

        // Phone Number
        JPanel phonePanel = new JPanel();
        phonePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel phoneLabel = new JLabel("Phone Number ");
        JTextField phoneField = new JTextField(20);
        phonePanel.add(phoneLabel);
        phonePanel.add(phoneField);
        rightPanel.add(phonePanel);

        
        // Terms and Conditions
        JLabel termsLabel = new JLabel("By creating an account, you agree to Terms and Conditions and Privacy Policy.", SwingConstants.CENTER);
        termsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        termsLabel.setForeground(Color.GRAY);
        rightPanel.add(termsLabel);

        
        // Sign Up Button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 14));
        signUpButton.setBackground(new Color(0, 153, 51));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String role = roleDropdown.getSelectedItem().toString();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String trn = trnField.getText();
                String phone = phoneField.getText();

                // Validate fields (simplified example)
                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || trn.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(RegistrationPage.this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Store user info in the database
                mainFrame.registerUser(email, password, role);

                // Show confirmation message
                String message = String.format("Account Created!\n\nName: %s %s\nEmail: %s\nTRN: %s\nPhone: ",
                        firstName, lastName, email, trn, phone);

                JOptionPane.showMessageDialog(RegistrationPage.this, message, "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
                mainFrame.showPage("Home"); // Redirect to Home page after registration
            }
        });
        rightPanel.add(signUpButton);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> mainFrame.showPage("Home"));
        rightPanel.add(backButton);

        // Add the panels to the main layout
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }
}











class ShippingRatesPage extends JPanel {
    public ShippingRatesPage() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Title for ShippingRates Page
        JLabel titleLabel = new JLabel("Shipping Rates ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel, BorderLayout.CENTER);

        // Content area with ShippingRates 
        JTextArea ShippingRatesinfo = new JTextArea(
                "Weight \t\t Fees(JMD).\n\n" +
                "2. Shipping rates are based on weight.\n\n" +
                "3. All packages are tracked and delivered to your designated address.\n\n" +
                "4. You will be notified when your package is out for delivery.\n\n" +
                "5. Shipping delays may occur due to customs clearance or weather conditions.\n"
        );
        ShippingRatesinfo.setFont(new Font("Arial", Font.PLAIN, 16));
        ShippingRatesinfo.setEditable(false);  // Make it read-only
        ShippingRatesinfo.setBackground(Color.WHITE);
        add(new JScrollPane(ShippingRatesinfo), BorderLayout.CENTER);  // Add scrollable text area

        // Create a JLabel for the back link
        JLabel backLink = new JLabel("<html><u>Back to Home</u></html>");
        backLink.setForeground(Color.BLUE);  // Set color to blue to resemble a link
        backLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));  // Change cursor to hand

        // Add mouse listener for clickable behavior
        backLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Go back to Home Page
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(ShippingRatesPage.this);
                mainFrame.showPage("Home");
            }
        });

        // Add the back link to the bottom of the panel
        JPanel backPanel = new JPanel();
        backPanel.setBackground(Color.WHITE);
        backPanel.add(backLink);  // Add the back link
        add(backPanel, BorderLayout.SOUTH);  // Add to the bottom of the page
    }
}



