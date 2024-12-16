/* Note: No designs yet; please send back code when functionality is added 
 * for final design retouching.
 *
 * Note 2: Will send setup page within tomorrow.
 */
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;

public class SettingsPage {
    private static String currentDirectory = System.getProperty("user.dir");

    public static void start() {
        Configuration conf = new Configuration();
        String booksDirectory = conf.getBooksdir();
        if (booksDirectory != null) {
            currentDirectory = booksDirectory;
        }
        // Main frame
        JFrame frame = new JFrame("Settings - Change Directory");
        frame.setSize(530, 150);
        frame.setLayout(null);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setBackground(new Color(68, 68, 68, 255));
        

        // Directory Label
        JLabel directoryLabel = new JLabel("Current Directory:");
        directoryLabel.setFont(Fonts.getFont(12));
        directoryLabel.setBounds(20, 30, 120, 30);
        frame.add(directoryLabel);

        // Current Directory
        JTextField directoryField = new JTextField();
        directoryField.setBounds(150, 30, 250, 30);
        directoryField.setEditable(false);
        directoryField.setText(currentDirectory);
        frame.add(directoryField);

        // Browse Button
        RoundedButton browseButton = new RoundedButton(30);
        browseButton.setText("Browse");
        browseButton.setFont(Fonts.getFont(12));
        browseButton.setBounds(410, 30, 90, 30);
        browseButton.setBackground(new Color(47, 47, 47, 255));
        browseButton.setForeground(new Color(74, 198, 148, 255));
        browseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Swap colors when mouse enters
                Color bg = browseButton.getBackground();
                Color fg = browseButton.getForeground();
                browseButton.setBackground(fg);
                browseButton.setForeground(bg);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Swap colors back when mouse exits
                Color bg = browseButton.getBackground();
                Color fg = browseButton.getForeground();
                browseButton.setBackground(fg);
                browseButton.setForeground(bg);
            }
        });
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Only directories
                int option = fileChooser.showOpenDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    String selectedDir = fileChooser.getSelectedFile().getAbsolutePath();
                    directoryField.setText(selectedDir); // Update text field with selected directory
                }
            }
        });
        frame.add(browseButton);

        // Save Button
        RoundedButton saveButton = new RoundedButton(30);
        saveButton.setText("Save");
        saveButton.setFont(Fonts.getFont(12));
        saveButton.setBounds(150, 100, 80, 30);
        frame.add(saveButton);
        saveButton.setBackground(new Color(47, 47, 47, 255));
        saveButton.setForeground(new Color(74, 198, 148, 255));
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Swap colors when mouse enters
                Color bg = saveButton.getBackground();
                Color fg = saveButton.getForeground();
                saveButton.setBackground(fg);
                saveButton.setForeground(bg);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Swap colors back when mouse exits
                Color bg = saveButton.getBackground();
                Color fg = saveButton.getForeground();
                saveButton.setBackground(fg);
                saveButton.setForeground(bg);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDir = directoryField.getText();
                if (!selectedDir.isEmpty() && new File(selectedDir).exists()) {
                    currentDirectory = selectedDir;
                    conf.setBookDir(selectedDir);
                    JOptionPane.showMessageDialog(frame, "Directory saved: " + selectedDir);
                    frame.dispose();
                    SelectionMenu.start();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid directory selected!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Cancel Button
        RoundedButton cancelButton = new RoundedButton(30);
        cancelButton.setText("Cancel");
        cancelButton.setFont(Fonts.getFont(12));
        cancelButton.setBounds(280, 100, 80, 30);
        frame.add(cancelButton);
        cancelButton.setBackground(new Color(47, 47, 47, 255));
        cancelButton.setForeground(new Color(74, 198, 148, 255));
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Swap colors when mouse enters
                Color bg = cancelButton.getBackground();
                Color fg = cancelButton.getForeground();
                cancelButton.setBackground(fg);
                cancelButton.setForeground(bg);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Swap colors back when mouse exits
                Color bg = cancelButton.getBackground();
                Color fg = cancelButton.getForeground();
                cancelButton.setBackground(fg);
                cancelButton.setForeground(bg);
            }
        });
        cancelButton.addActionListener(e -> {
            frame.dispose();
            SelectionMenu.start();
            });

        frame.setVisible(true);
    }
}
