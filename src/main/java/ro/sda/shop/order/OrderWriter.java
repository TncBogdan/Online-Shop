package ro.sda.shop.order;

import ro.sda.shop.client.ClientWriter;
import ro.sda.shop.common.ConsoleWriter;
import ro.sda.shop.product.ProductWriter;

import java.util.List;

public class OrderWriter implements ConsoleWriter<Order> {
    public void write(Order order) {
        System.out.println("Id: " + order.getId());
        System.out.print("Client: ");
        new ClientWriter().writeSummary(order.getClient());
        System.out.println("Products: ");
        new ProductWriter().writeAll(order.getOrderedProducts());
        System.out.println("Final price: " + order.getFinalPrice());
        System.out.println("Status: " + order.getStatus());
        System.out.println("Date when placed: " + order.getTimestamp());
    }

    void writeAll(List<Order> orders) {
        if (orders.size() == 0) {
            System.out.println("No orders available.");
        } else {
            System.out.println("Orders list: ");
            for (Order order : orders) {
                writeSummary(order);
            }
        }
    }

    private void writeSummary(Order order) {
        System.out.print("Id: " + order.getId());
        System.out.print("  Client: " + order.getClient().getName());
        System.out.print("  Products: ");
        ProductWriter productWriter = new ProductWriter();
        productWriter.writeAll(order.getOrderedProducts());
        System.out.print("  Final price: " + order.getFinalPrice());
        System.out.print("  Status: " + order.getStatus());
        System.out.println(" Date when placed: " + order.getTimestamp());
    }
}
