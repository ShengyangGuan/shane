import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileProductCatalog implements ProductCatalog {
    private List<Product> products = new ArrayList<>();
    private String fileName;

    public FileProductCatalog(String fileName) {
        this.fileName = fileName;
        loadProductsFromFile();
    }

    private void loadProductsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String type = parts[0];
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    Product product = ProductFactory.createProduct(type, name, price);
                    products.add(product);
                    System.out.println("Loaded product: " + product.getName() + " - $" + product.getPrice());
                } else {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveProductsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Product product : products) {
                bw.write(product.getClass().getSimpleName() + "," + product.getName() + "," + product.getPrice());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
        saveProductsToFile();
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public Product getProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }
}
