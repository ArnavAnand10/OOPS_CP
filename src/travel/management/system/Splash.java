

package travel.management.system;

import javax.swing.*;
import java.awt.*;

public class Splash {
    public static void main(String[] args) {
        SplashFrame frame = new SplashFrame(); // object of the main frame
        frame.setVisible(true);

        int i, x = 1;
        for (i = 2; i <= 400; i += 10, x += 7) { // Reduced screen size
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setSize(i + x, i); // for setting the size of the frame
            try {
                Thread.sleep(8);
            } catch (Exception e) {
            }
        }

    }
}

// frame is coded inside this class
class SplashFrame extends JFrame implements Runnable // extends will make the scope of Jframe in the whole class
{
    Thread t1;

    JProgressBar progressBar;
    int progress = 0;

    SplashFrame() // constructor
    {
        setLayout(new BorderLayout());

        // Use a simple background color
        getContentPane().setBackground(new Color(6, 7, 15)); // Example: Dark Blue 39, 63, 39

        setUndecorated(true);

        // Add "Travel Planner" label in the center
        JLabel titleLabel = new JLabel("Travel Planner");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 36));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.CENTER);

        // Add "Loading..." label below "Travel Planner"
        JLabel loadingLabel = new JLabel("Loading.... Please Wait");
        loadingLabel.setForeground(Color.WHITE);
        loadingLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(loadingLabel, BorderLayout.SOUTH);

        // Add a progress bar
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(66, 77, 158)); // Adjust the color
        add(progressBar, BorderLayout.NORTH);

        t1 = new Thread(this);
        t1.start();
    }

    @Override
    public void run() {
        try {
            while (progress < 100) {
                Thread.sleep(50);
                progress++;
                progressBar.setValue(progress);
            }

            this.setVisible(false);

            Login l = new Login();
            l.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
