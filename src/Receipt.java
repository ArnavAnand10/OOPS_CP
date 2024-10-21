// Receipt.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Receipt extends JFrame {
    private JLabel receiptLabel;

    public Receipt(String packageType, String duration, String persons, String offer, String totalCost) {
        setTitle("Booking Receipt");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JPanel receiptPanel = new JPanel(new BorderLayout());
        receiptPanel.setBackground(new Color(6, 7, 15));
        receiptPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        receiptLabel = new JLabel("<html>Package: " + packageType + "<br>Duration: " + duration +
                "<br>Persons: " + persons + "<br>Special Offer: " + offer +
                "<br>Total Cost: " + totalCost + "</html>");
        receiptLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        receiptLabel.setForeground(Color.WHITE);
        receiptPanel.add(receiptLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);

        JButton confirmButton = new JButton("Confirm Booking");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Assume sendEmail() is a method to send the receipt to the user's email
                sendEmail(packageType, duration, persons, offer, totalCost);
                JOptionPane.showMessageDialog(null, "Booking confirmed. Receipt sent to your email.");
            }
        });
        confirmButton.setBackground(new Color(255, 87, 34));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(confirmButton);

        mainPanel.add(receiptPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void sendEmail(String packageType, String duration, String persons, String offer, String totalCost) {
        // Implement the logic to send the receipt to the user's email
        // You can use JavaMail API or any other email sending mechanism
        // Example:
        String userEmail = "user@example.com";  // Get the user's email from sign up
        String subject = "Booking Receipt";
        String body = "Package: " + packageType + "\nDuration: " + duration +
                "\nPersons: " + persons + "\nSpecial Offer: " + offer +
                "\nTotal Cost: " + totalCost;

        // Call a method to send the email
        // sendEmail(userEmail, subject, body);
    }

    // Main method to test the Receipt class
    public static void main(String[] args) {
        // Example usage:
        Receipt receipt = new Receipt("Gold Package", "6 Days", "2 Persons", "Summer Special", "Rs 24,000/-");
    }

    private void calculatePackageCost() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
