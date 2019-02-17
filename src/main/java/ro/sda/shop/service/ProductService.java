package ro.sda.shop.service;

import ro.sda.shop.model.Product;
import ro.sda.shop.storage.ProductDAO;

import java.util.List;

public class ProductService {
    private ProductDAO productDAO = new ProductDAO();

    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    public Product getProduct(Long id) {
        return productDAO.findById(id);
    }

    public Product save(Product product) {
        Product updatedProduct = null;
        if (product.getId() == null) {
            updatedProduct = productDAO.add(product);
        } else {
            productDAO.update(product);
            updatedProduct = product;
        }
        return updatedProduct;
    }

    public void delete(Long id){
        productDAO.deleteById(id);
    }
}
