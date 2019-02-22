package ro.sda.shop.product;

import java.util.List;

public class ProductService {
    private ProductDAO productDAO = new ProductDAO();

    public void initialize() {
        productDAO.add(new Product("Tv", "4k", 3300D));
        productDAO.add(new Product("Consola", "PS4", 1300D));
        productDAO.add(new Product("Laptop", "Asus", 2500D));
        productDAO.add(new Product("Monitor", "24\"", 700D));
    }

    List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    Product getProduct(Long id) {
        return productDAO.findById(id);
    }

    Product save(Product product) {
        Product updatedProduct = null;
        if (product.getId() == null) {
            updatedProduct = productDAO.add(product);
        } else {
            productDAO.update(product);
            updatedProduct = product;
        }
        return updatedProduct;
    }

    boolean delete(Long id) {
        return productDAO.deleteById(id);
    }
}
