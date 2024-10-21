package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.TitledBorder;

public class Dashboard extends JFrame implements ActionListener {
    JButton[] buttons = new JButton[12]; // Updated array length
    String username;

    Dashboard(String username) {
        setTitle("Dashboard");
        this.username = username;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        getContentPane().setBackground(new Color(40, 44, 52));


        // Panel 2
        JPanel p2 = new JPanel();
        p2.setBounds(0, 0, 300, 800);
        p2.setBackground(new Color(33, 33, 33));
        p2.setLayout(new GridLayout(12, 1, 0, 10)); // Updated grid layout
        add(p2);
        
        // Add a label on top of the buttons panel
        JLabel titleLabell = new JLabel("Travel Planner");
        titleLabell.setForeground(Color.WHITE);
        titleLabell.setFont(new Font("Poppins", Font.BOLD, 15));
        titleLabell.setHorizontalAlignment(SwingConstants.CENTER);
        p2.add(titleLabell);
        
        
        // Add an empty label to move down the buttons
        JLabel emptyLabel = new JLabel();
        p2.add(emptyLabel);
        
        // Add an empty label to move down the buttons
        JLabel emptyLabel1 = new JLabel();
        p2.add(emptyLabel);
        
        // Add an empty label to move down the buttons
        JLabel emptyLabel2 = new JLabel();
        p2.add(emptyLabel);
        
        // Add an empty label to move down the buttons
        JLabel emptyLabel3 = new JLabel();
        p2.add(emptyLabel);

        // Buttons
        String[] buttonLabels = {"Update Personal Details", "View Personal Details", "Check Package",
                "Book Package", "View Package", "Book Hotels", "View Booked Hotel", "Log Out"};

        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setBackground(new Color(40, 44, 52));
            buttons[i].setForeground(new Color(180, 180, 167));
            buttons[i].setFont(new Font("Poppins", Font.PLAIN, 14));
            buttons[i].setPreferredSize(new Dimension(200, 40));
            buttons[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            buttons[i].addActionListener(this);
            p2.add(buttons[i]);
        }
        
        // Right Panel
        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(300, 0, 1300, 800);
        rightPanel.setBackground(new Color(22, 22, 19));
        rightPanel.setLayout(null);
        add(rightPanel);
        
        
        // Welcome message
        JLabel welcomeLabel = new JLabel("Hello Traveler!");
        welcomeLabel.setBounds(100, 30, 600, 30);
        welcomeLabel.setFont(new Font("Poppins", Font.BOLD, 25));
        welcomeLabel.setForeground(Color.WHITE);
        rightPanel.add(welcomeLabel);

        // Additional content
        JLabel contentLabel1 = new JLabel("Welcome to your Travel Planner Dashboard. Your journey begins here, where you can seamlessly plan and organize your adventures. ");
        contentLabel1.setBounds(100, 60, 900, 20);
        contentLabel1.setFont(new Font("Poppins", Font.BOLD, 12));
        contentLabel1.setForeground(new Color(214, 214, 214));
        rightPanel.add(contentLabel1);

        // Dynamic Travel Map Panel
        JPanel mapPanel = new JPanel();
        mapPanel.setBounds(100, 120, 500, 200);
        mapPanel.setBackground(new Color(51, 51, 51));
        mapPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(255, 87, 34), 0),
                "Dynamic Travel Map",
                Font.BOLD,
                Font.PLAIN,
                new Font("Poppins", Font.PLAIN, 12),
                Color.WHITE
        ));
        mapPanel.setLayout(new BorderLayout());
        rightPanel.add(mapPanel, BorderLayout.CENTER);

        // Image Slideshow
        String[] imagePaths = {"path/to/image1.jpg", "path/to/image2.jpg", "path/to/image3.jpg"};
        ImageSlideshow slideshow = new ImageSlideshow(imagePaths);
        mapPanel.add(slideshow);

        // Upcoming Trips Panel
        JPanel upcomingTripsPanel = new JPanel();
        upcomingTripsPanel.setBounds(640, 560, 500, 200);
        upcomingTripsPanel.setBackground(new Color(51, 51, 51));
        upcomingTripsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(255, 87, 34), 0),
                "Upcoming Trips",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Poppins", Font.PLAIN, 12),
                Color.WHITE
        ));
        upcomingTripsPanel.setLayout(new BorderLayout());
        rightPanel.add(upcomingTripsPanel);

        // Create a table to display trip information
        String[] columnNames = {"Destination", "Travel Dates", "Status"};
        Object[][] tripData = {
        {"Anuradhapura,Sri Lanka", "2023-10-15 to 2023-10-22", "Booked"},
        {"Polonnaruwa,Sri Lanka", "2023-11-05 to 2023-11-10", "Confirmed"},
        {"Kandy,Sri Lanka", "2024-01-20 to 2024-01-30", "Pending"},
        {"Galle,Sri Lanka", "2024-01-20 to 2024-01-30", "Confirmed"},
        {"Mathara,Sri Lanka", "2024-01-20 to 2024-01-30", "Confirmed"},
        {"Unawatuna,Sri Lanka", "2024-01-20 to 2024-01-30", "Confirmed"},
        {"Matale,Sri Lanka", "2024-01-20 to 2024-01-30", "Confirmed"},
        {"Dabulla,Sri Lanka", "2024-01-20 to 2024-01-30", "Confirmed"},
        {"Colombo,Sri Lanka", "2024-01-20 to 2024-01-30", "Confirmed"},
        {"Jaffna,Sri Lanka", "2024-01-20 to 2024-01-30", "Pending"}
        };

                JTable tripTable = new JTable(tripData, columnNames);
        tripTable.setBackground(new Color(51, 51, 51));
        tripTable.setForeground(Color.WHITE);
        tripTable.setFont(new Font("Poppins", Font.PLAIN, 12));
        tripTable.getTableHeader().setBackground(new Color(57, 71, 147));
        tripTable.getTableHeader().setForeground(Color.WHITE);

        JScrollPane tripScrollPane = new JScrollPane(tripTable);
        upcomingTripsPanel.add(tripScrollPane, BorderLayout.CENTER);



        // Travel Budget Tracker Panel
        JPanel budgetPanel = new JPanel();
        budgetPanel.setBounds(100, 560, 500, 200);
        budgetPanel.setBackground(new Color(51, 51, 51));
        budgetPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(255, 87, 34), 0),
                "Travel Budget Tracker",
                Font.BOLD,
                Font.PLAIN,
                new Font("Poppins", Font.PLAIN, 12),
                Color.WHITE
        ));
        budgetPanel.setLayout(new BoxLayout(budgetPanel, BoxLayout.Y_AXIS)); // Change layout to BoxLayout
        rightPanel.add(budgetPanel);

        // Add dummy components for budget tracker
        JLabel budgetLabel = new JLabel("Budget Remaining: Rs 47,500.00");
        budgetLabel.setForeground(Color.WHITE);

        // Add more dummy text
        JLabel expenseLabel = new JLabel("Recent Expenses:");
        expenseLabel.setForeground(Color.WHITE);

        // Dummy expense items (you can add more as needed)
        JLabel expenseItem1 = new JLabel("1. Dinner - Rs 500.00");
        expenseItem1.setForeground(Color.WHITE);

        JLabel expenseItem2 = new JLabel("2. Taxi - Rs 200.00");
        expenseItem2.setForeground(Color.WHITE);

        // Add more dummy text
        JLabel suggestionLabel = new JLabel("Suggestions:");
        suggestionLabel.setForeground(Color.WHITE);

        // Dummy suggestions (you can add more as needed)
        JLabel suggestion1 = new JLabel("1. Consider cheaper accommodation options.");
        suggestion1.setForeground(Color.WHITE);

        JLabel suggestion2 = new JLabel("2. Look for budget-friendly activities.");
        suggestion2.setForeground(Color.WHITE);

        // JProgressBar
        JProgressBar progressBar = new JProgressBar();
        budgetPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        progressBar.setValue(70);
        progressBar.setStringPainted(true);

        // JButton
        JButton addExpenseButton = new JButton("Add Expense");
        addExpenseButton.setBackground(new Color(255, 87, 34));
        addExpenseButton.setForeground(Color.WHITE);

        // Add components to the budgetPanel
        budgetPanel.add(budgetLabel);
        budgetPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
        budgetPanel.add(expenseLabel);
        budgetPanel.add(expenseItem1);
        budgetPanel.add(expenseItem2);
        budgetPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
        budgetPanel.add(suggestionLabel);
        budgetPanel.add(suggestion1);
        budgetPanel.add(suggestion2);
        budgetPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
        budgetPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
        budgetPanel.add(progressBar);
        budgetPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
        budgetPanel.add(addExpenseButton);


        // Travel Journal Panel
        JPanel journalPanel = new JPanel();
        journalPanel.setBounds(100, 340, 500, 200); 
        journalPanel.setBackground(new Color(51, 51, 51));
        journalPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(255, 87, 34), 0),
                "Travel Journal Panel",
                Font.BOLD,
                Font.PLAIN,
                new Font("Poppins", Font.PLAIN, 12),
                Color.WHITE
        ));
        journalPanel.setLayout(new BoxLayout(journalPanel, BoxLayout.Y_AXIS));
        rightPanel.add(journalPanel);

        // Add a few dummy journal entries
        for (int i = 0; i < 3; i++) {
            JPanel entryItem = new JPanel();
            entryItem.setBackground(new Color(17, 21, 44));
            entryItem.setPreferredSize(new Dimension(280, 80));

            // You can add JLabels or other components here for entry details
            JLabel dateLabel = new JLabel("Date: 2023-10-01");
            dateLabel.setForeground(new Color(255, 255, 255)); // Set your preferred color

            JLabel locationLabel = new JLabel("Location: Anuradhapura, Sri Lanka");
            locationLabel.setForeground(new Color(255, 255, 255));
            
            JLabel entryTextLabel = new JLabel("Enjoyed my time exploring the beautiful city!");
            entryTextLabel.setForeground(new Color(255, 255, 255));

            entryItem.add(dateLabel);
            entryItem.add(locationLabel);
            entryItem.add(entryTextLabel);

            journalPanel.add(entryItem);
        }

        // Travel Community Forum Panel
        JPanel forumPanel = new JPanel();
        forumPanel.setBounds(640, 120, 500, 420);
        forumPanel.setBackground(new Color(51, 51, 51));
        forumPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255), 0),
                "Travel Community Forum",
                Font.PLAIN,
                Font.PLAIN,
                new Font("Poppins", Font.BOLD, 14),
                new Color(51, 51, 51)
        ));
        forumPanel.setLayout(null);
        rightPanel.add(forumPanel);

        // Add a title label
        JLabel titleLabel00 = new JLabel("Travel Community Forum");
        titleLabel00.setBounds(10, 20, 480, 30);
        titleLabel00.setFont(new Font("Poppins", Font.BOLD, 14));
        titleLabel00.setForeground(new Color(255, 255, 255));
        forumPanel.add(titleLabel00);

        // Add a scrollable area for forum posts
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 60, 480, 300);
        scrollPane.setBackground(new Color(31, 31, 31));
        forumPanel.add(scrollPane);

        // Add a panel to hold forum posts
        JPanel postsPanel = new JPanel();
        postsPanel.setBackground(new Color(31, 31, 31));
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));

        // Add dummy forum posts
        for (int i = 0; i < 5; i++) {
            JPanel postItem = new JPanel();
            postItem.setPreferredSize(new Dimension(480, 80));
            postItem.setBackground(new Color(31, 31, 31));
            postItem.setBorder(BorderFactory.createLineBorder(new Color(60, 82, 51)));

            // You can add JLabels or other components here for post details
            JLabel authorLabel = new JLabel("Susith Deshan Alwis");
            JLabel postTitleLabel = new JLabel("Title: Best Restaurants in Anuradhapura");
            JLabel contentLabel = new JLabel("Looking for recommendations on great restaurants in Anuradhapura!");

            authorLabel.setForeground(new Color(255, 255, 255));
            postTitleLabel.setForeground(new Color(255, 255, 255));
            contentLabel.setForeground(new Color(255, 255, 255));

            postItem.add(authorLabel);
            postItem.add(postTitleLabel);
            postItem.add(contentLabel);

            postsPanel.add(postItem);
        }

        scrollPane.setViewportView(postsPanel);

        // Add a button for creating a new post
        JButton newPostButton = new JButton("New Post");
        newPostButton.setBounds(10, 380, 100, 30);
        newPostButton.setBackground(new Color(60, 82, 51));
        newPostButton.setForeground(new Color(255, 255, 255));
        newPostButton.setBorderPainted(false);
        forumPanel.add(newPostButton);
        newPostButton.addActionListener(this);
    }
    
    
    public void actionPerformed(ActionEvent ae) {
        JButton sourceButton = (JButton) ae.getSource();

        switch (sourceButton.getText()) {
            case "Add Personal Details":
                new AddCustomer().setVisible(true);
                break;
            case "View Personal Details":
                new ViewCustomer(username).setVisible(true);
                break;
            case "Check Package":
                new CheckPackage().setVisible(true);
                break;
            case "Book Package":
                new BookPackage(username).setVisible(true);
                break;
            case "View Package":
                new ViewPackage(username).setVisible(true);
                break;
            case "Book Hotels":
                new BookHotel(username).setVisible(true);
                break;
            case "View Booked Hotel":
                new ViewBookedHotel(username).setVisible(true);
                break;
            case "Log Out":
                this.dispose();
                new Login().setVisible(true);
                break;
        }
    }

    public static void main(String[] args) {
        new Dashboard("").setVisible(true);
        
        
    }
    
    class ImageSlideshow extends JPanel implements ActionListener {
    private Timer timer;
    private int currentIndex;
    private String[] imagePaths;

    ImageSlideshow(String[] imagePaths) {
        this.imagePaths = imagePaths;
        this.currentIndex = 0;

        // Set a timer to change the image every 3 seconds (adjust as needed)
        timer = new Timer(3000, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon imageIcon = new ImageIcon(imagePaths[currentIndex]);
        Image image = imageIcon.getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentIndex = (currentIndex + 1) % imagePaths.length;
        repaint();
    }
}
}
