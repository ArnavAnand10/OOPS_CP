package travel.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class ViewBookedHotel extends JFrame implements ActionListener
{
    JButton b1,b2,b3,deleteButton;
    private String username;
    
    ViewBookedHotel(String username)
    {
        this.username = username;
        setTitle("Hotel Details");
        setBounds(550,250,1000,600);
        getContentPane().setBackground(new Color(6, 7, 15));
        setLayout(null);
        setLocationRelativeTo(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/hotel1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(540,600,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l110 = new JLabel(i3);
        l110.setBounds(600,0,400,600);
        add(l110);
        
        JLabel heading = new JLabel("View Hotel Details");
        heading.setBounds(30,40,300,25);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Poppins", Font.BOLD, 25));
        add(heading);
        
        JLabel l1 = new JLabel("Username : ");
        l1.setBounds(30,80,100,30);
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("Poppins", Font.PLAIN, 16));
        add(l1);
        
        JLabel l11 = new JLabel();
        l11.setBounds(250,80,200,30);
        l11.setForeground(Color.WHITE);
        l11.setFont(new Font("Poppins", Font.PLAIN, 16));
        add(l11);
        
        JLabel l2 = new JLabel("Hotel : ");
        l2.setBounds(30,140,100,30);
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("Poppins", Font.PLAIN, 16));
        add(l2);
        
        JLabel l12 = new JLabel();
        l12.setBounds(250,140,200,30);
        l12.setForeground(Color.WHITE);
        l12.setFont(new Font("Poppins", Font.PLAIN, 16));
        add(l12);
        
        JLabel l3 = new JLabel("Total Persons : ");
        l3.setBounds(30,200,150,30);
        l3.setForeground(Color.WHITE);
        l3.setFont(new Font("Poppins", Font.PLAIN, 16));
        add(l3);
        
        JLabel l13 = new JLabel();
        l13.setBounds(250,200,200,30);
        l13.setForeground(Color.WHITE);
        l13.setFont(new Font("Poppins", Font.PLAIN, 16));
        add(l13);
        
        JLabel l4 = new JLabel("Total Days : ");
        l4.setBounds(30,260,100,30);
        l4.setForeground(Color.WHITE);
        l4.setFont(new Font("Poppins", Font.PLAIN, 16));
        add(l4);
        
        JLabel l14 = new JLabel();
        l14.setBounds(250,260,200,30);
        l14.setForeground(Color.WHITE);
        l14.setFont(new Font("Poppins", Font.PLAIN, 16));
        add(l14);
        
        JLabel l5 = new JLabel("Swimming Pool : ");
        l5.setBounds(30,320,150,30);
        l5.setForeground(Color.WHITE);
        l5.setFont(new Font("Poppins", Font.PLAIN, 16));
        add(l5);
        
        JLabel l15 = new JLabel();
        l15.setBounds(250,320,200,30);
        l15.setForeground(Color.WHITE);
        l15.setFont(new Font("Poppins", Font.PLAIN, 16));
        add(l15);
        
        JLabel l6 = new JLabel("Food Included : ");
        l6.setBounds(30,380,150,30);
        l6.setForeground(Color.WHITE);
        l6.setFont(new Font("Poppins", Font.PLAIN, 16));
        add(l6);
        
        JLabel l16 = new JLabel();
        l16.setBounds(250,380,200,30);
        l16.setForeground(Color.WHITE);
        l16.setFont(new Font("Poppins", Font.PLAIN, 16));
        add(l16);    
        
        JLabel l10 = new JLabel("Total Cost : ");
        l10.setBounds(30,440,200,30);
        l10.setForeground(Color.WHITE);
        l10.setFont(new Font("Poppins", Font.PLAIN, 16));
        add(l10);
        
        JLabel l20 = new JLabel();
        l20.setBounds(250,440,200,30);
        l20.setForeground(Color.WHITE);
        l20.setFont(new Font("Poppins", Font.PLAIN, 16));
        add(l20);
        
        try
        {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bookhotel where username = '"+username+"'");
            while(rs.next())
            {
                l11.setText(rs.getString(1));
                l12.setText(rs.getString(2));
                l13.setText(rs.getString(3));
                l14.setText(rs.getString(4));
                l15.setText(rs.getString(5));
                l16.setText(rs.getString(6));
                l20.setText(rs.getString(7));
            }
        }catch (Exception e)
        {
            
        }
        
        
        deleteButton = new JButton("Delete Package");
        deleteButton.setBackground(new Color(82, 94, 183));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBounds(350, 500, 156, 50);
        deleteButton.setFont(new Font("Poppins", Font.PLAIN, 15));
        deleteButton.addActionListener(this);
        deleteButton.setBorderPainted(false);
        add(deleteButton);
        
        b1 = new JButton("BACK");
        b1.setBackground(new Color(82, 94, 183));
        b1.setForeground(Color.WHITE);
        b1.setBounds(150,500,156, 50);
        b1.setFont(new Font("Poppins",Font.PLAIN,15));
        b1.addActionListener(this);
        b1.setBorderPainted(false);
        add(b1);
        
        
    }
    
    
    private void deletePackage() {
        try {
            Conn c = new Conn();
            String query = "DELETE FROM bookhotel WHERE username = '" + username + "'";
            c.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Package Deleted Successfully!");
            this.setVisible(false);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately, show a user-friendly error message
        }
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deleteButton) {
            deletePackage();
        } else if (ae.getSource() == b1) {
            this.setVisible(false);
        }
    }
    
    public static void main(String[] args)
    {
        new ViewBookedHotel("").setVisible(true);
    }
}