import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ShoppingCartTest {
    private ShoppingCart cart;
    private Product product1;
    private Product product2;
    private User user;

    @Before
    public void setUp() {
        // Initialize the shopping cart
        cart = ShoppingCart.getInstance();
        // Clear the cart before each test
        cart.getItems().clear();
        
        // Initialize some products and a user
        product1 = new Product("Laptop", 1000.0, "ELEC001");
        product2 = new Product("T-Shirt", 20.0, "CLTH001");
        user = new User("testuser", "password");
    }

    @Test
    public void testAddItem() {
        ShoppingCart cart = new ShoppingCart.Builder()
            .addItem(product1)
            .build();

        assertTrue(cart.getItems().contains(product1));
    }

    @Test
    public void testRemoveItem() {
        ShoppingCart cart = new ShoppingCart.Builder()
            .addItem(product1)
            .addItem(product2)
            .removeItem(product1)
            .build();

        assertFalse(cart.getItems().contains(product1));
        assertTrue(cart.getItems().contains(product2));
    }

    @Test
    public void testSetCustomerInfo() {
        ShoppingCart cart = new ShoppingCart.Builder()
            .setCustomerInfo("testcustomer")
            .build();

        assertEquals("testcustomer", cart.getCustomerInfo());
    }

    @Test
    public void testSetUser() {
        ShoppingCart cart = new ShoppingCart.Builder()
            .setUser(user)
            .build();

        assertEquals(user, cart.getUser());
    }

    @Test
    public void testSingleton() {
        ShoppingCart cart1 = ShoppingCart.getInstance();
        ShoppingCart cart2 = ShoppingCart.getInstance();

        assertSame(cart1, cart2);
    }
}
