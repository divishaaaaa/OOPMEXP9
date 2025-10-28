package smartcart.gui;

import javax.swing.*;
import java.awt.*;
import smartcart.model.Cart;
import smartcart.util.BillGenerator;
import smartcart.util.FileHandler;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BillFrame extends FrameTemplate {
    private String customerName;
    private Cart cart;

    public BillFrame(String customerName, Cart cart) {
        super("SmartCart - Bill");
        this.customerName = customerName;
        this.cart = cart;
        initUI();
        setVisible(true);
    }

    private void initUI() {
        setLayout(new BorderLayout());
        String billText = BillGenerator.generateBillText(customerName, cart);
        JTextArea ta = new JTextArea(billText);
        ta.setEditable(false);
        add(new JScrollPane(ta), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSave = new JButton("Save Bill");
        JButton btnDone = new JButton("Done (Clear Cart)");

        btnSave.addActionListener(e -> {
            String filename = "bill_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".txt";
            try {
                File f = FileHandler.saveBillToFile(filename, billText);
                JOptionPane.showMessageDialog(BillFrame.this, "Saved bill to: " + f.getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(BillFrame.this, "Error saving bill: " + ex.getMessage());
            }
        });

        btnDone.addActionListener(e -> {
            cart.clear();
            JOptionPane.showMessageDialog(BillFrame.this, "Order completed and cart cleared.\nThank you!");
            dispose();
        });

        bottom.add(btnSave);
        bottom.add(btnDone);
        add(bottom, BorderLayout.SOUTH);
    }
}
