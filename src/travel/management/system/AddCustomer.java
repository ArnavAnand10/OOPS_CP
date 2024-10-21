package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomer extends JFrame implements ActionListener {
    JTextField t1, t2, t3, t4, t5, t6, t7;
    JComboBox<String> c1;
    JRadioButton r1, r2;
    JButton b1, b2;

    AddCustomer() {
        setBounds(400, 150, 1000, 600);
        getContentPane().setBackground(new Color(6, 7, 15));
        setLayout(null);
        setLocationRelativeTo(null);

        // Bold Text
        JLabel boldLabel = new JLabel("Add Customer Details");
        boldLabel.setBounds(30, 20, 500, 30);
        boldLabel.setForeground(Color.WHITE);
        boldLabel.setFont(new Font("Poppins", Font.BOLD, 30));
        add(boldLabel);
        
        // Normal Text
        JLabel normalLabel = new JLabel("Please enter the customer details below:");
        normalLabel.setBounds(30, 60, 400, 25);
        normalLabel.setForeground(Color.WHITE);
        normalLabel.setFont(new Font("Poppins", Font.PLAIN, 15));
        add(normalLabel);
        
        createLabel("Username:", 30, 120, 150, 25, 15, Color.WHITE);
    t1 = createTextField(220, 120, 400, 35);

    createLabel("Full Name:", 30, 180, 150, 25, 15, Color.WHITE);
    t2 = createTextField(220, 180, 400, 35);

    createLabel("Phone Number:", 30, 240, 150, 25, 15, Color.WHITE);
    t3 = createTextField(220, 240, 400, 35);

    createLabel("Gender:", 30, 300, 150, 25, 15, Color.WHITE);
    r1 = createRadioButton("Male", 220, 300, 70, 25);
    r2 = createRadioButton("Female", 300, 300, 150, 25);

    createLabel("Country:", 30, 360, 150, 25, 15, Color.WHITE);
    t4 = createTextField(220, 360, 400, 35);

    createLabel("Email:", 30, 420, 150, 25, 15, Color.WHITE);
    t7 = createTextField(220, 420, 400, 35);

        b1 = createButton("ADD", new Color(66, 77, 158), Color.WHITE, 220, 470, 156, 50);
        b2 = createButton("BACK", new Color(66, 77, 158), Color.WHITE, 380, 470, 156, 50);


        b1.addActionListener(this);
        b2.addActionListener(this);

        setVisible(true);
    }

    private void createLabel(String text, int x, int y, int width, int height, int fontSize, Color color) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setForeground(color);
        label.setFont(new Font("Poppins", Font.PLAIN, fontSize));
        add(label);
    }

    private JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setBorder(null); // Remove the border
        textField.setBackground(new Color(28, 30, 50)); // Set background color
        textField.setForeground(Color.WHITE);
        add(textField);
        return textField;
    }

    private JComboBox<String> createComboBox(String[] items, int x, int y, int width, int height) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setBounds(x, y, width, height);
        comboBox.setBackground(new Color(108, 122, 137));
        comboBox.setForeground(Color.WHITE);
        add(comboBox);
        return comboBox;
    }

    private JRadioButton createRadioButton(String text, int x, int y, int width, int height) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setBounds(x, y, width, height);
        radioButton.setBackground(new Color(33, 33, 33));
        radioButton.setForeground(Color.WHITE);
        radioButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(radioButton);
        return radioButton;
    }

    private JButton createButton(String text, Color background, Color foreground, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setBounds(x, y, width, height);
        button.setBorderPainted(false); // Remove the border
        add(button);
        return button;
    }

    private void createImageIcon(String path, int x, int y, int width, int height) {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(path));
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon);
        label.setBounds(x, y, width, height);
        add(label);
    }


    

    
    private boolean isValidEmail(String email) {
    // Regular expression for a simple email format validation
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    return email.matches(emailRegex);
}

    
    
    public static void main(String[] args) {
        new AddCustomer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
