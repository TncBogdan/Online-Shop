package ro.sda.shop.product;

import ro.sda.shop.common.GenericDAO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductDAO extends GenericDAO<Product> {
    private static List<Product> products = new ArrayList<>();

    @Override
    protected List<Product> getItems() {
        products.sort(Comparator.comparing(Product::getId));
        return products;
    }
}
