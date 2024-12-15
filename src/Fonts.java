import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class Fonts {
    public static Font getFont(float size) {
        Font customFont = null;
        try {
            /* Stumped at this part, create method to get parent directory for portability */
            File fontFile = new File("C:\\Users\\ivyda\\Documents\\Codes\\JLibReaderSwing\\src\\main\\resources\\fonts\\JacquesFrancois-Regular.ttf");
            // Create and derive the font
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            customFont = customFont.deriveFont(size); // Adjustable size for flexibility
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return customFont;
    }
}

