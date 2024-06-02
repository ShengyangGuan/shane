public class Main {
    public static void main(String[] args) {
        // File-based catalog
        ProductCatalog fileCatalog = new FileProductCatalog("products.txt");

        // Add a new product to the catalog and save to file
        Product newProduct = ProductFactory.createProduct("Electronics", "Tablet", 299.99);
        fileCatalog.addProduct(newProduct);

        // Create a shopping cart using the file-based catalog
        ShoppingCart cart = new ShoppingCart.Builder()
                .addItem(fileCatalog.getProductByName("Smartphone"))
                .addItem(fileCatalog.getProductByName("T-Shirt"))
                .addItem(fileCatalog.getProductByName("Tablet"))
                .setCustomerInfo("John Doe")
                .build();

        System.out.println("Customer: " + cart.getCustomerInfo());
        System.out.println("Items in cart:");
        for (Product item : cart.getItems()) {
            System.out.println("- " + item.getName() + ": $" + item.getPrice());
        }
    }
}
