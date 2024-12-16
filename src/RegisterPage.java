import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;   

/* Note: The dimensions and positions used are referenced from the Figma design.
 * URL: https://www.figma.com/design/dVPP1iCdiqVwgDXwQPsxsL/JLibReader
 */
public class RegisterPage {
    private JFrame registerFrame;
    private RoundedPasswordField passField;
    private RoundedPasswordField confirmPassField;

    /* Change if needed or add filepath tracking method for portability */ 
    private BufferedImage image = ImageIO.read(new File("resources/img/login-bg.jpg")); 

    RegisterPage() throws IOException {
        // Create login frame 
        registerFrame = new JFrame("JLibReader");
        registerFrame.setSize(600, 632); // added 32px to height to compensate for title bar
        registerFrame.setResizable(false);
        registerFrame.setContentPane(new BackgroundImage(image)); // set up background image
        registerFrame.setLocationRelativeTo(null);
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create title label
        JLabel titleLabel = new JLabel("JLibReader");
        titleLabel.setSize(200, 75);
        titleLabel.setLocation(200, 76);
        titleLabel.setForeground(new Color(255, 255, 255, 255));
        titleLabel.setFont(Fonts.getFont(36));

        // Create register panel
        RoundedPanel registerPanel = new RoundedPanel(30);
        registerPanel.setSize(350, 330);
        registerPanel.setLocation(125, 151);
        registerPanel.setBackground(new Color(47, 47, 47, 255));
        registerPanel.setLayout(null);
        
        // Components inside register panel //
        // Label
        JLabel enterPass = new JLabel("Enter your password");
        enterPass.setSize(193, 39);
        enterPass.setLocation(78, 12);
        enterPass.setForeground(new Color(255, 255, 255, 255));
        enterPass.setFont(Fonts.getFont(20));

        // PasswordField
        passField = new RoundedPasswordField(30);
        passField.setBounds(30, 60, 290, 52);
        passField.setBackground(new Color(68, 68, 68, 255));
        passField.setForeground(Color.WHITE);
        passField.setFont(Fonts.getFont(20));
        passField.setEchoChar('*');

        JLabel confirmPass = new JLabel("Confirm password");
        confirmPass.setSize(193, 39);
        confirmPass.setLocation(88, 122);
        confirmPass.setForeground(new Color(255, 255, 255, 255));
        confirmPass.setFont(Fonts.getFont(20));

        // PasswordField
        confirmPassField = new RoundedPasswordField(30);
        confirmPassField.setBounds(30, 175, 290, 52);
        confirmPassField.setBackground(new Color(68, 68, 68, 255));
        confirmPassField.setForeground(Color.WHITE);
        confirmPassField.setFont(Fonts.getFont(20));
        confirmPassField.setEchoChar('*');

        // register Button
        RoundedButton registerButton = new RoundedButton(30);
        registerButton.setBounds(30, 250, 290, 52);
        registerButton.setText("Register");
        registerButton.setFont(Fonts.getFont(20));
        registerButton.setBackground(new Color(47, 47, 47, 255));
        registerButton.setForeground(new Color(74, 198, 148, 255));
        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Swap colors when mouse enters
                Color bg = registerButton.getBackground();
                Color fg = registerButton.getForeground();
                registerButton.setBackground(fg);
                registerButton.setForeground(bg);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Swap colors back when mouse exits
                Color bg = registerButton.getBackground();
                Color fg = registerButton.getForeground();
                registerButton.setBackground(fg);
                registerButton.setForeground(bg);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                // Launches another page
                if (registerButtonClicked()) {
                    registerFrame.setVisible(false);
                    try {
                        new LoginPage();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Add panel components
        registerPanel.add(registerButton);
        registerPanel.add(enterPass);
        registerPanel.add(passField);
        registerPanel.add(confirmPass);
        registerPanel.add(confirmPassField);
        
        // register frame execution 
        registerFrame.add(titleLabel); // add title label to frame
        registerFrame.add(registerPanel); // add register panel to frame

        registerFrame.setVisible(true);
    } // registerPage

    private boolean registerButtonClicked() {
        Authentication auth = Authentication.getInstance();
        String password, confirmedPassword;

        password = passField.getText().trim();
        confirmedPassword = passField.getText().trim();

        if (password.isEmpty() || confirmedPassword.isEmpty()) {
            JOptionPane.showMessageDialog(registerFrame, "Password must not be empty");
            passField.setText("");
            confirmPassField.setText("");
        } else if (password.equals(confirmedPassword)) {
            auth.setPassword(new String(passField.getPassword()).trim());
            return true;
        } else {
            JOptionPane.showMessageDialog(registerFrame, "Passwords do not match!");
        }

        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new RegisterPage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

