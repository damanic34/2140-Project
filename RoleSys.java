import javax.swing.*;

public class RoleSys {

    // Method to handle role-based login and navigation
    public void role_login(String userRole, MainFrame mainFrame) {
        if (userRole != null) {
            switch (userRole) {
                case "Customer":
                    mainFrame.showPage("CustomerDashboard");
                    break;
                case "Admin":
                    mainFrame.showPage("AdminDashboard");
                    break;
                case "Employee":
                    mainFrame.showPage("EmployeeDashboard");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid role for this account.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid email, password, or role!", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}