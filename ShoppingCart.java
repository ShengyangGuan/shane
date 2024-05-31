public class ShoppingCart {
    private static ShoppingCart instance;

    private ShoppingCart() { }

    public static synchronized ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }
}
