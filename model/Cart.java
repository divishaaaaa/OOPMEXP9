package smartcart.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addProduct(Product p, int qty) {
        for (CartItem ci : items) {
            if (ci.getProduct().getId() == p.getId()) {
                ci.setQuantity(ci.getQuantity() + qty);
                return;
            }
        }
        items.add(new CartItem(p, qty));
    }

    public void removeProduct(int productId) {
        items.removeIf(ci -> ci.getProduct().getId() == productId);
    }

    public List<CartItem> getItems() { return items; }

    public double getSubtotal() {
        double s = 0.0;
        for (CartItem ci : items) s += ci.getTotalPrice();
        return s;
    }

    public void clear() { items.clear(); }
}
