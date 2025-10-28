package smartcart.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import smartcart.model.Cart;

public class LoginFrame extends FrameTemplate {
    public LoginFrame() {
        super("SmartCart - Login");
        initUI();
        setVisible(true);
    }

    private void initUI() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        JLabel lblName = new JLabel("Name:");
        JTextField tfName = new JTextField(20);
        JButton btnLogin = new JButton("Continue");

        gbc.gridx = 0; gbc.gridy = 0; p.add(lblName, gbc);
        gbc.gridx = 1; p.add(tfName, gbc);
        gbc.gridx = 1; gbc.gridy = 1; p.add(btnLogin, gbc);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = tfName.getText().trim();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Please enter your name.");
                    return;
                }
                // Open product catalog and pass a fresh cart
                Cart cart = new Cart();
                new ProductCatalogFrame(name, cart);
                dispose();
            }
        });

        add(p);
    }
}
