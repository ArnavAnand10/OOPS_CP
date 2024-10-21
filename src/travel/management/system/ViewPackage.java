package travel.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class ViewPackage extends JFrame implements ActionListener
{
    JButton b1,b2,b3,deleteButton;
    private String username;
    
    ViewPackage(String username)
    {
        this.username = username;
        setTitle("Package Details");
        setBounds(580,250,1000,600);
        getContentPane().setBackground(new Color(6, 7, 15));
        setLayout(null);
        setLocationRelativeTo(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/bookedDetails.jpg"));
        Image i2 = i1.getImage().getScaledInstance(540,600,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l10 = new JLabel(i3);
        l10.setBounds(600,0,400,600);
        add(l10);
        
        JLabel heading = new JLabel("View Package Details");
        heading.setBounds(30, 40,300,25);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Poppins",Font.BOLD,24));
        add(heading);
        
        JLabel heading1 = new JLabel("Your Booked Package Details");
        heading1.setBounds(30, 70,300,25);
        heading1.setForeground(new Color(49, 64, 73));
        heading1.setFont(new Font("Poppins",Font.PLAIN,15));
        add(heading1);
        
        JLabel l1 = new JLabel("Username : ");
        l1.setBounds(30,140,200,30);
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("Poppins",Font.PLAIN,16));
        add(l1);
        
        JLabel l11 = new JLabel();
        l11.setBounds(250,140,200,30);
        l11.setForeground(Color.WHITE);
        l11.setFont(new Font("Poppins",Font.PLAIN,16));
        add(l11);
        
        JLabel l2 = new JLabel("Package : ");
        l2.setBounds(30,200,200,30);
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("Poppins",Font.PLAIN,16));
        add(l2);
        
        JLabel l12 = new JLabel();
        l12.setBounds(250,200,200,30);
        l12.setForeground(Color.WHITE);
        l12.setFont(new Font("Poppins",Font.PLAIN,16));
        add(l12);
        
        JLabel l3 = new JLabel("Persons : ");
        l3.setBounds(30,260,200,30);
        l3.setForeground(Color.WHITE);
        l3.setFont(new Font("Poppins",Font.PLAIN,16));
        add(l3);
        
        JLabel l13 = new JLabel();
        l13.setBounds(250,260,200,30);
        l13.setForeground(Color.WHITE);
        l13.setFont(new Font("Poppins",Font.PLAIN,16));
        add(l13);
        
        JLabel l5 = new JLabel("Email Address  : ");
        l5.setBounds(30,320,200,30);
        l5.setForeground(Color.WHITE);
        l5.setFont(new Font("Poppins",Font.PLAIN,16));
        add(l5);
        
        JLabel l15 = new JLabel();
        l15.setBounds(250,320,300,30);
        l15.setForeground(Color.WHITE);
        l15.setFont(new Font("Poppins",Font.PLAIN,16));
        add(l15);
        
        
        JLabel l7 = new JLabel("Price : ");
        l7.setBounds(30,440,200,30);
        l7.setForeground(Color.WHITE);
        l7.setFont(new Font("Poppins",Font.BOLD,20));
        add(l7);
        
        JLabel l17 = new JLabel();
        l17.setBounds(250,440,200,30);
        l17.setForeground(Color.WHITE);
        l17.setFont(new Font("Poppins",Font.BOLD,20));
        add(l17);
        
        try
        {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bookPackage where username = '"+username+"'");
            while(rs.next())
            {
                l11.setText(rs.getString(1));
                l12.setText(rs.getString(2));
                l13.setText(rs.getString(3));
                l15.setText(rs.getString(4));
                l17.setText(rs.getString(5));
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
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deleteButton) {
            deletePackage();
        } else if (ae.getSource() == b1) {
            this.setVisible(false);
        }
    }
    
    private void deletePackage() {
        try {
            Conn c = new Conn();
            String query = "DELETE FROM bookpackage WHERE username = '" + username + "'";
            c.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Package Deleted Successfully!");
            this.setVisible(false);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately, show a user-friendly error message
        }
    }
    
    public static void main(String[] args)
    {
        new ViewPackage("").setVisible(true);
    }
}
