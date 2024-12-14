import java.io.File;
import java.nio.file.Path;

public class Configuration {
    private File configFile;

    public Configuration() {
        configFile = new File("settings.cfg");
        try {
            if (!configFile.exists()) {
                configFile.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBookDir() {

    }

    public String getBooksdir() {

    }
}
