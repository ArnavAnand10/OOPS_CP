package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ForgotPassword extends JFrame implements ActionListener {
    JTextField t1, t2, t3, t4, t5;
    JButton b1, b2, b3;

    ForgotPassword() {
        setBounds(400, 150, 1000, 600);
        getContentPane().setBackground(new Color(6, 7, 15));
        setLayout(null);
        setLocationRelativeTo(null);


        JPanel p = createPanel(30, 30, 1000, 600, new Color(6, 7, 15));
        add(p);

        // Add two JLabels at the top
        JLabel findPasswordLabel = createLabel("Find your forgotten password", 40, 20, 400, 25, 24, Color.WHITE, Font.BOLD, p);

        createLabel("User Name", 40, 100, 150, 25, 15, Color.WHITE, p);
        t1 = createTextField(220, 100, 400, 35, p);

        b1 = createButton("Search", new Color(66, 77, 158), Color.WHITE, 630, 100, 100, 35, 14, p);

        createLabel("Name", 40, 160, 150, 25, 15, Color.WHITE, p);
        t2 = createTextField(220, 160, 400, 35, p);

        createLabel("Security Question", 40, 220, 150, 25, 15, Color.WHITE, p);
        t3 = createTextField(220, 220, 400, 35, p);

        createLabel("Answer", 40, 280, 150, 25, 15, Color.WHITE, p);
        t4 = createTextField(220, 280, 400, 35, p);

        b2 = createButton("Retrieve", new Color(66, 77, 158), Color.WHITE, 630, 280, 100, 35, 14, p);

        createLabel("Password", 40, 340, 150, 25, 15, Color.WHITE, p);
        t5 = createTextField(220, 340, 400, 35, p);

        b3 = createButton("Back", new Color(66, 77, 158), Color.WHITE, 220, 430, 156, 50, 14, p);


    }

    private JPanel createPanel(int x, int y, int width, int height, Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(x, y, width, height);
        panel.setBackground(color);
        return panel;
    }
    
    private JLabel createLabel(String text, int x, int y, int width, int height, int fontSize, Color textColor, int fontStyle, JPanel panel) {
    JLabel label = new JLabel(text);
    label.setBounds(x, y, width, height);
    label.setFont(new Font("Poppins", fontStyle, fontSize));
    label.setForeground(textColor);
    panel.add(label);
    return label;
}


    private JLabel createLabel(String text, int x, int y, int width, int height, int fontSize, Color textColor,  JPanel panel) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Poppins", Font.PLAIN, 15));
        label.setForeground(textColor);
        panel.add(label);
        return label;
    }

    private JTextField createTextField(int x, int y, int width, int height, JPanel panel) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setBorder(null); // Remove the border
        textField.setFont(new Font("Poppins", Font.PLAIN, 15));
        textField.setBackground(new Color(28, 30, 50)); // Set background color
        textField.setForeground(new Color(255,255,255)); // Set text color
        panel.add(textField);
        return textField;
    }

    private JButton createButton(String text, Color background, Color foreground, int x, int y, int width, int height, int fontSize, JPanel panel) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFont(new Font("Poppins", Font.PLAIN, 15));
        button.setBorderPainted(false); // Remove the border
        button.addActionListener(this);
        panel.add(button);
        return button;
    }

    public void actionPerformed(ActionEvent ae) {
    Conn c = new Conn();

    if (ae.getSource() == b1) {
        String username = t1.getText().trim();

        if (username.isEmpty()) {
            showMessage("Please enter a username.");
            return;
        }

        try {
            String sql = "select * from account where username = '" + username + "'";
            ResultSet rs = c.s.executeQuery(sql);

            while (rs.next()) {
                t2.setText(rs.getString("name"));
                t3.setText(rs.getString("question"));
            }
        } catch (Exception e) {
            // Handle the exception
        }
    } else if (ae.getSource() == b2) {
        String answer = t4.getText().trim();
        String username = t1.getText().trim();

        if (answer.isEmpty() || username.isEmpty()) {
            showMessage("Please enter both username and answer.");
            return;
        }

        try {
            String sql = "select * from account where answer = '" + answer + "' AND username = '" + username + "'";
            ResultSet rs = c.s.executeQuery(sql);

            while (rs.next()) {
                t5.setText(rs.getString("password"));
            }
        } catch (Exception e) {
            // Handle the exception
        }
    } else if (ae.getSource() == b3) {
        this.setVisible(false);
        new Login().setVisible(true);
    }
}

private void showMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
}


    public static void main(String[] args) {
        new ForgotPassword().setVisible(true);
    }
}
