package smartcart.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import smartcart.model.Product;
import smartcart.model.Cart;

public class ProductCatalogFrame extends FrameTemplate {
    private String customerName;
    private Cart cart;
    private List<Product> products;

    public ProductCatalogFrame(String customerName, Cart cart) {
        super("SmartCart - Products");
        this.customerName = customerName;
        this.cart = cart;
        this.products = loadSampleProducts();
        initUI();
        setVisible(true);
    }

    private List<Product> loadSampleProducts() {
        List<Product> list = new ArrayList<>();
        list.add(new Product(1, "Shampoo", "Personal Care", 100.0));
        list.add(new Product(2, "Soap", "Personal Care", 30.0));
        list.add(new Product(3, "Toothpaste", "Personal Care", 120.0));
        list.add(new Product(4, "Notebook", "Stationery", 40.0));
        list.add(new Product(5, "Pen Pack", "Stationery", 60.0));
        list.add(new Product(6, "Headphones", "Electronics", 1200.0));
        return list;
    }

    private void initUI() {
        JPanel main = new JPanel(new BorderLayout());

        JLabel header = new JLabel("Welcome, " + customerName + "! Browse Products", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        main.add(header, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(0, 1, 8, 8));
        for (Product p : products) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
            row.add(new JLabel(p.getName() + " - " + p.getCategory() + " - ₹" + p.getPrice()));
            JTextField qtyField = new JTextField("1", 3);
            JButton addBtn = new JButton("Add to Cart");
            addBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        int q = Integer.parseInt(qtyField.getText().trim());
                        if (q <= 0) throw new NumberFormatException();
                        cart.addProduct(p, q);
                        JOptionPane.showMessageDialog(ProductCatalogFrame.this, "Added to cart: " + p.getName());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(ProductCatalogFrame.this, "Enter a valid positive integer quantity.");
                    }
                }
            });
            row.add(new JLabel("Qty:"));
            row.add(qtyField);
            row.add(addBtn);
            grid.add(row);
        }

        JScrollPane sp = new JScrollPane(grid);
        main.add(sp, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton viewCart = new JButton("View Cart");
        viewCart.addActionListener(ae -> new CartFrame(customerName, cart));
        bottom.add(viewCart);
        main.add(bottom, BorderLayout.SOUTH);

        add(main);
    }
}
