import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        Authentication auth = Authentication.getInstance();

        if (auth.passwordExists()) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        new LoginPage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        new RegisterPage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
