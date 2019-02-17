package ro.sda.shop.presentation;

import ro.sda.shop.model.Client;
import ro.sda.shop.model.Order;
import ro.sda.shop.model.Product;
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
        String inpMessage = "Client ID";
        String invalMessage = "Invalid client Id. Please, retry!";
        Client selectedClient = clientDAO.findById(ConsoleUtil.readLong(inpMessage, invalMessage));
        while (selectedClient == null) {
            System.out.print("Client not found. Select again: ");
            selectedClient = clientDAO.findById(ConsoleUtil.readLong(inpMessage, invalMessage));
        }
        new ProductWriter().writeAll(productDAO.findAll());
        String inputMessage = "Number of products";
        String invalidMessage = "Invalid number. Please, retry!";
        Long noOfProducts = ConsoleUtil.readLong(inputMessage, invalidMessage);
        while (noOfProducts <= 0) {
            System.out.print("Incorrect number. Insert again: ");
            noOfProducts = ConsoleUtil.readLong(inputMessage, invalidMessage);
        }
        List<Product> listOfProducts = getProducts(noOfProducts);
        System.out.print("Actual price: ");
        Double actualPrice = ConsoleUtil.getPrice();
        order.setClient(selectedClient);
        order.setOrderedProducts(listOfProducts);
        order.setFinalPrice(actualPrice);
        order.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        return order;
    }

    private List<Product> getProducts(Long noOfItems) {
        List<Product> listOfProducts = new ArrayList<>();
        for (int i = 0; i < noOfItems;){
            //@sdatrainers - if you read this, beer is on us!!!!!!
            System.out.print("Product #" + (i + 1) + ": ");
            String inpMessage = "Product ID";
            String invalMessage = "Invalid product Id. Please, retry!";
            Product product = productDAO.findById(ConsoleUtil.readLong(inpMessage, invalMessage));
            if(product != null) {
                listOfProducts.add(product);
                i++;
            }
        }
        return listOfProducts;
    }

    public void setOrderToClient(Order order) {
        Client client = order.getClient();
        List<Order> orders = client.getOrders();
        orders.add(order);
        client.setOrders(orders);
    }
}
