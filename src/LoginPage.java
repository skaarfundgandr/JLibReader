import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

class BackgroundImage extends JComponent {
    private Image image;

    public BackgroundImage(Image image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Graphics2D g2d = (Graphics2D) g.create();

            // Set the opacity (alpha value: 0.0f to 1.0f)
            float opacity = 0.98f;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

            // Draw the background image
            g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);

            // Add black color to image for darkening
            g2d.setColor(new Color(0, 0, 0, 75)); // RGBA, alpha 75 for transparency
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.dispose();
        }
    }
}   

/* Note: The dimensions and positions used are referenced from the Figma design.
 * URL: https://www.figma.com/design/dVPP1iCdiqVwgDXwQPsxsL/JLibReader
 */
public class LoginPage {
    private JFrame loginFrame;

    /* Change if needed or add filepath tracking method for portability */ 
    private BufferedImage image = ImageIO.read(new File("resources/img/login-bg.jpg")); 

    LoginPage() throws IOException {
        // Create login frame 
        loginFrame = new JFrame("JLibReader");
        loginFrame.setSize(600, 482); // added 32px to height to compensate for title bar
        loginFrame.setResizable(false);
        loginFrame.setUndecorated(true);
        loginFrame.setContentPane(new BackgroundImage(image)); // set up background image
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create title label
        JLabel titleLabel = new JLabel("JLibReader");
        titleLabel.setSize(200, 75);
        titleLabel.setLocation(200, 76);
        titleLabel.setForeground(new Color(255, 255, 255, 255));
        titleLabel.setFont(Fonts.getFont(36));

        // Create login panel
        RoundedPanel loginPanel = new RoundedPanel(30);
        loginPanel.setSize(350, 240);
        loginPanel.setLocation(125, 151);
        loginPanel.setBackground(new Color(47, 47, 47, 255));
        loginPanel.setLayout(null);
        
        // Components inside login panel //
        // Label
        JLabel enterPass = new JLabel("Enter your password");
        enterPass.setSize(193, 39);
        enterPass.setLocation(78, 12);
        enterPass.setForeground(new Color(255, 255, 255, 255));
        enterPass.setFont(Fonts.getFont(20));

        // PasswordField
        RoundedPasswordField passField = new RoundedPasswordField(30);
        passField.setBounds(30, 60, 290, 52);
        passField.setBackground(new Color(68, 68, 68, 255));
        passField.setForeground(Color.WHITE);
        passField.setFont(Fonts.getFont(20));
        passField.setEchoChar('*');

        // Login Button
        RoundedButton loginButton = new RoundedButton(30);
        loginButton.setBounds(30, 140, 290, 52);
        loginButton.setText("Log in");
        loginButton.setFont(Fonts.getFont(20));
        loginButton.setBackground(new Color(47, 47, 47, 255));
        loginButton.setForeground(new Color(74, 198, 148, 255));
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Swap colors when mouse enters
                Color bg = loginButton.getBackground();
                Color fg = loginButton.getForeground();
                loginButton.setBackground(fg);
                loginButton.setForeground(bg);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Swap colors back when mouse exits
                Color bg = loginButton.getBackground();
                Color fg = loginButton.getForeground();
                loginButton.setBackground(fg);
                loginButton.setForeground(bg);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                // Launches another page
                loginFrame.setVisible(false);
                SelectionMenu.start();
            }
        });

        // Add panel components
        loginPanel.add(loginButton);
        loginPanel.add(enterPass);
        loginPanel.add(passField);
        
        // Login frame execution 
        loginFrame.add(titleLabel); // add title label to frame
        loginFrame.add(loginPanel); // add login panel to frame
        
        loginFrame.setVisible(true);
    } // LoginPage

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new LoginPage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
