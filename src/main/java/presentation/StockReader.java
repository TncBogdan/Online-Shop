package presentation;

import model.Product;
import model.Stock;
import service.IOService;
import storage.ProductDAO;

import java.util.List;
import java.util.Scanner;

public class StockReader implements ConsoleReader<Stock> {
    private ProductDAO productDAO = new ProductDAO();

    public Stock read() {
        if (productDAO.findAll().isEmpty()) {
            return null;
        }
        Stock stock = new Stock();
        new ProductWriter().writeAll(productDAO.findAll());
        System.out.print("Select no. of products: ");
        Long noOfProducts = IOService.getNumericInput();
        while (noOfProducts <= 0) {
            System.out.print("Incorrect number. Insert again: ");
            noOfProducts = IOService.getNumericInput();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Location: ");
        String location = scanner.nextLine();
//        System.out.println("Quantity: ");
//        Integer quantity = scanner.nextInt();
//        stock.setProducts(product);
        stock.setLocation(location);
//        stock.setQuantity(quantity);
        return stock;
    }
}
