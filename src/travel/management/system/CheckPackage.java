package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.TitledBorder;

public class CheckPackage extends JFrame {

    CheckPackage() {
        setTitle("Travel Packages");
        setSize(1200, 600);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        String[][] packages = {
                {"GOLD PACKAGE", "6 Days and 7 Nights", "Airport Assistance",
                        "One day city tour", "Daily Buffet", "Welcome drinks on Arrival",
                        "Full Day 3 Island Cruise", "English Language Guide",
                        "BOOK NOW", "Summer Special Offer:", "Rs 12,000/-",
                        "Additional Info:", "Free Wi-Fi", "Luxury Accommodation", "24/7 Customer Support", "Spa and Wellness Center",
                        "Fitness Center Access", "Complimentary Yoga Classes", "Gourmet Dining Experience"},
                {"SILVER PACKAGE", "5 Days and 6 Nights", "Airport Assistance",
                        "Two day city tour", "Daily Buffet", "Welcome drinks on Arrival",
                        "Full Day 1 Island Cruise", "Night Safari",
                        "BOOK NOW", "Winter Special Offer:", "Rs 24,000/-",
                        "Additional Info:", "Outdoor Adventure Activities", "Swimming Pool Access", "Local Cuisine Experience", "Golf Course Access",
                        "Guided Nature Walks", "Live Music Entertainment", "Exclusive Spa Discounts"},
                {"BRONZE PACKAGE", "4 Days and 3 Nights", "Return Airfare",
                        "Free Clubbing", "Daily Buffet", "Welcome drinks on Arrival",
                        "Full Day 2 Island Cruise", "BBQ Dinner",
                        "BOOK NOW", "Summer Special Offer:", "Rs 32,000/-",
                        "Additional Info:", "City Tour Guide Included", "Historical Site Visits", "Shopping Discounts", "Car Rental Included",
                        "Beachside Bonfire Nights", "Cultural Dance Performances", "Wine Tasting Events"}
        };

        for (String[] pack : packages) {
            JPanel panel = createPackagePanel(pack);
            tabbedPane.addTab(pack[0], null, panel);
        }

        add(tabbedPane);
        setVisible(true);
    }

    private JPanel createPackagePanel(String[] pack) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(6, 7, 15));

        JPanel infoPanel = new JPanel(new GridLayout(4, 2, 10, 5));
        infoPanel.setBackground(new Color(6, 7, 15));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = createLabel("Package Name:", pack[0]);
        JLabel durationLabel = createLabel("Duration:", pack[1]);
        JLabel offerLabel = createLabel("Special Offer:", pack[9]);
        JLabel priceLabel = createLabel("Price:", pack[10]);

        infoPanel.add(nameLabel);
        infoPanel.add(durationLabel);
        infoPanel.add(offerLabel);
        infoPanel.add(priceLabel);

        // Create a panel for the Features section
        JPanel featuresPanel = createFeaturesPanel(pack);
        panel.add(featuresPanel, BorderLayout.EAST);

        
        
        JButton bookButton = createButton("Book Now", pack[8]);

        panel.add(createTitleLabel(pack[0]), BorderLayout.NORTH);
        panel.add(infoPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(bookButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createFeaturesPanel(String[] pack) {
        JPanel featuresPanel = new JPanel(new BorderLayout());
        featuresPanel.setBackground(new Color(6, 7, 15));
        featuresPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Add padding to the whole panel

        JLabel featuresLabel = new JLabel("Package Features");
        featuresLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        featuresLabel.setForeground(new Color(0, 79, 255));
        featuresLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));  // Add padding and border
        featuresPanel.add(featuresLabel, BorderLayout.NORTH);

        JTextArea featuresTextArea = createTextArea(pack, 2, pack.length - 5);
        featuresTextArea.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));  // Add padding to text area
        featuresPanel.add(featuresTextArea, BorderLayout.CENTER);

        // Add a titled border to the whole features panel
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(230, 90, 97)), ""
            );
            titledBorder.setTitleFont(new Font("Poppins", Font.BOLD, 14));  // Adjust title font if needed
            titledBorder.setTitleColor(new Color(230, 90, 97));
            titledBorder.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 100));  // Add padding to the titled border
            featuresPanel.setBorder(titledBorder);

                return featuresPanel;
    }

    private JLabel createLabel(String labelText, String value) {
        JLabel label = new JLabel("<html><b>" + labelText + "</b> " + value + "</html>");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Poppins", Font.PLAIN, 25));
        return label;
    }

    private JTextArea createTextArea(String[] pack, int startIndex, int endIndex) {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(new Color(6, 7, 15));
        textArea.setForeground(Color.WHITE);  // Set text color to white for better readability
        textArea.setFont(new Font("Poppins", Font.PLAIN, 14));

        for (int i = startIndex; i <= endIndex; i++) {
            textArea.append("• " + pack[i] + "\n");
        }

        return textArea;
    }

    private JButton createButton(String buttonText, String actionCommand) {
        JButton button = new JButton(buttonText);
        button.setBackground(new Color(82, 94, 183));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Poppins", Font.BOLD, 18)); // Increased font size
        button.setFocusPainted(false);
        button.setActionCommand(actionCommand);

        button.addActionListener(new ActionListener() {
            private String username;
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookPackage(username).setVisible(true);
            }
        });

        return button;
    }

    private JLabel createTitleLabel(String title) {
        JLabel titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        titleLabel.setForeground(new Color(112, 112, 112));
        titleLabel.setOpaque(true);  // Set to true to make background color visible
        titleLabel.setBackground(new Color(6, 7, 15));  // Set the background color
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Add padding
        return titleLabel;
    }

    public static void main(String[] args) {
        new CheckPackage();
    }
}
