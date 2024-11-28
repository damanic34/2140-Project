import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JPanel {
    public AdminDashboard(MainFrame mainFrame) {
        setLayout(new BorderLayout());

        // Header
        JLabel heading = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setForeground(new Color(0, 102, 204));
        add(heading, BorderLayout.NORTH);

        // Example content for Admin
        JTextArea adminContent = new JTextArea("Admin specific content goes here.\nManage users, settings, etc.");
        adminContent.setEditable(false);
        add(adminContent, BorderLayout.CENTER);

        // Button to log out or navigate
        JButton logoutButton = new JButton("Log out");
        logoutButton.addActionListener(e -> mainFrame.showPage("Login"));
        add(logoutButton, BorderLayout.SOUTH);
    }
}
