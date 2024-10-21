package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class AdminDashboard extends JFrame implements ActionListener {

    JButton viewBookingsButton, managePackagesButton, manageHotelsButton, logoutButton, reportButton, reportButton1;
    JTextArea resultTextArea;

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setBounds(400, 150, 1500, 800);  // Updated window size
        getContentPane().setBackground(new Color(6, 7, 15));
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel dashboardLabel = new JLabel("Admin Dashboard");
        dashboardLabel.setBounds(50, 60, 300, 30);  // Adjusted label position
        dashboardLabel.setFont(new Font("Poppins", Font.BOLD, 30));
        dashboardLabel.setForeground(Color.WHITE);
        add(dashboardLabel);

        viewBookingsButton = new JButton("View Bookings");
        viewBookingsButton.setBackground(new Color(82, 94, 183));
        viewBookingsButton.setForeground(Color.WHITE);
        viewBookingsButton.setBounds(50, 200, 200, 30);
        viewBookingsButton.setFont(new Font("Poppins", Font.PLAIN, 12));
        viewBookingsButton.addActionListener(this);
        viewBookingsButton.setBorderPainted(false);
        add(viewBookingsButton);

        managePackagesButton = new JButton("View Users");
        managePackagesButton.setBackground(new Color(82, 94, 183));
        managePackagesButton.setForeground(Color.WHITE);
        managePackagesButton.setBounds(50, 250, 200, 30);
        managePackagesButton.setFont(new Font("Poppins", Font.PLAIN, 12));
        managePackagesButton.addActionListener(this);
        managePackagesButton.setBorderPainted(false);
        add(managePackagesButton);

        manageHotelsButton = new JButton("View Hotels");
        manageHotelsButton.setBackground(new Color(82, 94, 183));
        manageHotelsButton.setForeground(Color.WHITE);
        manageHotelsButton.setBounds(50, 300, 200, 30);
        manageHotelsButton.setFont(new Font("Poppins", Font.PLAIN, 12));
        manageHotelsButton.addActionListener(this);
        manageHotelsButton.setBorderPainted(false);
        add(manageHotelsButton);
        
        JLabel dashboardLabel2 = new JLabel("Generate Reports");
        dashboardLabel2.setBounds(50, 370, 300, 30);  // Adjusted label position
        dashboardLabel2.setFont(new Font("Poppins", Font.BOLD, 13));
        dashboardLabel2.setForeground(Color.WHITE);
        add(dashboardLabel2);
        
        reportButton = new JButton("Packages Booked");
        reportButton.setBackground(new Color(0, 45, 143));
        reportButton.setForeground(Color.WHITE);
        reportButton.setBounds(50, 420, 200, 30);
        reportButton.setFont(new Font("Poppins", Font.PLAIN, 12));
        reportButton.addActionListener(this);
        reportButton.setBorderPainted(false);
        add(reportButton);
        
        reportButton1 = new JButton("Hotels Booked");
        reportButton1.setBackground(new Color(0, 45, 143));
        reportButton1.setForeground(Color.WHITE);
        reportButton1.setBounds(50, 470, 200, 30);
        reportButton1.setFont(new Font("Poppins", Font.PLAIN, 12));
        reportButton1.addActionListener(this);
        reportButton1.setBorderPainted(false);
        add(reportButton1);

        logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(82, 94, 183));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBounds(50, 700, 200, 30);
        logoutButton.setFont(new Font("Poppins", Font.PLAIN, 12));
        logoutButton.addActionListener(this);
        logoutButton.setBorderPainted(false);
        add(logoutButton);

        resultTextArea = new JTextArea();
        resultTextArea.setBackground(new Color(28, 30, 50));
        resultTextArea.setForeground(Color.WHITE);
        resultTextArea.setFont(new Font("Poppins", Font.PLAIN, 14));
        resultTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        scrollPane.setBounds(300, 200, 1100, 500);  // Adjusted the scroll pane size
        add(scrollPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewBookingsButton) {
            displayBookings();
        } else if (e.getSource() == managePackagesButton) {
            displayPackages();
        } else if (e.getSource() == manageHotelsButton) {
            displayHotels();
        } else if (e.getSource() == reportButton) {
            generatePackageBookingReport(); // Call method for generating package booking report
        } else if (e.getSource() == reportButton1) {
            generateHotelBookingReport(); // Call method for generating hotel booking report
        } else if (e.getSource() == logoutButton) {
            dispose();
        }
    }

    private void displayBookings() {
        executeQuery("SELECT * FROM bookpackage");
    }

    private void displayPackages() {
        executeQuery("SELECT * FROM account");
    }

    private void displayHotels() {
        executeQuery("SELECT * FROM bookhotel");
    }

    private void generatePackageBookingReport() {
        try {
            // Path to your JRXML file for Package Bookings
            String jrxmlPath = "D:\\SY DOME\\OOPS_CP\\Travel-Planner-JavaBased-Application\\src\\Reports\\PKGBookingReport.jrxml";
            String jasperPath = "D:\\SY DOME\\OOPS_CP\\Travel-Planner-JavaBased-Application\\src\\Reports\\PKGBookingReport.jasper";
            JasperCompileManager.compileReportToFile(jrxmlPath, jasperPath);

            // Fill the report with data
            Connection conn = new Conn().c;
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperPath, null, conn);

            // Display the report
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            ex.printStackTrace();
            resultTextArea.setText("Error generating report: " + ex.getMessage());
        }
    }

    private void generateHotelBookingReport() {
        try {
            // Path to your JRXML file for Hotel Bookings
            String jrxmlPath = "D:\\SY DOME\\OOPS_CP\\Travel-Planner-JavaBased-Application\\src\\Reports\\HotelBookingReport.jrxml";
            String jasperPath = "D:\\SY DOME\\OOPS_CP\\Travel-Planner-JavaBased-Application\\src\\Reports\\HotelBookingReport.jasper";
            JasperCompileManager.compileReportToFile(jrxmlPath, jasperPath);

            // Fill the report with data
            Connection conn = new Conn().c;
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperPath, null, conn);

            // Display the report
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            ex.printStackTrace();
            resultTextArea.setText("Error generating report: " + ex.getMessage());
        }
    }

    private void executeQuery(String query) {
        try {
            Conn c = new Conn();
            Statement statement = c.s;
            ResultSet resultSet = statement.executeQuery(query);

            StringBuilder result = new StringBuilder();
            while (resultSet.next()) {
                if (query.equals("SELECT * FROM bookpackage")) {
                    result.append("Username: ").append(resultSet.getString("username")).append("\t");
                    result.append("Package: ").append(resultSet.getString("package")).append("\t");
                    result.append("Persons: ").append(resultSet.getString("persons")).append("\t");
                    result.append("Email: ").append(resultSet.getString("email")).append("\t");
                    result.append("Price: ").append(resultSet.getString("price")).append("\n");
                } else if (query.equals("SELECT * FROM account")) {
                    result.append("Username: ").append(resultSet.getString("username")).append("\n");
                } else if (query.equals("SELECT * FROM bookhotel")) {
                    result.append("Hotel: ").append(resultSet.getString("selectedHotelType")).append("\t");
                    result.append("Persons: ").append(resultSet.getString("numberOfPersons")).append("\t");
                    result.append("Days: ").append(resultSet.getString("totalDays")).append("\t");
                    result.append("Swimming Pool: ").append(resultSet.getString("swimmingpool")).append("\t");
                    result.append("Food: ").append(resultSet.getString("food")).append("\t");
                    result.append("\n");
                }
            }
            resultTextArea.setText(result.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            resultTextArea.setText("Error executing query: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new AdminDashboard().setVisible(true);
    }
}
