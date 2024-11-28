import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JPanel {
    public LoginPage(MainFrame mainFrame, RoleSys roleSys) {
        setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(Color.WHITE);

        ImageIcon logoImage = new ImageIcon("path_to_logo_image_here");
        if (logoImage.getImageLoadStatus() != MediaTracker.COMPLETE) {
            logoImage = new ImageIcon("default_logo_path_here");
        }
        JLabel logoLabel = new JLabel(logoImage);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(logoLabel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Login to Your Account", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(0, 102, 204));
        rightPanel.add(title);

        JComboBox<String> roleDropdown = new JComboBox<>(new String[]{"Customer", "Admin", "Employee"});
        roleDropdown.setBorder(BorderFactory.createTitledBorder("Select Role"));
        rightPanel.add(roleDropdown);

        JTextField emailField = new JTextField();
        emailField.setBorder(BorderFactory.createTitledBorder("Email"));
        rightPanel.add(emailField);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        rightPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setBackground(new Color(0, 153, 51));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                String selectedRole = roleDropdown.getSelectedItem().toString(); // Correctly get the role from dropdown


                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginPage.this, "Please enter both email and password.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String userRole = mainFrame.checkLogin(email, password, selectedRole);

                // Call the RoleSys method 
                roleSys.role_login(userRole, mainFrame);
            }
        });
        rightPanel.add(loginButton);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> mainFrame.showPage("Home"));
        rightPanel.add(backButton);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }
}

