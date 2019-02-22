package ro.sda.shop.stock;

import ro.sda.shop.common.ConsoleReader;
import ro.sda.shop.common.ConsoleUtil;
import ro.sda.shop.product.ProductWriter;
import ro.sda.shop.product.ProductDAO;

import java.util.Scanner;

public class StockReader implements ConsoleReader<Stock> {
    private ProductDAO productDAO = new ProductDAO();

    public Stock read() {
        if (productDAO.findAll().isEmpty()) {
            return null;
        }
        Stock stock = new Stock();
        new ProductWriter().writeAll(productDAO.findAll());
        String invalidMessage = "Invalid number. Please retry: ";
        Long noOfProducts = ConsoleUtil.readLong(invalidMessage);
        while (noOfProducts <= 0) {
            noOfProducts = ConsoleUtil.readLong(invalidMessage);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Location: ");
        String location = scanner.nextLine();
//        System.out.println("Quantity: ");
//        Integer quantity = scanner.nextInt();
//        stock.setProducts(product);
//        stock.setLocation(location);
//        stock.setQuantity(quantity);
        return stock;
    }
}
