package ro.sda.shop.presentation;

import ro.sda.shop.model.Client;
import ro.sda.shop.model.Order;
import ro.sda.shop.model.Product;
import ro.sda.shop.service.IOService;
import ro.sda.shop.storage.ClientDAO;
import ro.sda.shop.storage.ProductDAO;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderReader implements ConsoleReader<Order> {
    private ClientDAO clientDAO = new ClientDAO();
    private ProductDAO productDAO = new ProductDAO();

    public Order read() {
        if (productDAO.findAll().isEmpty() || clientDAO.findAll().isEmpty()) {
            return null;
        }
        Order order = new Order();
        new ClientWriter().writeAll(clientDAO.findAll());
        System.out.print("Select client: ");
        Client selectedClient = clientDAO.findById(IOService.getNumericInput());
        while (selectedClient == null) {
            System.out.print("Client not found. Select again: ");
            selectedClient = clientDAO.findById(IOService.getNumericInput());
        }
        new ProductWriter().writeAll(productDAO.findAll());
        System.out.print("Select no. of products: ");
        Long noOfProducts = IOService.getNumericInput();
        while (noOfProducts <= 0) {
            System.out.print("Incorrect number. Insert again: ");
            noOfProducts = IOService.getNumericInput();
        }
        List<Product> listOfProducts = getProducts(noOfProducts);
        System.out.print("Actual price: ");
        Double actualPrice = IOService.getPrice();
        order.setClient(selectedClient);
        order.setOrderedProducts(listOfProducts);
        order.setFinalPrice(actualPrice);
        order.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        return order;
    }

    private List<Product> getProducts(Long noOfItems) {
        for (int i = 0; i < noOfItems; i++) {
            System.out.print("Product #" + (i + 1) + ": ");
            productDAO.findById(IOService.getNumericInput());
        }
        List<Product> listOfProducts = new ArrayList<>();
//        Product product1 = productReader.read();
//        Product product2 = productReader.read();
//        listOfProducts.add(product1);
//        listOfProducts.add(product2);
        return listOfProducts;
    }

    public void setOrderToClient(Order order) {
        Client client = order.getClient();
        List<Order> orders = client.getOrders();
        orders.add(order);
        client.setOrders(orders);
    }
}
