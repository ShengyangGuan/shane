public class ProductFactory {
    public static Product createProduct(String type, String name, double price) {
        switch (type) {
            case "Electronics":
                return new Electronics(name, price);
            case "Clothing":
                return new Clothing(name, price);
            default:
                throw new IllegalArgumentException("Unknown product type");
        }
    }
}
