import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Authentication {
    private File passwordFile;
    private static volatile Authentication instance = null;

    private Authentication() {
        this.passwordFile = new File("password.txt");

        try {
            if (!passwordFile.exists()) {
                passwordFile.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Authentication getInstance() {
        if (instance == null) {
            synchronized(Authentication.class) {
                if (instance == null) {
                    instance = new Authentication();
                }
            }
        }
        return instance;
    }

    public boolean authenticate(String password) {
        try (BufferedReader bf = new BufferedReader(new FileReader(passwordFile))) {
            return bf.readLine().trim().equals(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean passwordExists() {
        try (BufferedReader bf = new BufferedReader(new FileReader(passwordFile))) {
            return bf.readLine() != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setPassword(String password) {
        try (FileWriter fw = new FileWriter(passwordFile)) {
            fw.write(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
