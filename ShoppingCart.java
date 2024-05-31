import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> items;
    private String customerInfo;

    private ShoppingCart() {
        items = new ArrayList<>();
    }

    public List<Product> getItems() {
        return items;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public static class Builder {
        private ShoppingCart cart;

        public Builder() {
            cart = new ShoppingCart();
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

        public ShoppingCart build() {
            return cart;
        }
    }
}
