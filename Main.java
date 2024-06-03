import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize product catalog
        ProductCatalog fileCatalog = new FileProductCatalog("products.txt");

        // Initialize user authentication system
        FileUserAuthentication fileUserAuthentication = new FileUserAuthentication("users.txt");

        // Register or login user
        UserInterface userInterface = new UserInterface(fileUserAuthentication);
        User user = userInterface.registerOrLoginUser();

        if (user == null) {
            System.out.println("Failed to register or login. Exiting...");
            return;
        }

        // List all products from the catalog
        System.out.println("Available Products:");
        for (Product product : fileCatalog.getAllProducts()) {
            System.out.println(product.getName() + " (SKU: " + product.getSku() + ") - $" + product.getPrice());
        }

        // Prompt user to add products to shopping cart
        Scanner scanner = new Scanner(System.in);
        ShoppingCart.Builder cartBuilder = new ShoppingCart.Builder();

        while (true) {
            System.out.print("Enter product name to add (or 'done' to finish): ");
            String productName = scanner.nextLine();
            if (productName.equalsIgnoreCase("done")) {
                break;
            }

            Product product = fileCatalog.getProductByName(productName);
            if (product == null) {
                System.out.println("Product not found in catalog.");
                continue;
            }

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            for (int i = 0; i < quantity; i++) {
                cartBuilder.addItem(product);
            }

            System.out.println("Product(s) added to cart.");
        }

        // Set customer information and user for the shopping cart
        ShoppingCart cart = cartBuilder.setCustomerInfo(user.getUsername())
                                        .setUser(user)
                                        .build();

        // Display cart items
        System.out.println("Cart Items:");
        for (Product item : cart.getItems()) {
            System.out.println(item.getName() + " - Price: $" + item.getPrice());
        }

        // Place order
        Order order = new Order(cart.getItems());
        System.out.println(order.toString());

        // Process payment
        PaymentGateway paymentGateway = new MockPaymentGateway();
        if (paymentGateway.processPayment(order.getTotalPrice())) {
            // Payment successful, log order
            Logger.log("Order placed by " + user.getUsername() + ": " + cart.getItems().size() + " items, Total price: $" + order.getTotalPrice());
            System.out.println("Payment successful. Order has been placed.");
        } else {
            // Payment failed
            System.out.println("Payment failed. Please try again.");
        }

        scanner.close();
    }
}
