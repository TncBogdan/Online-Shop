package ro.sda.shop.order;

import ro.sda.shop.common.AbstractMenu;
import ro.sda.shop.common.ConsoleUtil;
import ro.sda.shop.product.ProductWriter;

public class OrderMenu extends AbstractMenu {
    private OrderService service = new OrderService();
    private OrderReader reader = new OrderReader();
    private OrderWriter writer = new OrderWriter();

    protected void displayOptions() {
        System.out.println("\nOrders menu");
        System.out.println("1 - View all orders");
        System.out.println("2 - View order details");
        System.out.println("3 - Edit order");
        System.out.println("4 - Add order");
        System.out.println("5 - Cancel order");
        System.out.println("0 - Exit");
    }

    protected void executeCmd(Integer option) {
        switch (option) {
            case 1:
                writer.writeAll(service.getAllOrders());
                break;
            case 2:
                viewOrderDetails();
                break;
            case 3:
                editOrder();
                break;
            case 4:
                Order newOrder = reader.read();
                service.save(newOrder);
                System.out.println("Order added");
                break;
            case 5:
                cancelOrder();
                break;
            case 0:
                System.out.println("Exiting to main menu");
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    private void viewOrderDetails() {
        if (service.getAllOrders().isEmpty()) {
            System.out.println("No orders available.");
        } else {
            writer.writeAll(service.getAllOrders());
            System.out.print("Select order to view: ");
            Order foundOrder = service.getOrder(ConsoleUtil.readLong());
            if (foundOrder == null) {
                System.out.println("Order not found");
            } else {
                System.out.println("Order details are: ");
                writer.write(foundOrder);
            }
        }
    }

    private void editOrder() {
        if (service.getAllOrders().isEmpty()) {
            System.out.println("No orders available.");
        } else {
            writer.writeAll(service.getAllOrders());
            System.out.print("Select order to edit: ");
            Order foundOrder = service.getOrder(ConsoleUtil.readLong());
            if (foundOrder == null) {
                System.out.println("Order not found");
            } else {
                new ProductWriter().writeAll(foundOrder.getOrderedProducts());
                service.save(foundOrder);
                System.out.println("Order updated");
            }
        }
    }

    private void cancelOrder() {
        if (service.getAllOrders().isEmpty()) {
            System.out.println("No orders available.");
        } else {
            writer.writeAll(service.getAllOrders());
            System.out.print("Select order to delete: ");
            Long id = ConsoleUtil.readLong();
            if (id == null) {
                System.out.println("Order not found");
            } else {
                service.cancelOrder(service.getOrder(id));
                System.out.println("Order deleted");
            }
        }
    }
}
