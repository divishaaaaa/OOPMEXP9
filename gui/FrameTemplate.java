package smartcart.gui;

import javax.swing.JFrame;

public class FrameTemplate extends JFrame {
    public FrameTemplate(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }
}
