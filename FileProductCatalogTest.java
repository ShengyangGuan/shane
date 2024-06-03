import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.util.List;

public class FileProductCatalogTest {
    private FileProductCatalog catalog;

    @Before
    public void setUp() {
        // Create a temporary file with product data
        try (PrintWriter writer = new PrintWriter("test_products.txt")) {
            writer.println("Laptop,1000.0,ELEC001");
            writer.println("T-Shirt,20.0,CLTH001");
        } catch (IOException e) {
            e.printStackTrace();
        }
        catalog = new FileProductCatalog("test_products.txt");
    }

    @Test
    public void testLoadProductsFromFile() {
        List<Product> products = catalog.getAllProducts();
        assertEquals(2, products.size());
    }

    @Test
    public void testGetProductByName() {
        Product product = catalog.getProductByName("Laptop");
        assertNotNull(product);
        assertEquals("Laptop", product.getName());
        assertEquals(1000.0, product.getPrice(), 0.01);
        assertEquals("ELEC001", product.getSku());
    }

    @Test
    public void testAddProduct() {
        Product newProduct = new Product("Phone", 500.0, "ELEC002");
        catalog.addProduct(newProduct, newProduct.getSku());
        Product retrievedProduct = catalog.getProductByName("Phone");
        assertNotNull(retrievedProduct);
        assertEquals("Phone", retrievedProduct.getName());
        assertEquals(500.0, retrievedProduct.getPrice(), 0.01);
        assertEquals("ELEC002", retrievedProduct.getSku());
    }
}
