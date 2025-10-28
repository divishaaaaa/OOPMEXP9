package smartcart.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import smartcart.model.Cart;
import smartcart.model.CartItem;

public class CartFrame extends FrameTemplate {
    private String customerName;
    private Cart cart;
    private DefaultTableModel model;
    private JTable table;

    public CartFrame(String customerName, Cart cart) {
        super("SmartCart - Cart");
        this.customerName = customerName;
        this.cart = cart;
        initUI();
        setVisible(true);
    }

    private void initUI() {
        setLayout(new BorderLayout());
        model = new DefaultTableModel(new Object[]{"Product ID","Name","Qty","Unit Price","Total"}, 0) {
            public boolean isCellEditable(int row, int col) { return col == 2; }
        };
        table = new JTable(model);
        refreshTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnUpdate = new JButton("Update Quantities");
        JButton btnRemove = new JButton("Remove Selected");
        JButton btnCheckout = new JButton("Checkout");

        btnUpdate.addActionListener(e -> {
            try {
                for (int i = 0; i < model.getRowCount(); i++) {
                    int pid = Integer.parseInt(model.getValueAt(i,0).toString());
                    int qty = Integer.parseInt(model.getValueAt(i,2).toString());
                    if (qty <= 0) throw new NumberFormatException();
                    for (CartItem ci : cart.getItems()) {
                        if (ci.getProduct().getId() == pid) ci.setQuantity(qty);
                    }
                }
                refreshTable();
                JOptionPane.showMessageDialog(CartFrame.this, "Cart updated.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(CartFrame.this, "Enter valid integer quantities.");
            }
        });

        btnRemove.addActionListener(e -> {
            int sel = table.getSelectedRow();
            if (sel == -1) { JOptionPane.showMessageDialog(CartFrame.this, "Select a row to remove."); return; }
            int pid = Integer.parseInt(model.getValueAt(sel,0).toString());
            cart.removeProduct(pid);
            refreshTable();
        });

        btnCheckout.addActionListener(e -> {
            if (cart.getItems().isEmpty()) {
                JOptionPane.showMessageDialog(CartFrame.this, "Cart is empty.");
                return;
            }
            new BillFrame(customerName, cart);
        });

        bottom.add(btnUpdate);
        bottom.add(btnRemove);
        bottom.add(btnCheckout);
        add(bottom, BorderLayout.SOUTH);
    }

    private void refreshTable() {
        model.setRowCount(0);
        for (CartItem ci : cart.getItems()) {
            model.addRow(new Object[]{ci.getProduct().getId(), ci.getProduct().getName(), ci.getQuantity(), ci.getProduct().getPrice(), ci.getTotalPrice()});
        }
    }
}
