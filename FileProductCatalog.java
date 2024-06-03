import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class FileProductCatalog implements ProductCatalog {
    private List<Product> products;
    private String fileName;

    public FileProductCatalog(String fileName) {
        this.fileName = fileName;
        loadProductsFromFile();
    }

    private void loadProductsFromFile() {
        products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    double price = Double.parseDouble(parts[1]);
                    String sku = parts[2];
                    products.add(new Product(name, price, sku));
                } else {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product getProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void addProduct(Product product, String sku) {
        // Check if the SKU already exists
        for (Product existingProduct : products) {
            if (existingProduct.getSku().equalsIgnoreCase(sku)) {
                throw new IllegalArgumentException("SKU '" + sku + "' already exists.");
            }
        }
        // If SKU is unique, add the product
        products.add(product);
        // Save the updated product list to file
        saveProductsToFile();
    }

    private void saveProductsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Product product : products) {
                bw.write(product.getName() + "," + product.getPrice() + "," + product.getSku());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
