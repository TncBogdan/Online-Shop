package ro.sda.shop.presentation;

import ro.sda.shop.model.Order;
import ro.sda.shop.storage.OrderDAO;

public class OrderMenu extends AbstractMenu {
    private OrderDAO orderDAO = new OrderDAO();
    private OrderReader reader = new OrderReader();
    private OrderWriter writer = new OrderWriter();

    protected void displayOptions() {
        System.out.println("\nOrders menu");
        System.out.println("1 - View all orders");
        System.out.println("2 - View order details");
        System.out.println("3 - Edit order");
        System.out.println("4 - Add order");
        System.out.println("5 - Delete order");
        System.out.println("0 - Exit");
    }

    protected void executeCmd(Integer option) {
        switch (option) {
            case 1:
                writer.writeAll(orderDAO.findAll());
                break;
            case 2:
                if (orderDAO.findAll().isEmpty()) {
                    System.out.println("No orders available.");
                } else {
                    writer.writeAll(orderDAO.findAll());
                    System.out.print("Select order to view: ");
                    displayOrderDetails();
                }
                break;
            case 3:
                if (orderDAO.findAll().isEmpty()) {
                    System.out.println("No orders available.");
                } else {
                    writer.writeAll(orderDAO.findAll());
                    System.out.print("Select order to edit: ");
                    editOrder();
                }
                break;
            case 4:
                Order newOrder = reader.read();
                if (newOrder == null) {
                    System.out.println("No products/clients available");
                } else {
                    orderDAO.add(newOrder);
//                    reader.setOrderToClient(newOrder); not working! (-x
                    System.out.println("Order added");
                }
                break;
            case 5:
                if (orderDAO.findAll().isEmpty()) {
                    System.out.println("No orders available.");
                } else {
                    writer.writeAll(orderDAO.findAll());
                    System.out.print("Select order to delete: ");
                    String inputMessage = " Order ID: ";
                    String invalidMessage = "Invalid Order Id. Please, retry!";
                    boolean isDeleted = orderDAO.deleteById(ConsoleUtil.readLong(inputMessage, invalidMessage));
                    if (!isDeleted) {
                        System.out.println("Order not found");
                    } else {
                        System.out.println("Order deleted");
                    }
                }
                break;
            case 0:
                System.out.println("Exiting to main menu");
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    private void editOrder() {
//        Scanner scanner = new Scanner(System.in);
//        Order foundOrder = orderDAO.findById(ConsoleUtil.getNumericInput());
//        if (foundOrder == null) {
//            System.out.println("Order not found");
//        } else {
//            System.out.print("Enter new name: ");
//            foundOrder.setName(scanner.next());
//            System.out.print("Enter new phone number: ");
//            String phoneNumber = scanner.next().trim();
//            if (!phoneNumber.matches("0[0-9]{9}")) {
//                System.out.println("Phone number not changed");
//            } else {
//                foundOrder.setPhoneNumber(phoneNumber);
//            }
//            System.out.print("Enter new social ID: "); // nu prea e ok sa schimbam cnp-ul, pt. ca ar trebui sa fie unic si "imutabil"
//            String socialId = scanner.next().trim();
//            if (!socialId.matches("0[0-9]{9}")) {
//                System.out.println("ID not changed");
//            } else {
//                foundOrder.setSocialId(socialId);
//            }
//            System.out.print("Enter new adress: ");
//            foundOrder.setAddress(scanner.next());
//            orderDAO.update(foundOrder);
//            System.out.println("Order updated");
//        }
    }

    private void displayOrderDetails() {
        String inputMessage = " Order ID: ";
        String invalidMessage = "Invalid Order Id. Please, retry!";
        Long id = ConsoleUtil.readLong(inputMessage, invalidMessage);
        Order foundOrder = orderDAO.findById(id);
        if (foundOrder == null) {
            System.out.println("Order not found");
        } else {
            System.out.println("Order details are: ");
            writer.write(foundOrder);
        }
    }
}
