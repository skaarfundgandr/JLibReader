import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

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

    public void setBookDir(String path) {
        try (BufferedReader bf = new BufferedReader(new FileReader(configFile))) {
            FileWriter fw = new FileWriter(configFile);
            String current;
            String out = "";
            while ((current = bf.readLine()) != null) {
                if (current.contains("Books: ")) {
                    out += "Books: " + path;
                } else {
                    out += current;
                }
            }
            if (!out.contains("Books: ")) {
                out += "Books: " + path;
            }
            fw.write(out);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getBooksdir() {
        try (BufferedReader bf = new BufferedReader(new FileReader(configFile))) {
            String currentLine;
            while ((currentLine = bf.readLine()) != null) {
                if (currentLine.contains("Books: ")) {
                    return currentLine.substring(currentLine.indexOf("Books:") + 6).trim();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
