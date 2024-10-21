package travel.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Conn {
    public Connection c; // Use private access modifier for better encapsulation
    public Statement s;

    public Conn() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded");

            // Establish Connection
            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tms", "root", "1234");
            System.out.println("Connection Established");

            // Create Statement object
            s = c.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Method to return the Connection object
    public Connection getConnection() {
        return c;
    }

    // Method to create a PreparedStatement
    public PreparedStatement prepareStatement(String query) throws SQLException {
        return c.prepareStatement(query);
    }
}
