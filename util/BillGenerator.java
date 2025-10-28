package smartcart.util;

import smartcart.model.Cart;

public class BillGenerator {
    private static final double GST_PERCENT = 18.0; // example

    public static String generateBillText(String customerName, Cart cart) {
        StringBuilder sb = new StringBuilder();
        sb.append("-------------------------------\n");
        sb.append("        SMARTCART BILL\n");
        sb.append("-------------------------------\n");
        sb.append("Customer: ").append(customerName).append("\n\n");
        sb.append(String.format("%-20s %5s %8s\n", "Item Name", "Qty", "Price"));
        sb.append("-------------------------------\n");
        cart.getItems().forEach(ci -> {
            sb.append(String.format("%-20s %5d %8.2f\n", ci.getProduct().getName(), ci.getQuantity(), ci.getTotalPrice()));
        });
        sb.append("-------------------------------\n");
        double subtotal = cart.getSubtotal();
        double gst = subtotal * GST_PERCENT / 100.0;
        double total = subtotal + gst;
        sb.append(String.format("Subtotal: %25.2f\n", subtotal));
        sb.append(String.format("GST (%.0f%%): %21.2f\n", GST_PERCENT, gst));
        sb.append("-------------------------------\n");
        sb.append(String.format("TOTAL: %28.2f\n", total));
        sb.append("-------------------------------\n");
        sb.append("Thank you for shopping!\n");
        return sb.toString();
    }
}
