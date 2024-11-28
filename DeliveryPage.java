import javax.swing.*;
import java.awt.*;

public class DeliveryPage extends JPanel {
    public DeliveryPage(MainFrame mainFrame) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Heading panel
        JPanel headingPanel = new JPanel();
        headingPanel.setBackground(Color.WHITE);
        JLabel headingLabel = new JLabel("Islandwide Delivery");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setForeground(new Color(0, 102, 204));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 102, 204))); // Adds underline
        headingPanel.add(headingLabel);
        add(headingPanel, BorderLayout.NORTH);

        // Content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add the delivery information
        String deliveryText = """
                We can have your package delivered to you anywhere in Jamaica. We offer delivery in Kingston at drop-off points or to your office/residence. 
                If you require your package to be delivered outside Kingston, we utilize Knutsford Express (1 business day) or the Post Office's ZipMail (3-5 business days).\n
                Knutsford pick-up offices (drop-off points) are located in: Montego Bay (Airport and Pier One), Trelawny (Falmouth), Ocho Rios, Negril, Savanna-La-Mar, Mandeville, Black River, and Port Antonio.\n
                For other areas that Knutsford Express doesn't offer delivery, we will send your packages via Zip Mail (post office to post office).
                """;

        JTextArea deliveryInfo = new JTextArea(deliveryText);
        deliveryInfo.setFont(new Font("Arial", Font.PLAIN, 14));
        deliveryInfo.setLineWrap(true);
        deliveryInfo.setWrapStyleWord(true);
        deliveryInfo.setEditable(false);
        deliveryInfo.setOpaque(false);
        contentPanel.add(deliveryInfo);

        add(contentPanel, BorderLayout.CENTER);

        // Footer panel with "More Info" button
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);
        JButton moreInfoButton = new JButton("More Info");
        moreInfoButton.setFont(new Font("Arial", Font.BOLD, 16));
        moreInfoButton.setBackground(new Color(0, 102, 204));
        moreInfoButton.setForeground(Color.WHITE);

        // Add action listener for More Info button
        moreInfoButton.addActionListener(e -> {
            // Show a dialog with the phone number and options to cancel or call
            int option = JOptionPane.showOptionDialog(
                    this,
                    "Contact Us at: (876) 555-1234",
                    "More Info",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new String[]{"Call", "Cancel"},
                    "Cancel"
            );

            if (option == JOptionPane.YES_OPTION) {
                // Simulate calling (you can add functionality if needed)
                JOptionPane.showMessageDialog(this, "Dialing (876) 555-1234...", "Call", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        footerPanel.add(moreInfoButton);
        add(footerPanel, BorderLayout.SOUTH);
    }
}
