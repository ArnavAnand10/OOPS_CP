package travel.management.system;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookPackage extends JFrame implements ActionListener
{
    Choice c1;
    JLabel l2,l3,l4,l7,l8,l11,l12,l13,l14,l15;
    JTextField t1;
    JButton b1,b2,b3;
    private JLabel boldText, normalText;
    private final String username;
    
    BookPackage(String  username)
    {
        this.username = username;
        
        setTitle("Book Package");
        setBounds(400, 150, 1000, 700);
        setLayout(null);
        getContentPane().setBackground(new Color(6, 7, 15));
        setLocationRelativeTo(null);
        
        boldText = new JLabel("Select and Book Your Package");
        boldText.setBounds(40, 50, 500, 30);
        boldText.setFont(new Font("Poppins", Font.BOLD, 30));
        boldText.setForeground(Color.WHITE);
        add(boldText);
        
        normalText = new JLabel("You can reserve your prefered package here");
        normalText.setBounds(40, 75, 500, 30);
        normalText.setFont(new Font("Poppins", Font.PLAIN, 15));
        normalText.setForeground(new Color(49, 64, 73));
        add(normalText);
        
        l2 = new JLabel("User Name:");
        l2.setBounds(40, 270, 150, 30);
        l2.setFont(new Font("Poppins",Font.PLAIN,18));
        l2.setForeground(Color.WHITE);
        add(l2);
        
        l11 = new JLabel();
        l11.setBounds(250, 270, 400, 35);
        l11.setFont(new Font("Poppins",Font.PLAIN,18));
        l11.setForeground(Color.WHITE);
        add(l11);
        
        l3 = new JLabel("Select Package:");
        l3.setBounds(40, 330, 200, 30);
        l3.setFont(new Font("Poppins",Font.PLAIN,18));
        l3.setForeground(Color.WHITE);
        add(l3);
        
        c1 = new Choice();
        c1.add("Gold Package");
        c1.add("Silver Package");
        c1.add("Bronze Package");
        c1.setBounds(250, 330, 400, 35);
        c1.setFont(new Font("Poppins", Font.PLAIN, 18));
        add(c1);
        
        l4 = new JLabel("Total Persons:");
        l4.setBounds(40, 390, 200, 30);
        l4.setFont(new Font("Poppins", Font.PLAIN, 18));;
        l4.setForeground(Color.WHITE);
        add(l4);
        
        t1 = new JTextField("1");
        t1.setBounds(250, 390, 400, 35);
        t1.setFont(new Font("Poppins", Font.PLAIN, 18));
        t1.setBackground(new Color(28, 30, 50));
        t1.setBorder(BorderFactory.createEmptyBorder()); // Removed the border
        t1.setForeground(Color.WHITE);
        add(t1);
        
        l7 = new JLabel("Email Address:");
        l7.setBounds(40, 450, 200, 30);
        l7.setFont(new Font("Poppins", Font.PLAIN, 18));
        l7.setForeground(Color.WHITE);
        add(l7);
        
        l14 = new JLabel();
        l14.setBounds(250, 450, 300, 30);
        l14.setFont(new Font("Poppins", Font.PLAIN, 18));
        l14.setForeground(Color.WHITE);
        add(l14);
        
        l8 = new JLabel("Total Price:");
        l8.setBounds(40, 510, 200, 30);
        l8.setFont(new Font("Poppins", Font.PLAIN, 18));
        l8.setForeground(Color.WHITE);
        add(l8);
        
        l15 = new JLabel();
        l15.setBounds(250, 510, 150, 30);
        l15.setForeground(Color.red);
        l15.setFont(new Font("Poppins", Font.PLAIN, 18));
        l15.setForeground(Color.WHITE);
        add(l15);
        
        try
        {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from account where username = '"+username+"'");
            while(rs.next())
            {
                l11.setText(rs.getString("username"));
                l14.setText(rs.getString("email"));
            }
            
        }catch(SQLException e)
        {
            
        }
        
        b1 = new JButton("Check Price");
        b1.setBackground(new Color(82, 94, 183));
        b1.setForeground(Color.WHITE);
        b1.setBounds(250,580,156, 50);
        b1.setFont(new Font("Poppins", Font.PLAIN, 14));
        b1.setBorderPainted(false);
        b1.addActionListener(this);
        add(b1);
        
        b2 = new JButton("Book Package");
        b2.setBackground(new Color(82, 94, 183));
        b2.setForeground(Color.WHITE);
        b2.setBounds(430,580,156, 50);
        b2.setFont(new Font("Poppins", Font.PLAIN, 14));
        b2.setBorderPainted(false);
        b2.addActionListener(this);
        add(b2);
        
        b3 = new JButton("BACK");
        b3.setBackground(new Color(82, 94, 183));
        b3.setForeground(Color.WHITE);
        b3.setBounds(610,580,156, 50);
        b3.setFont(new Font("Poppins", Font.PLAIN, 14));
        b3.setBorderPainted(false);
        b3.addActionListener(this);
        add(b3);
      
        
    }
    
    public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == b1) {
        calculatePackageCost();
    } else if (ae.getSource() == b2) {
        try {
            bookPackage();
        } catch (SQLException ex) {
            Logger.getLogger(BookPackage.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else if (ae.getSource() == b3) {
        new Receipt(username).setVisible(true);
    }
}

private void calculatePackageCost() {
    String selectedPackage = c1.getSelectedItem();
    int baseCost = 0;

    if (selectedPackage.equals("Gold Package")) {
        baseCost = 12000;
    } else if (selectedPackage.equals("Silver Package")) {
        baseCost = 24000;
    } else if (selectedPackage.equals("Bronze Package")) {
        baseCost = 32000;
    }

    int persons = Integer.parseInt(t1.getText());
    int totalCost = baseCost * persons;

    l15.setText("Rs = " + totalCost);
}

private void bookPackage() throws SQLException {
    try {
        Conn c = new Conn();
        String username = l11.getText();
        String selectedPackage = c1.getSelectedItem();
        String numberOfPersons = t1.getText();
        String email = l14.getText();  // Using the email field instead of phone
        String price = l15.getText();

        String query = String.format(
            "INSERT INTO bookpackage (username, package, persons, email, price) VALUES ('%s', '%s', '%s', '%s', '%s')",
            username, selectedPackage, numberOfPersons, email, price
        );

        c.s.executeUpdate(query);

        JOptionPane.showMessageDialog(null, "Package Booked Successfully!");
        this.setVisible(false);
        
    } catch (HeadlessException e) {
        // Handle exceptions properly, printStackTrace is for debugging purposes
        
    }
}


    public static void main(String[] args)
    {
        new BookPackage("").setVisible(true);
    }
}
