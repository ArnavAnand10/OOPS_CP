 package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
 import java.sql.PreparedStatement;


public class Signup extends JFrame implements ActionListener {
    JButton b1, b2;
    JTextField t1, t2, t3, t4, t5;
    Choice c1;
    JLabel validationLabel; // Label to display validation messages

    Signup() {
        
        setTitle("Sign Up");
        setBounds(400, 150, 1000, 700);
        getContentPane().setBackground(new Color(6, 7, 15));
        setLayout(null);
        setLocationRelativeTo(null);
        
        JPanel p1 = createPanel(0, 0, 1000, 700, new Color(6, 7, 15));
        add(p1);
        
       validationLabel = createLabel("", 40, 150, 600, 25, 14, Color.RED, p1); // Initialize validationLabel
        
       JLabel signUpLabel = new JLabel("Welcome to Travell Planner");
       signUpLabel.setBounds(40, 50, 500, 60);
       signUpLabel.setFont(new Font("Poppins", Font.BOLD, 30));
       signUpLabel.setForeground(Color.WHITE);
       p1.add(signUpLabel);
       
       JLabel instructionLabel = new JLabel("Create Your Free Account Here");
       instructionLabel.setBounds(40, 100, 400, 25);
       instructionLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
       instructionLabel.setForeground(new Color(49, 64, 73));
       p1.add(instructionLabel);



       createLabel("User Name", 40, 210, 125, 25, 15, Color.WHITE, p1);
       t1 = createTextField(190, 210, 250, 35, p1);

       createLabel("Full Name", 540, 210, 125, 25, 15, Color.WHITE, p1);
       t2 = createTextField(670, 210, 250, 35, p1);

       createLabel("Password", 40, 280, 125, 25, 15, Color.WHITE, p1);
       t3 = createPasswordField(190, 280, 400, 35, p1);

       createLabel("Security Question", 40, 350, 125, 25, 15, Color.WHITE, p1);
       c1 = createChoice(new String[]{"Name of your Hometown?", "Name of your Best-friend?", "Your Nickname?", "Your Birth-year?"}, 190, 350, 400, 35, p1);

       createLabel("Answer", 40, 420, 125, 25, 15, Color.WHITE, p1);
       t4 = createTextField(190, 420, 400, 35, p1);

       createLabel("Email", 40, 490, 125, 25, 15, Color.WHITE, p1);
       t5 = createTextField(190, 490, 400, 35, p1);

       b1 = createButton("Create", new Color(82, 94, 183), Color.WHITE, 190, 560, 156, 50, 14, p1);
       b2 = createButton("Back", new Color(82, 94, 183), Color.WHITE, 400, 560, 156, 50, 14, p1);
       
       // Adding document listeners for real-time validation
        t1.getDocument().addDocumentListener(new FieldDocumentListener(t1));
        t2.getDocument().addDocumentListener(new FieldDocumentListener(t2));
        t3.getDocument().addDocumentListener(new FieldDocumentListener(t3));
        t4.getDocument().addDocumentListener(new FieldDocumentListener(t4));
        t5.getDocument().addDocumentListener(new FieldDocumentListener(t5));
        


        configureButton(b1, this);
        configureButton(b2, this);
    }

    
    
    private class FieldDocumentListener implements DocumentListener {
        private JTextField textField;

        FieldDocumentListener(JTextField textField) {
            this.textField = textField;
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            validateField();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            validateField();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            validateField();
        }

        private void validateField() {
            String text = textField.getText().trim();

            if (textField == t1 || textField == t2 || textField == t4 || textField == t5) {
                // For non-password fields, check if empty
                if (text.isEmpty()) {
                    textField.setBorder(BorderFactory.createLineBorder(Color.RED));
                    validationLabel.setText("Please fill in all required fields.");
                } else {
                    textField.setBorder(BorderFactory.createEmptyBorder());
                    validationLabel.setText(""); // Clear validation message
                }
            }

            if (textField == t3) {
                // For the password field, check if it has at least 8 characters
                if (text.length() < 8) {
                    textField.setBorder(BorderFactory.createLineBorder(Color.RED));
                    validationLabel.setText("Password must be at least 8 characters long.");
                } else {
                    textField.setBorder(BorderFactory.createEmptyBorder());
                    validationLabel.setText(""); // Clear validation message
                }
            }

            if (textField == t5) {
                // For the email field, check if it's a valid email
                if (!isValidEmail(text)) {
                    textField.setBorder(BorderFactory.createLineBorder(Color.RED));
                    validationLabel.setText("Please enter a valid email address.");
                } else {
                    textField.setBorder(BorderFactory.createEmptyBorder());
                    validationLabel.setText(""); // Clear validation message
                }
            }
        }
    }
    
    private void configureLabel(JLabel label, int x, int y, int width, int height, int fontStyle, int fontSize, Color color) {
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Poppins", fontStyle, fontSize));
        label.setForeground(color);
    }

    
    private JPasswordField createPasswordField(int x, int y, int width, int height, JPanel panel) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(x, y, width, height);
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        passwordField.setBackground(new Color(28, 30, 50)); // Set background color
        passwordField.setForeground(Color.WHITE);
        panel.add(passwordField);
        return passwordField;
    }
    
    private JLabel createLabel(String text, int x, int y, int width, int height, int fontSize, Color textColor, JPanel panel) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Poppins", Font.PLAIN, fontSize));
        label.setForeground(textColor);
        panel.add(label);
        return label;
    }

    private JPanel createPanel(int x, int y, int width, int height, Color color) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setBounds(x, y, width, height);
        panel.setLayout(null);
        return panel;
    }

    private JTextField createTextField(int x, int y, int width, int height, JPanel panel) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setBackground(new Color(28, 30, 50)); // Set background color
        textField.setForeground(Color.WHITE);
        textField.setFont(new Font("Poppins", Font.PLAIN, 15));
        panel.add(textField);
        return textField;
    }

    private Choice createChoice(String[] items, int x, int y, int width, int height, JPanel panel) {
        Choice choice = new Choice();
        for (String item : items) {
            choice.add(item);
        }
        choice.setBounds(x, y, width, height);
        panel.add(choice);
        return choice;
    }

    private JButton createButton(String text, Color background, Color foreground, int x, int y, int width, int height, int fontSize, JPanel panel) {
        JButton button = new JButton(text);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Poppins", Font.PLAIN, fontSize));
        button.setBorderPainted(false); // Remove the border
        panel.add(button);
        return button;
    }

    private void configureButton(JButton button, ActionListener actionListener) {
        button.setForeground(Color.WHITE);
        button.addActionListener(actionListener);
    }

    @Override
        public void actionPerformed(ActionEvent ae) {
         if (ae.getSource() == b1) { // If 'b1' button is clicked
        String username = t1.getText().trim();
        String name = t2.getText().trim();
        String password = t3.getText().trim();
        String question = c1.getSelectedItem();
        String answer = t4.getText().trim();
        String email = t5.getText().trim();

        // Validation checks
        if (username.isEmpty() || name.isEmpty() || password.isEmpty() || answer.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (password.length() < 8) {
            JOptionPane.showMessageDialog(this, "Password must be at least 8 characters long!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Enter a valid email address!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String query = "INSERT INTO account (username, name, password, question, answer, email) VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Create a connection to the database
            Conn dbConnection = new Conn(); // This should return your established connection
            conn = dbConnection.getConnection();

            // Use PreparedStatement to safely insert data
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, name);
            pstmt.setString(3, password);
            pstmt.setString(4, question);
            pstmt.setString(5, answer);
            pstmt.setString(6, email);

            System.out.println("Trying to create account");
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Account created successfully");
                this.setVisible(false);
                new Login().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to create account");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "SQL Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Close resources in the finally block
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    } else if (ae.getSource() == b2) { // If 'b2' button is clicked
        new Login().setVisible(true);
        this.setVisible(false);
    }
    }

    private boolean isValidEmail(String email) {
    // A simple email validation regex
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z]{2,})?$";



    return email.matches(emailRegex);
}
    
    public static void main(String[] args) {
        new Signup().setVisible(true);
    }
}
