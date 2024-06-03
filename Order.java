import java.util.List;

public class Order {
    private List<Product> items;
    private double totalPrice;

    public Order(List<Product> items) {
        this.items = items;
        this.totalPrice = calculateTotalPrice(items);
    }

    public List<Product> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private double calculateTotalPrice(List<Product> items) {
        double totalPrice = 0;
        for (Product item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order Summary:\n");
        for (Product item : items) {
            sb.append(item.getName()).append(" - Price: $").append(item.getPrice()).append("\n");
        }
        sb.append("Total Price: $").append(totalPrice).append("\n");
        return sb.toString();
    }
}
