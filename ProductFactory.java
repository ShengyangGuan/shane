public class ProductFactory {
    public static Product createProduct(String type, String name, double price, String sku) {
        switch (type) {
            case "Electronics":
                return new Electronics(name, price, sku);
            case "Clothing":
                return new Clothing(name, price, sku);
            default:
                throw new IllegalArgumentException("Unknown product type");
        }
    }
}
