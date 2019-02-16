package ro.sda.shop.storage;

import ro.sda.shop.model.Client;
import ro.sda.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends GenericDAO<Product> {
    private static List<Product> products =new ArrayList<>();

    @Override
    protected List<Product> getItems() {
        return products;
    }
}
