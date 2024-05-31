public class Main {
    public static void main(String[] args) {
        Product phone = ProductFactory.createProduct("Electronics", "Smartphone", 699.99);
        Product shirt = ProductFactory.createProduct("Clothing", "T-Shirt", 19.99);

        ShoppingCart cart = new ShoppingCart.Builder()
                .addItem(phone)
                .addItem(shirt)
                .setCustomerInfo("John Doe")
                .build();

        System.out.println("Customer: " + cart.getCustomerInfo());
        System.out.println("Items in cart:");
        for (Product item : cart.getItems()) {
            System.out.println("- " + item.getName() + ": $" + item.getPrice());
        }
    }
}
