
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
 
public class Fonts {
    public static Font getTitleFont() {
        Font customFont = null;
        try {
            /* Change if needed or add filepath tracking method for portability */
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/JacquesFrancois-Regular.ttf")); // change path when needed
            // Set the size of the font
            customFont = customFont.deriveFont(36f); // Size 36
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return customFont;
    }

    public static Font getRegFont() {
        Font customFont = null;
        try {
            /* Change if needed or add filepath tracking method for portability */
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/JacquesFrancois-Regular.ttf")); // change path when needed
            // Set the size of the font
            customFont = customFont.deriveFont(20f); // Size 20
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return customFont;
    }
}

