import javax.swing.*;
import java.awt.*;

public class EmployeeDashboard extends JPanel {
    public EmployeeDashboard(MainFrame mainFrame) {
        setLayout(new BorderLayout());

        // Header
        JLabel heading = new JLabel("Employee Dashboard", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setForeground(new Color(0, 102, 204));
        add(heading, BorderLayout.NORTH);

        // Example content for Employee
        JTextArea employeeContent = new JTextArea("Employee specific content goes here.\nManage orders, tasks, etc.");
        employeeContent.setEditable(false);
        add(employeeContent, BorderLayout.CENTER);

        // Button to log out or navigate
        JButton logoutButton = new JButton("Log out");
        logoutButton.addActionListener(e -> mainFrame.showPage("Login"));
        add(logoutButton, BorderLayout.SOUTH);
    }
}
