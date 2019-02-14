package presentation;

import model.Client;
import model.Order;
import model.Product;
import storage.ClientDAO;
import storage.ProductDAO;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OrderReader implements ConsoleReader<Order> {
    private Scanner scanner = new Scanner(System.in);
    private ClientDAO clientDAO = new ClientDAO();
    private ProductDAO productDAO = new ProductDAO();

    public Order read() {
        if (productDAO.findAll().isEmpty() || clientDAO.findAll().isEmpty()) {
            return null;
        }
        Order order = new Order();
        new ClientWriter().writeAll(clientDAO.findAll());
        System.out.print("Select client: ");
        Client selectedClient = clientDAO.findById(getNumericInput());
        while (selectedClient == null) {
            System.out.print("Client not found. Select again: ");
            selectedClient = clientDAO.findById(getNumericInput());
        }
        new ProductWriter().writeAll(productDAO.findAll());
        System.out.print("Select no. of products: ");
        Long noOfProducts = getNumericInput();
        while (noOfProducts <= 0) {
            System.out.print("Incorrect number. Insert again: ");
            noOfProducts = getNumericInput();
        }
        List<Product> listOfProducts = getProducts(noOfProducts);
        System.out.print("Actual price: ");
        Double actualPrice = getPrice();
        order.setClient(selectedClient);
        order.setOrderedProducts(listOfProducts);
        order.setFinalPrice(actualPrice);
        order.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        return order;
    }

    private Long getNumericInput() {
        try {
            return scanner.nextLong();
        } catch (InputMismatchException e) {
            scanner.nextLine();
        }
        return -1L;
    }

    private Double getPrice() {
        double price = 0d;
        while (true) {
            try {
                price = scanner.nextDouble();
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
            if (price <= 0)
                System.out.print("Incorrect price. Insert again: ");
            else break;
        }
        return price;
    }

    private List<Product> getProducts(Long noOfItems) {
        for (int i = 0; i < noOfItems; i++) {
            System.out.println("Product #" + i + ": ");
            productDAO.findById(getNumericInput());
        }
        List<Product> listOfProducts = new ArrayList<>();
//        Product product1 = productReader.read();
//        Product product2 = productReader.read();
//        listOfProducts.add(product1);
//        listOfProducts.add(product2);
        return listOfProducts;
    }
}
