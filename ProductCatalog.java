import java.util.List;

public interface ProductCatalog {
    void addProduct(Product product);
    List<Product> getProducts();
    Product getProductByName(String name);
}
