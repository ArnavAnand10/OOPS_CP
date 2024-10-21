package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import travel.management.system.AdminDashboard;
import travel.management.system.Conn;
import travel.management.system.Dashboard;
import travel.management.system.ForgotPassword;
import travel.management.system.Signup;

public class AdminLogin extends JFrame implements ActionListener {
    JButton loginButton, forgotPasswordButton;
    JTextField usernameEmailField;
    JPasswordField passwordField;

    public AdminLogin() {
        setTitle("Admin Login");
        setLayout(null);
        getContentPane().setBackground(new Color(6, 7, 15));
        setBounds(400, 150, 1000, 600);
        setLocationRelativeTo(null);

        JPanel imagePanel = new JPanel();
        imagePanel.setBounds(0, 0, 400, 600);
        imagePanel.setBackground(new Color(6, 7, 15));
        imagePanel.setLayout(null);
        add(imagePanel);

        ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/login1.png"));
        Image scaledImage = backgroundImage.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel backgroundLabel = new JLabel(scaledImageIcon);
        backgroundLabel.setBounds(0, 0, 500, 600);
        imagePanel.add(backgroundLabel);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBounds(400, 25, 600, 500);
        loginPanel.setBackground(new Color(6, 7, 15));
        add(loginPanel);

        // Add the "Welcome back!" label
        JLabel welcomeLabel = new JLabel("Welcome back!");
        welcomeLabel.setBounds(200, 70, 200, 25);
        welcomeLabel.setFont(new Font("Poppins", Font.BOLD, 23));
        welcomeLabel.setForeground(Color.WHITE);
        loginPanel.add(welcomeLabel);

        // Add the "Log into your account" label
        JLabel loginLabel = new JLabel("Log into your account");
        loginLabel.setBounds(210, 100, 200, 25);
        loginLabel.setFont(new Font("Poppins", Font.PLAIN, 15));
        loginLabel.setForeground(new Color(49, 64, 73));
        loginPanel.add(loginLabel);

        JLabel usernameEmailLabel = new JLabel("Username or Email");
        usernameEmailLabel.setBounds(50, 220, 150, 25);
        usernameEmailLabel.setFont(new Font("Poppins", Font.PLAIN, 15));
        usernameEmailLabel.setForeground(Color.WHITE);
        loginPanel.add(usernameEmailLabel);

        usernameEmailField = new JTextField();
        usernameEmailField.setBounds(50, 250, 500, 40);
        usernameEmailField.setBackground(new Color(28, 30, 50));
        usernameEmailField.setForeground(Color.WHITE);
        usernameEmailField.setFont(new Font("Poppins", Font.PLAIN, 15));
        usernameEmailField.setBorder(BorderFactory.createEmptyBorder());
        loginPanel.add(usernameEmailField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 300, 150, 25);
        passwordLabel.setFont(new Font("Poppins", Font.PLAIN, 15));
        passwordLabel.setForeground(Color.WHITE);
        loginPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(50, 330, 500, 40);
        passwordField.setBackground(new Color(28, 30, 50));
        passwordField.setForeground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        loginPanel.add(passwordField);

        loginButton = createButton("Login", new Color(82, 94, 183), Color.WHITE, 50, 390, 156, 50, 18, loginPanel);
        forgotPasswordButton = createButton("Are You an Normal User ?", new Color(15, 113, 115), Color.WHITE, 50, 470, 250, 30, 15, loginPanel);
    }

    private JButton createButton(String text, Color background, Color foreground, int x, int y, int width, int height, int fontSize, JPanel panel) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFont(new Font("Poppins", Font.PLAIN, fontSize));
        button.setBorderPainted(false);
        button.addActionListener(this);
        panel.add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == loginButton) {
        try {
            String input = usernameEmailField.getText().trim(); // Get the input from the username/email field
            String password = new String(passwordField.getPassword());

            String sql;
            if (isValidEmail(input)) {
                // If the input is a valid email, use email for login
                sql = "select * from account where email = '" + input + "' AND password = '" + password + "'";
            } else {
                // Otherwise, use username for login
                sql = "select * from account where username = '" + input + "' AND password = '" + password + "'";
            }

            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery(sql);

            if (rs.next()) {
                this.setVisible(false);
                new AdminDashboard().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Login!");
            }
        } catch (HeadlessException | SQLException e) {
            // Handle exceptions properly in your actual code
            e.printStackTrace();
        }
    }
    
    else if(ae.getSource() == forgotPasswordButton)
        {
            new Login().setVisible(true);                                      
            this.setVisible(false);                                             
        }
}
  



    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    public static void main(String[] args) {
        new AdminLogin().setVisible(true);
    }
}
