import java.util.List;

public interface ProductCatalog {
    List<Product> getAllProducts();
    Product getProductByName(String name);
    void addProduct(Product product, String sku); 
}
