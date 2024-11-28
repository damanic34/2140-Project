import javax.swing.*;
import java.awt.*;

public class FAQsPage extends JFrame {
    public FAQsPage() {
        // Frame setup
        setTitle("FAQs");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // FAQs content
        String[][] faqs = {
            {"Is there a service fee for using your card?", "Yes, there is a service fee charged for using our card to place orders."},
            {"How do I sign up for a shipping address?", "You can sign up for a free USA shipping address by clicking here. Once you create an account with us, you will receive a free USA shipping address and instructions in your email."},
            {"How do you charge for packages?", "We charge by weight for each individual package that we ship for you."}
        };

        for (String[] faq : faqs) {
            JLabel questionLabel = new JLabel(faq[0]);
            questionLabel.setFont(new Font("Arial", Font.BOLD, 14));
            questionLabel.setForeground(Color.BLUE);

            JLabel answerLabel = new JLabel(faq[1]);
            answerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            answerLabel.setForeground(Color.BLACK);

            add(questionLabel);
            add(answerLabel);
            add(Box.createRigidArea(new Dimension(0, 15)));
        }
        setLocationRelativeTo(null);
    }
}
