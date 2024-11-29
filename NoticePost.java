import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoticePost {

    // Attributes
    private String noticeId;
    private String employee;
    private String date;
    private List<String> customerReceiver; // List of customers selected to receive the notice
    private List<String> registeredCustomers;
    private Map<String, List<String>> customerMessages; // Map to store messages for each customer
    private Map<String, List<String>> sentMessages; // Map to store sent notices for each customer
    private Map<String, List<String>> employeeInbox; // Map to store messages for all employees (inbox)

    public NoticePost(String noticeId, String employee, String date) {
        this.noticeId = noticeId;
        this.employee = employee;
        this.date = date;
        this.customerReceiver = new ArrayList<>();
        this.registeredCustomers = new ArrayList<>();
        this.customerMessages = new HashMap<>();
        this.sentMessages = new HashMap<>();
        this.employeeInbox = new HashMap<>();

        // Add registered customers
        registeredCustomers.add("User001");
        registeredCustomers.add("User002");
        registeredCustomers.add("User003");
        registeredCustomers.add("User004");

        // Initialize customer messages and sent messages map
        for (String customer : registeredCustomers) {
            customerMessages.put(customer, new ArrayList<>());
            sentMessages.put(customer, new ArrayList<>());
        }

        // Initialize employee inbox (all employees)
        employeeInbox.put("Employee001", new ArrayList<>());
        employeeInbox.put("Employee002", new ArrayList<>());
        employeeInbox.put("Employee003", new ArrayList<>());
    }

    public void createAndShowGUI() {
        // Main frame
        JFrame frame = new JFrame("Notice Post");
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

        // Customer selection dropdown
        JLabel customerLabel = new JLabel("Select Customer:");
        JComboBox<String> customerDropdown = new JComboBox<>(registeredCustomers.toArray(new String[0]));
        panel.add(customerLabel);
        panel.add(customerDropdown);

        // Buttons
        JButton addCustomerButton = new JButton("Add Customer");
        JButton sendButton = new JButton("Send Notice");
        panel.add(addCustomerButton);
        panel.add(sendButton);

        // Text area to display added customers
        JTextArea customerListArea = new JTextArea(5, 20);
        customerListArea.setEditable(false);
        JScrollPane customerScroll = new JScrollPane(customerListArea);
        frame.add(customerScroll, BorderLayout.EAST);

        // Add the panel to the frame
        frame.add(panel, BorderLayout.CENTER);

        // Event handling for adding customers
        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCustomer = (String) customerDropdown.getSelectedItem();
                if (!customerReceiver.contains(selectedCustomer)) {
                    customerReceiver.add(selectedCustomer);
                    customerListArea.append(selectedCustomer + "\n");
                } else {
                    JOptionPane.showMessageDialog(frame, "Customer already added!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Event handling for sending notices
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String content = contentArea.getText();

                if (title.isEmpty() || content.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Title and Content cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (customerReceiver.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No customers selected!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Send the message to each selected customer by updating internal data structures
                for (String customer : customerReceiver) {
                    sendMessageToCustomer(customer, title, content);
                }

                // Also send the message to all employees' inboxes
                sendMessageToEmployees(title, content);

                JOptionPane.showMessageDialog(frame, "Notice sent successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                customerReceiver.clear(); // Clear receiver list after sending
                customerListArea.setText(""); // Clear displayed selected customers
            }
        });

        // Customer view panel
        JFrame customerViewFrame = new JFrame("Sent Notices");
        customerViewFrame.setSize(400, 300);

        JPanel customerPanel = new JPanel(new BorderLayout());
        JLabel customerViewLabel = new JLabel("Customer Received:");
        JComboBox<String> customerViewDropdown = new JComboBox<>(registeredCustomers.toArray(new String[0]));
        JTextArea customerMessageArea = new JTextArea();
        customerMessageArea.setEditable(false);

        JButton viewMessagesButton = new JButton("View Messages");
        customerPanel.add(customerViewLabel, BorderLayout.NORTH);
        customerPanel.add(customerViewDropdown, BorderLayout.CENTER);
        customerPanel.add(viewMessagesButton, BorderLayout.SOUTH);

        customerViewFrame.add(new JScrollPane(customerMessageArea), BorderLayout.CENTER);
        customerViewFrame.add(customerPanel, BorderLayout.NORTH);

        viewMessagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCustomer = (String) customerViewDropdown.getSelectedItem();
                List<String> messages = customerMessages.get(selectedCustomer);
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

        JButton openCustomerViewButton = new JButton("Sent Notices");
        panel.add(openCustomerViewButton);
        openCustomerViewButton.addActionListener(e -> customerViewFrame.setVisible(true));

        // Delete button functionality
        JButton deleteMessageButton = new JButton("Delete Selected Notice");
        customerPanel.add(deleteMessageButton, BorderLayout.EAST);

        deleteMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCustomer = (String) customerViewDropdown.getSelectedItem();
                if (selectedCustomer == null || selectedCustomer.isEmpty()) {
                    JOptionPane.showMessageDialog(customerViewFrame, "No customer selected!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Get the list of sent messages for the selected customer
                List<String> sentMessagesList = sentMessages.get(selectedCustomer);
                if (sentMessagesList == null || sentMessagesList.isEmpty()) {
                    JOptionPane.showMessageDialog(customerViewFrame, "No sent messages to delete for this customer.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                // Display sent messages to choose one to delete
                String[] sentMessagesArray = sentMessagesList.toArray(new String[0]);
                String messageToDelete = (String) JOptionPane.showInputDialog(
                        customerViewFrame,
                        "Select a sent notice to delete:",
                        "Delete Notice",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        sentMessagesArray,
                        sentMessagesArray[0]
                );

                if (messageToDelete != null) {
                    sentMessagesList.remove(messageToDelete); // Remove the selected message from sent messages
                    JOptionPane.showMessageDialog(customerViewFrame, "Sent notice deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Update the displayed sent messages
                    customerMessageArea.setText(""); // Clear current messages
                    for (String message : sentMessagesList) {
                        customerMessageArea.append(message + "\n\n");
                    }
                }
            }
        });

        // Inbox for Employees
        JFrame employeeInboxFrame = new JFrame("Employee Inbox");
        employeeInboxFrame.setSize(400, 300);

        JPanel inboxPanel = new JPanel(new BorderLayout());
        JLabel inboxLabel = new JLabel("Inbox Messages:");
        JComboBox<String> employeeDropdown = new JComboBox<>(new String[]{"Employee001", "Employee002", "Employee003"});
        JTextArea inboxMessageArea = new JTextArea();
        inboxMessageArea.setEditable(false);

        JButton viewInboxButton = new JButton("View Inbox");
        inboxPanel.add(inboxLabel, BorderLayout.NORTH);
        inboxPanel.add(employeeDropdown, BorderLayout.CENTER);
        inboxPanel.add(viewInboxButton, BorderLayout.SOUTH);

        employeeInboxFrame.add(new JScrollPane(inboxMessageArea), BorderLayout.CENTER);
        employeeInboxFrame.add(inboxPanel, BorderLayout.NORTH);

        viewInboxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedEmployee = (String) employeeDropdown.getSelectedItem();
                List<String> messages = employeeInbox.get(selectedEmployee);
                inboxMessageArea.setText(""); // Clear previous inbox messages
                if (messages != null && !messages.isEmpty()) {
                    for (String message : messages) {
                        inboxMessageArea.append(message + "\n\n");
                    }
                } else {
                    inboxMessageArea.setText("No messages for this employee.");
                }
            }
        });

        // Button to open the employee inbox window
        JButton openInboxButton = new JButton("View Inbox");
        panel.add(openInboxButton);
        openInboxButton.addActionListener(e -> employeeInboxFrame.setVisible(true));

        // Show the main frame
        frame.setVisible(true);
    }

    // Method to handle sending messages by updating internal data structures
    private void sendMessageToCustomer(String customer, String title, String content) {
        // Add the message to the customerMessages map (received messages)
        List<String> messages = customerMessages.get(customer);
        if (messages != null) {
            messages.add("Title: " + title + "\nContent: " + content);
        }

        // Add the message to the sentMessages map (sent messages)
        List<String> sentMessagesForCustomer = sentMessages.get(customer);
        if (sentMessagesForCustomer != null) {
            sentMessagesForCustomer.add("Title: " + title + "\nContent: " + content);
        }
    }

    // Method to send the message to all employees' inboxes
    private void sendMessageToEmployees(String title, String content) {
        for (String employee : employeeInbox.keySet()) {
            List<String> inboxMessages = employeeInbox.get(employee);
            if (inboxMessages != null) {
                inboxMessages.add("Customer: " + "SomeCustomer" + "\nTitle: " + title + "\nContent: " + content);
            }
        }
    }

    public static void main(String[] args) {
        // Start the application
        SwingUtilities.invokeLater(() -> {
            NoticePost noticePost = new NoticePost("Notice001", "Employee001", "2024-11-28");
            noticePost.createAndShowGUI();
        });
    }
}
