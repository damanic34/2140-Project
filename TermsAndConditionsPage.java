import javax.swing.*;
import java.awt.*;

public class TermsAndConditionsPage extends JFrame {
    public TermsAndConditionsPage() {
        // Set the frame properties
        setTitle("Terms and Conditions");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel to hold the content
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Heading - Terms and Conditions
        JLabel heading = new JLabel("Terms & Conditions", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setForeground(Color.BLUE);
        heading.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Create the content area for the terms
        JTextArea termsArea = new JTextArea();
        termsArea.setText(
                "1. Global Logistics And Travel Inc. Ltd stores uncollected packages (with outstanding balances) for a maximum of five (5) days without penalty.\n" +
                "   After these 5 days, a storage fee of JM$100 per day is applied for each day the package is not collected.\n" +
                "   After 30 days, the package will be considered unclaimed and will be repossessed.\n" +
                "   If there is no outstanding balance, the initial 5-day-no-penalty period is extended to 14 days.\n" +
                "—————————————————————————————————————-\n" +
                "2. If the shipping weight of an order increases upon arrival, the final price will be adjusted to reflect the new weight (e.g: if a package estimated at 1lb is confirmed to be 2lbs upon arrival).\n" +
                "—————————————————————————————————————-\n" +
                "3. When placing orders on customers' behalf, Global Logistics And Travel Inc. Ltd is only responsible for:\n" +
                "   • Placing the order (from the link sent by the customer)\n" +
                "   • Shipping packages safely\n" +
                "   • Storing packages securely (according to Term of Service 1 above.)\n\n" +
                "   NB: Global Logistics And Travel Inc. Ltd is not responsible for items that do not arrive as shown on websites (via the links received from customers).\n" +
                "   Please read the description and other details about the item before making the order.\n" +
                "—————————————————————————————————————-\n" +
                "Shipping packages with your card\n\n" +
                "Currently, we ship packages two (2) times per week. If there are any delays beyond our control (flight delay, customs clearance delay, and others), we are not responsible for those inconveniences. We do not refund packages unless they are lost or damaged by the Global Logistics And Travel Inc. Ltd team.\n\n" +
                "Thanks for choosing Global Logistics And Travel Inc. Ltd."
        );
        termsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        termsArea.setLineWrap(true);
        termsArea.setWrapStyleWord(true);
        termsArea.setEditable(false);
        termsArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add scroll pane for the terms
        JScrollPane scrollPane = new JScrollPane(termsArea);

        // Add components to the panel
        panel.add(heading, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add panel to the frame
        add(panel);
    }
}
