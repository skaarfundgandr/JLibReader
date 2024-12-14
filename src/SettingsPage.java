/* Note: No designs yet; please send back code when functionality is added 
 * for final design retouching.
 * 
 * Note 2: Will send setup page within tomorrow.
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SettingsPage {
    private static String currentDirectory = System.getProperty("user.dir");
    private static File configFile = new File("settings.cfg");

    public static void start() {
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Main frame
        JFrame frame = new JFrame("Settings - Change Directory");
        frame.setSize(600, 200);
        frame.setLayout(null);

        // Directory Label
        JLabel directoryLabel = new JLabel("Current Directory:");
        directoryLabel.setBounds(20, 30, 120, 30); 
        frame.add(directoryLabel);

        // Current Directory 
        JTextField directoryField = new JTextField();
        directoryField.setBounds(150, 30, 250, 30); 
        directoryField.setEditable(false);
        directoryField.setText(currentDirectory);
        frame.add(directoryField);

        // Browse Button
        JButton browseButton = new JButton("Change");
        browseButton.setBounds(410, 30, 90, 30);
        frame.add(browseButton);
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

        // Save Button
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(150, 100, 80, 30);
        frame.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDir = directoryField.getText();
                if (!selectedDir.isEmpty() && new File(selectedDir).exists()) {
                    currentDirectory = selectedDir;
                    JOptionPane.showMessageDialog(frame, "Directory saved: " + selectedDir);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid directory selected!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Cancel Button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(250, 100, 80, 30);
        frame.add(cancelButton);
        cancelButton.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }
}
