package storage;

import model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements GenericDAO<Order> {
    private static List<Order> orders = new ArrayList<>();

    public List<Order> findAll() {
        return orders;
    }

    public Order findById(Long id) {
        for (Order order : orders) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }

    public void update(Order order) {
        delete(order);
        add(order);
    }

    public Order add(Order order) {
        if (order != null) {
            if (order.getId() == null) {
                order.setId(generateNewId());
            }
            orders.add(order);
        }
        return order;
    }

    public void delete(Order order) {
        deleteById(order.getId());
    }

    public Order deleteById(Long id) {
        Order deletedOrder = null;
        for (Order order : orders) {
            if (order.getId().equals(id)) {
                deletedOrder = order;
            }
        }
        orders.remove(deletedOrder);
        return deletedOrder;
    }

    private Long generateNewId() {
        return findMaxId() + 1;
    }

    private Long findMaxId() {
        Long max = -1L;
        for (Order order : orders) {
            if (max < order.getId()) {
                max = order.getId();
            }
        }
        return max;
    }
}
