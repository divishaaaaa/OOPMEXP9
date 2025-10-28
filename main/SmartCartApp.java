package smartcart.main;

import javax.swing.SwingUtilities;
import smartcart.gui.LoginFrame;

public class SmartCartApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame();
        });
    }
}
