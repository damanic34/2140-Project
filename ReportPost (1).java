import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;

public class ReportPost {

    // Attributes
    private String noticeId;
    private String customer;
    private String date;
    private String AdminEmployee;//admin to receive message
    private Map<String, java.util.List<String>> AdminMessages; // Map to store messages for Admin employee
    

    public ReportPost(String noticeId, String customer, String date, String AdminEmployee) {
        this.noticeId = noticeId;
        this.customer = customer;
        this.date = date;
        this.AdminEmployee = AdminEmployee;
        this.AdminMessages = new HashMap<>();
        // Initialize customer messages map  
        AdminMessages.put(AdminEmployee, new ArrayList<>());
    
        
    }

    public void createAndShowGUI() {
        // Main frame
        JFrame frame = new JFrame("Report Post");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Panel for notice creation
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        // Title input
        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();
        panel.add(titleLabel);
        panel.add(titleField);

        // Content input
        JLabel contentLabel = new JLabel("Content:");
        JTextArea contentArea = new JTextArea(5, 20);
        panel.add(contentLabel);
        panel.add(new JScrollPane(contentArea));


        // Buttons
        JButton sendButton1 = new JButton("Send Notice");
        panel.add(sendButton1);


        // Add the panel to the frame
        frame.add(panel, BorderLayout.CENTER);

        // Event handling for sending notices
        sendButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String content = contentArea.getText();

                if (title.isEmpty() || content.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Title and Content cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Send the message to admin employee by updating internal data structures  
                sendMessageToAdmin(AdminEmployee, title, content);
                

                JOptionPane.showMessageDialog(frame, "Notice sent successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
               }
        });

        // Customer view panel
        JFrame customerViewFrame = new JFrame("Sent Messages!");
        customerViewFrame.setSize(400, 300);

        JPanel customerPanel = new JPanel(new BorderLayout());
        JLabel AdminLabel = new JLabel("Admin Received:");
        JTextField AdminTextField = new JTextField(AdminEmployee); 
        AdminTextField.setEditable(false);
        JTextArea customerMessageArea = new JTextArea();
        customerMessageArea.setEditable(false);

        JButton viewMessagesButton = new JButton("View Messages");
        customerPanel.add(AdminLabel, BorderLayout.NORTH);
        customerPanel.add(AdminTextField, BorderLayout.CENTER);
        customerPanel.add(viewMessagesButton, BorderLayout.SOUTH);

        customerViewFrame.add(new JScrollPane(customerMessageArea), BorderLayout.CENTER);
        customerViewFrame.add(customerPanel, BorderLayout.NORTH);

        viewMessagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List<String> messages = AdminMessages.get(AdminEmployee);
                customerMessageArea.setText(""); // Clear previous messages
                if (messages != null && !messages.isEmpty()) {
                    for (String message : messages) {
                        customerMessageArea.append(message + "\n\n");
                    }
                } else {
                    customerMessageArea.setText("No messages for this customer.");
                }
            }
        });

        // Show the frame
        frame.setVisible(true);
    }

    // Method to handle sending messages by updating internal data structures
    private void sendMessageToAdmin (String Admin, String title, String content) {
        // Add the message to the customerMessages map (received messages)
        List<String> messages = AdminMessages.get(Admin);
        if (messages != null) {
            messages.add("Title: " + title + "\nContent: " + content);
        }
    }

    public static void main(String[] args) {
        // Start the application
        SwingUtilities.invokeLater(() -> {
            ReportPost reportPost = new ReportPost ("Notice001", "Employee001", "2024-11-28", "Admin001");
            reportPost.createAndShowGUI();
        });
    }
}