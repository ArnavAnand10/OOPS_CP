package travel.management.system;

import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class BookHotel extends JFrame implements ActionListener {
    Choice c1, c2, c3;
    JTextField t1, t2;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l15, l21, l22, l23, l24, l25;
    JButton b1, b2, b3;
    private JLabel boldText, normalText;

    BookHotel(String username) {
        setBounds(400, 150, 1000, 800);
        getContentPane().setBackground(new Color(6, 7, 15));
        setLayout(null);
        setLocationRelativeTo(null);

        boldText = new JLabel("Book Your Hotel");
        boldText.setBounds(40, 50, 500, 30);
        boldText.setFont(new Font("Poppins", Font.BOLD, 30));
        boldText.setForeground(Color.WHITE);
        add(boldText);

        normalText = new JLabel("You can reserve your hotel here");
        normalText.setBounds(40, 75, 500, 30);
        normalText.setFont(new Font("Poppins", Font.PLAIN, 15));
        normalText.setForeground(new Color(49, 64, 73));
        add(normalText);

        l2 = new JLabel("Username:");
        l2.setBounds(40, 130, 100, 30);
        l2.setFont(new Font("Poppins", Font.PLAIN, 18));
        l2.setForeground(Color.WHITE);
        add(l2);

        l21 = new JLabel();
        l21.setBounds(250, 130, 200, 30);
        l21.setFont(new Font("Poppins", Font.PLAIN, 18));
        l21.setForeground(Color.WHITE);
        add(l21);

        l3 = new JLabel("Select Hotel:");
        l3.setBounds(40, 200, 150, 30);
        l3.setFont(new Font("Poppins", Font.PLAIN, 18));
        l3.setForeground(Color.WHITE);
        add(l3);

        c1 = new Choice();
        c1.add("7 Star Hotel");
        c1.add("5 Star Hotel");
        c1.add("3 Star Hotel");
        c1.add("Budget Hotel");
        c1.setBounds(250, 200, 200, 30);
        c1.setFont(new Font("Poppins", Font.PLAIN, 18));
        add(c1);

        l4 = new JLabel("Total Persons:");
        l4.setBounds(40, 270, 150, 30);
        l4.setFont(new Font("Poppins", Font.PLAIN, 18));
        l4.setForeground(Color.WHITE);
        add(l4);

        t1 = new JTextField();
        t1.setBounds(250, 270, 400, 35);
        t1.setBackground(new Color(28, 30, 50));
        t1.setBorder(BorderFactory.createEmptyBorder());
        t1.setForeground(Color.WHITE);
        add(t1);

        l5 = new JLabel("Days:");
        l5.setBounds(40, 330, 100, 30);
        l5.setFont(new Font("Poppins", Font.PLAIN, 18));
        l5.setForeground(Color.WHITE);
        add(l5);

        t2 = new JTextField();
        t2.setBounds(250, 330, 400, 35);
        t2.setBackground(new Color(28, 30, 50));
        t2.setBorder(BorderFactory.createEmptyBorder());
        t2.setForeground(Color.WHITE);
        add(t2);

        l6 = new JLabel("Swimming Pool:");
        l6.setBounds(40, 390, 200, 30);
        l6.setFont(new Font("Poppins", Font.PLAIN, 18));
        l6.setForeground(Color.WHITE);
        add(l6);

        c2 = new Choice();
        c2.add("Yes");
        c2.add("No");
        c2.setBounds(250, 390, 100, 30);
        c2.setFont(new Font("Poppins", Font.PLAIN, 18));
        add(c2);

        l7 = new JLabel("Food Included:");
        l7.setBounds(40, 450, 200, 30);
        l7.setFont(new Font("Poppins", Font.PLAIN, 18));
        l7.setForeground(Color.WHITE);
        add(l7);

        c3 = new Choice();
        c3.add("Yes");
        c3.add("No");
        c3.setBounds(250, 450, 100, 30);
        c3.setFont(new Font("Poppins", Font.PLAIN, 18));
        add(c3);

        l11 = new JLabel("Total Price:");
        l11.setBounds(40, 510, 200, 30);
        l11.setFont(new Font("Poppins", Font.PLAIN, 18));
        l11.setForeground(Color.WHITE);
        add(l11);

        l25 = new JLabel();
        l25.setBounds(250, 510, 150, 30);
        l25.setForeground(Color.WHITE);
        l25.setFont(new Font("Poppins", Font.PLAIN, 18));
        add(l25);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM account WHERE username = '" + username + "'");
            while (rs.next()) {
                l21.setText(rs.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        b1 = new JButton("Check Price");
        b1.setBackground(new Color(82, 94, 183));
        b1.setForeground(Color.WHITE);
        b1.setBounds(250, 580, 156, 50);
        b1.setFont(new Font("Poppins", Font.PLAIN, 14));
        b1.addActionListener(this);
        b1.setBorderPainted(false);
        add(b1);

        b2 = new JButton("Book Hotel");
        b2.setBackground(new Color(82, 94, 183));
        b2.setForeground(Color.WHITE);
        b2.setBounds(430, 580, 156, 50);
        b2.setFont(new Font("Poppins", Font.PLAIN, 14));
        b2.addActionListener(this);
        b2.setBorderPainted(false);
        add(b2);

        b3 = new JButton("Back");
        b3.setBackground(new Color(82, 94, 183));
        b3.setForeground(Color.WHITE);
        b3.setBounds(610, 580, 156, 50);
        b3.setFont(new Font("Poppins", Font.PLAIN, 14));
        b3.addActionListener(this);
        b3.setBorderPainted(false);
        add(b3);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            if (validateInput()) {
                calculatePrice();
            }
        } else if (ae.getSource() == b2) {
            if (validateInput()) {
                bookHotel();
            }
        } else if (ae.getSource() == b3) {
            this.setVisible(false);
        }
    }

    private boolean validateInput() {
        String personsText = t1.getText();
        String daysText = t2.getText();

        if (!isNumeric(personsText) || !isNumeric(daysText)) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for Persons and Days.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        int persons = Integer.parseInt(personsText);
        int days = Integer.parseInt(daysText);

        if (persons <= 0 || days <= 0) {
            JOptionPane.showMessageDialog(this, "Persons and Days must be greater than zero.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    private void calculatePrice() {
        String selectedHotelType = c1.getSelectedItem();
        int cost1 = 0, cost2 = 0, cost3 = 0;

        switch (selectedHotelType) {
            case "7 Star Hotel":
                cost1 += 32000;
                break;
            case "5 Star Hotel":
                cost1 += 24000;
                break;
            case "3 Star Hotel":
                cost1 += 12000;
                break;
            case "Budget Hotel":
                cost1 += 5000;
                break;
        }

        cost2 = "Yes".equals(c2.getSelectedItem()) ? 2000 : 0;
        cost3 = "Yes".equals(c3.getSelectedItem()) ? 3000 : 0;

        int persons = Integer.parseInt(t1.getText());
        int days = Integer.parseInt(t2.getText());

        int totalCost = (cost1 + cost2 + cost3) * persons * days;

        l25.setText("Rs = " + totalCost);
    }

    private void bookHotel() {
        try {
            Conn c = new Conn();
            String username = l21.getText();
            String selectedHotelType = c1.getSelectedItem();
            String numberOfPersons = t1.getText();
            String numberOfDays = t2.getText();
            String swimmingPool = c2.getSelectedItem();
            String foodIncluded = c3.getSelectedItem();
            String totalPrice = l25.getText();

            String query = "INSERT INTO bookhotel VALUES('" + username + "', '" + selectedHotelType + "', '" + numberOfPersons + "', '" + numberOfDays + "', '" + swimmingPool + "', '" + foodIncluded + "', '" + totalPrice + "')";
            c.s.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Hotel Booked Successfully!");
            this.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BookHotel("User").setVisible(true);
    }
}
