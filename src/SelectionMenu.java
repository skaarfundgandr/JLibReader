/* Note: For the icon images and other resources that utilize files,
 * please change the directory or add methods that detect said resources
 * for portability purposes.
 * 
 * Note 2: No component yet for displaying books. Send code back with epub/pdf data grabber methods
 * in order to properly display books.
 */



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SelectionMenu {
    public static void start() {
        // Configuration to make app fullscreen
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        int screenWidth = gd.getDefaultConfiguration().getBounds().width;
        int screenHeight = gd.getDefaultConfiguration().getBounds().height;

        JFrame mainFrame = new JFrame("JLibReader");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(screenWidth, screenHeight);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(47, 47, 47, 255));
        mainPanel.setBounds(0, 0, screenWidth, screenHeight);
        mainPanel.setLayout(null);

        // Menu panel
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(68, 68, 68, 255));
        menuPanel.setBounds(-350, 0, 350, screenHeight);
        menuPanel.setLayout(null);
        
        /* Menu panel components */ 
        // App name label
        JLabel menuLabel = new JLabel("JLibReader");
        menuLabel.setBounds(115, 35, 216, 40);
        menuLabel.setFont(Fonts.getFont(26));
        menuLabel.setForeground(Color.WHITE);
        menuPanel.add(menuLabel);

        // Books section button
        JButton booksButton = new JButton("Books");
        booksButton.setBounds(0, 130, 350, 90);
        booksButton.setBackground(menuPanel.getBackground());
        booksButton.setFont(Fonts.getFont(28));
        booksButton.setForeground(Color.WHITE);
        menuPanel.add(booksButton);

        // Menu Toggle button
        ImageIcon menuIcon = new ImageIcon("resources/icons/Hamburger_icon_white.png");
        Image scaledImage = menuIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JButton menuButton = new JButton(scaledIcon);
        menuButton.setBounds(25, 35, 40, 40);
        menuButton.setFocusPainted(false);
        menuButton.setBorderPainted(false);
        menuButton.setContentAreaFilled(false);

        // Settings icon
        ImageIcon settingIcon = new ImageIcon("resources/icons/white-setting-icon.png");
        Image scaledSettingImage = settingIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon scaledSettingIcon = new ImageIcon(scaledSettingImage);
        JButton settingsButton = new JButton(scaledSettingIcon);
        settingsButton.setBounds(25, screenHeight - 100, 40, 40);
        settingsButton.setFocusPainted(false);
        settingsButton.setBorderPainted(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SettingsPage.start();
            }
        });
        menuPanel.add(settingsButton);

        // Add components in a specific order
        mainFrame.add(mainPanel);
        mainPanel.add(menuPanel);
        mainPanel.add(menuButton);

        mainPanel.setComponentZOrder(menuButton, 0);
        mainPanel.setComponentZOrder(menuPanel, 1);

        // Menu Bar slide transition
        Timer slideIn = new Timer(3, null);
        Timer slideOut = new Timer(3, null);
        slideIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentX = menuPanel.getX();
                if (currentX < 0) {
                    menuPanel.setBounds(currentX + 25, 0, 350, screenHeight);
                } else {
                    slideIn.stop();
                }
            }
        });
        slideOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentX = menuPanel.getX();
                if (currentX > -350) {
                    menuPanel.setBounds(currentX - 25, 0, 350, screenHeight);
                } else {
                    slideOut.stop();
                }
            }
        });

        // Menu button action
        menuButton.addActionListener(new ActionListener() {
            private boolean isMenuVisible = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isMenuVisible) {
                    slideOut.start();
                } else {
                    slideIn.start(); 
                }
                isMenuVisible = !isMenuVisible;
            }
        });

        mainFrame.setVisible(true);
    }
}
