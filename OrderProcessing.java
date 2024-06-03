import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.nio.file.*;
import java.util.List;

public class OrderProcessingTest {
    private OrderProcessing orderProcessing;
    private ShoppingCart cart;
    private Product product1;
    private Product product2;
    private User user;

    @Before
    public void setUp() {
        cart = ShoppingCart.getInstance();
        cart.getItems().clear();
        
        product1 = new Product("Laptop", 1000.0, "ELEC001");
        product2 = new Product("T-Shirt", 20.0, "CLTH001");
        user = new User("testuser", "password");

        cart = new ShoppingCart.Builder()
            .addItem(product1)
            .addItem(product2)
            .setUser(user)
            .build();

        orderProcessing = new OrderProcessing();
    }

    @Test
    public void testPlaceOrder() {
        // Capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        orderProcessing.placeOrder();

        String expectedOutput = "Order placed for testuser. Total amount: $1020.0";
        assertTrue(outContent.toString().contains(expectedOutput));

        // Check log file
        try {
            List<String> lines = Files.readAllLines(Paths.get("logging.txt"));
            assertTrue(lines.contains(expectedOutput));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
