package ro.sda.shop.storage;

import ro.sda.shop.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends GenericDAO<Order> {
    private static List<Order> orders =new ArrayList<>();

    @Override
    protected List<Order> getItems() {
        return orders;
    }
}
