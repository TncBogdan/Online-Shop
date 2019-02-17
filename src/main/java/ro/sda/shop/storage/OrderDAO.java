package ro.sda.shop.storage;

import ro.sda.shop.model.Order;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends GenericDAO<Order> {
    private static List<Order> orders = new ArrayList<>();

    @Override
    protected List<Order> getItems() {
        return orders;
    }

    public List<Order> findAllByClientId(Long clientId) {
        List<Order> orders = new ArrayList<>();
        for (Order order : getItems()) {
            if (order.getClient().getId().equals(clientId)) {
                orders.add(order);
            }
        }
        return orders;
    }

    public List<Order> findAllBetweenDates(Timestamp start, Timestamp end) {
        List<Order> orders = new ArrayList<>();
        for (Order order : getItems()) {
            if (isBetween(order, start, end)) {
                orders.add(order);
            }
        }
        return orders;
    }

    private boolean isBetween(Order order, Timestamp start, Timestamp end) {
        return order.getTimestamp().after(start) && order.getTimestamp().before(end);
    }
}
