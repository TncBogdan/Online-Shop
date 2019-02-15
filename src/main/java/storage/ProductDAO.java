package storage;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements GenericDAO<Product> {
    private static List<Product> products = new ArrayList<>();

    public void initialize() {
        products.add(new Product(1L, "tigaie", "o tigaie mare", 23D));
        products.add(new Product(4L, "laptop", "cu soft piratat", 1400.99));
        products.add(new Product(2L, "margarina", "sanatoasa", 3.5));
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findById(Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public void update(Product product) {
        delete(product);
        add(product);
    }

    public Product add(Product product) {
        if (product.getId() == null) {
            product.setId(generateNewId());
        }
        products.add(product);
        return product;
    }

    public void delete(Product product) {
        deleteById(product.getId());
    }

    public Product deleteById(Long id) {
        Product deletedProduct = null;
        for (Product product : products) {
            if (product.getId().equals(id)) {
                deletedProduct = product;
            }
        }
        products.remove(deletedProduct);
        return deletedProduct;
    }

    private Long generateNewId() {
        return findMaxId() + 1;
    }

    private Long findMaxId() {
        Long max = -1L;
        for (Product product : products) {
            if (max < product.getId()) {
                max = product.getId();
            }
        }
        return max;
    }
}
