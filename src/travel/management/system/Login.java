package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton b1, b2, b3, b4;
    JTextField t1;
    JPasswordField t2;

    Login() {
        setTitle("Log In");
        setLayout(null);
        getContentPane().setBackground(new Color(6, 7, 15));
        setBounds(400, 150, 1000, 600);
        setLocationRelativeTo(null);

        JPanel p1 = new JPanel();
        p1.setBounds(0, 0, 400, 600);
        p1.setBackground(new Color(6, 7, 15));
        p1.setLayout(null);
        add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/login.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(0, 0, 500, 600);
        p1.add(l1);

        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBounds(400, 25, 600, 500);
        p2.setBackground(new Color(6, 7, 15));
        add(p2);

        // Add the "Welcome back!" label
        JLabel welcomeLabel = new JLabel("Welcome back!");
        welcomeLabel.setBounds(200, 70, 200, 25);
        welcomeLabel.setFont(new Font("Poppins", Font.BOLD, 23));
        welcomeLabel.setForeground(Color.WHITE);
        p2.add(welcomeLabel);

        // Add the "Log into your account" label
        JLabel loginLabel = new JLabel("Log into your account");
        loginLabel.setBounds(210, 100, 200, 25);
        loginLabel.setFont(new Font("Poppins", Font.PLAIN, 15));
        loginLabel.setForeground(new Color(49, 64, 73));
        p2.add(loginLabel);

        JLabel l2 = new JLabel("User Name");
        l2.setBounds(50, 220, 150, 25);
        l2.setFont(new Font("Poppins", Font.PLAIN, 15));
        l2.setForeground(Color.WHITE);
        p2.add(l2);

        t1 = new JTextField();
        t1.setBounds(50, 250, 500, 40);
        t1.setBackground(new Color(28, 30, 50));
        t1.setForeground(Color.WHITE);
        t1.setFont(new Font("Poppins", Font.PLAIN, 15));
        t1.setBorder(BorderFactory.createEmptyBorder());
        p2.add(t1);

        JLabel l3 = new JLabel("Password");
        l3.setBounds(50, 300, 150, 25);
        l3.setFont(new Font("Poppins", Font.PLAIN, 15));
        l3.setForeground(Color.WHITE);
        p2.add(l3);

        t2 = new JPasswordField();
        t2.setBounds(50, 330, 500, 40);
        t2.setBackground(new Color(28, 30, 50));
        t2.setForeground(Color.WHITE);
        t2.setBorder(BorderFactory.createEmptyBorder());
        p2.add(t2);

        b1 = createButton("Login", new Color(82, 94, 183), Color.WHITE, 50, 390, 156, 50, 18, p2);
        b2 = createButton("Sign Up", new Color(66, 77, 158), Color.WHITE, 260, 390, 156, 50, 18, p2);
        b3 = createButton("Forget Password?", new Color(15, 113, 115), Color.WHITE, 260, 470, 180, 30, 10, p2);
        b4 = createButton("Are You an Admin ?", new Color(0, 45, 143), Color.WHITE, 50, 470, 200, 30, 10, p2);
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

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            loginUser();
        } else if (ae.getSource() == b2) {
            new Signup().setVisible(true);
            this.setVisible(false);
        } else if (ae.getSource() == b3) {
            this.setVisible(false);
            new ForgotPassword().setVisible(true);
        } else if (ae.getSource() == b4) {
            new AdminLogin().setVisible(true);
            this.setVisible(false);
        }
    }

    private void loginUser() {
        String input = t1.getText().trim(); // Get the input from the username/email field
        String password = t2.getText();

        if (input.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter both username/email and password!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql;
        boolean isEmail = isValidEmail(input);

        try {
            Conn conn = new Conn();
            PreparedStatement pstmt;

            if (isEmail) {
                // If the input is a valid email, use email for login
                sql = "SELECT * FROM account WHERE email = ? AND password = ?";
            } else {
                // Otherwise, use username for login
                sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            }

            pstmt = conn.getConnection().prepareStatement(sql);
            pstmt.setString(1, input);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                this.setVisible(false);
                new Dashboard(input).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Login !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}
