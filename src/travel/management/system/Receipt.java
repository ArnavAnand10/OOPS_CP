package travel.management.system;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Receipt extends JFrame {
    private JLabel packageLabel, personsLabel, emailLabel, priceLabel;
    private JButton closeButton;

    Receipt(String username) {
        setTitle("Receipt");
        setBounds(600, 200, 1000, 600);
        getContentPane().setBackground(new Color(6, 7, 15));
        setLayout(null);

        JLabel receiptLabel = new JLabel("Receipt");
        receiptLabel.setBounds(250, 30, 100, 30);
        receiptLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        receiptLabel.setForeground(Color.WHITE);
        add(receiptLabel);

        JLabel usernamelabel = new JLabel();
        usernamelabel.setBounds(250, 70, 100, 30);
        usernamelabel.setFont(new Font("Poppins", Font.BOLD, 20));
        usernamelabel.setForeground(Color.WHITE);
        add(usernamelabel);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from account where username = '" + username + "'");
            while (rs.next()) {
                usernamelabel.setText(rs.getString("username"));
            }

        } catch (SQLException e) {

        }

        packageLabel = new JLabel();
        personsLabel = new JLabel();
        emailLabel = new JLabel();
        priceLabel = new JLabel();

        packageLabel.setBounds(50, 100, 300, 30);
        personsLabel.setBounds(50, 140, 300, 30);
        emailLabel.setBounds(50, 180, 300, 30);
        priceLabel.setBounds(50, 220, 300, 30);

        packageLabel.setFont(new Font("Poppins", Font.PLAIN, 18));
        personsLabel.setFont(new Font("Poppins", Font.PLAIN, 18));
        emailLabel.setFont(new Font("Poppins", Font.PLAIN, 18));
        priceLabel.setFont(new Font("Poppins", Font.PLAIN, 18));

        packageLabel.setForeground(Color.WHITE);
        personsLabel.setForeground(Color.WHITE);
        emailLabel.setForeground(Color.WHITE);
        priceLabel.setForeground(Color.WHITE);
        
        // Create labels to indicate the content of the main labels
                JLabel packageIndicatorLabel = new JLabel("Package: ");
                JLabel personsIndicatorLabel = new JLabel("Persons: ");
                JLabel emailIndicatorLabel = new JLabel("Email: ");
                        JLabel priceIndicatorLabel = new JLabel("Price: ");

        // Set the bounds for the indicator labels
        packageIndicatorLabel.setBounds(10, 100, 80, 30);
        personsIndicatorLabel.setBounds(10, 140, 80, 30);
        emailIndicatorLabel.setBounds(10, 180, 80, 30);
        priceIndicatorLabel.setBounds(10, 220, 80, 30);

        // Set fonts and foreground colors for the indicator labels
        packageIndicatorLabel.setFont(new Font("Poppins", Font.PLAIN, 18));
        personsIndicatorLabel.setFont(new Font("Poppins", Font.PLAIN, 18));
        emailIndicatorLabel.setFont(new Font("Poppins", Font.PLAIN, 18));
        priceIndicatorLabel.setFont(new Font("Poppins", Font.PLAIN, 18));

        packageIndicatorLabel.setForeground(Color.WHITE);
        personsIndicatorLabel.setForeground(Color.WHITE);
        emailIndicatorLabel.setForeground(Color.WHITE);
        priceIndicatorLabel.setForeground(Color.WHITE);

        // Add the indicator labels to the frame
        add(packageIndicatorLabel);
        add(personsIndicatorLabel);
        add(emailIndicatorLabel);
        add(priceIndicatorLabel);


        add(packageLabel);
        add(personsLabel);
        add(emailLabel);
        add(priceLabel);

        closeButton = new JButton("Close Receipt");
        closeButton.setBackground(new Color(82, 94, 183));
        closeButton.setForeground(Color.WHITE);
        closeButton.setBounds(220, 300, 150, 30);
        closeButton.setFont(new Font("Poppins", Font.PLAIN, 14));
        closeButton.setBorderPainted(false);
        closeButton.addActionListener((ActionEvent e) -> {
            dispose();
        });
        add(closeButton);
    }

    private void generateReceipt(String username) {
    try {
        Conn c = new Conn();
        String query = "SELECT * FROM bookpackage WHERE username = '" + username + "'";
        ResultSet rs = c.s.executeQuery(query);

        if (rs.next()) {
            usernamelabel.setText(rs.getString("username"));
            packageLabel.setText("Package: " + rs.getString("package"));
            personsLabel.setText("Persons: " + rs.getInt("persons"));
            emailLabel.setText("Email: " + rs.getString("email"));
            priceLabel.setText("Price: " + rs.getString("price"));
        } else {
            JOptionPane.showMessageDialog(null, "No details found for username: " + username);
            dispose();  // Close the window if no details found
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}



    
    public static void main(String[] args) {
        // Replace 123 with the actual booking ID
        new Receipt("").setVisible(true);
    }

    private static class usernamelabel {

        private static void setText(String string) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public usernamelabel() {
        }
    }
}
