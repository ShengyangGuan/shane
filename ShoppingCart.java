import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private static ShoppingCart instance = null;
    private List<Product> items;
    private String customerInfo;
    private User user;

    // Private constructor to prevent instantiation
    private ShoppingCart() {
        items = new ArrayList<>();
    }

    // Method to get the single instance of ShoppingCart (Singleton pattern)
    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    public List<Product> getItems() {
        return items;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public User getUser() {
        return user;
    }

    public static class Builder {
        private ShoppingCart cart;

        public Builder() {
            cart = ShoppingCart.getInstance();
        }

        public Builder addItem(Product product) {
            cart.items.add(product);
            return this;
        }

        public Builder removeItem(Product product) {
            cart.items.remove(product);
            return this;
        }

        public Builder setCustomerInfo(String customerInfo) {
            cart.customerInfo = customerInfo;
            return this;
        }

        public Builder setUser(User user) {
            cart.user = user;
            return this;
        }

        public ShoppingCart build() {
            return cart;
        }
    }
}
