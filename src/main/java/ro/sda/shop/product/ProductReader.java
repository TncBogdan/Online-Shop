package ro.sda.shop.product;

import ro.sda.shop.common.ConsoleReader;
import ro.sda.shop.common.ConsoleUtil;

import java.util.Scanner;

public class ProductReader implements ConsoleReader<Product> {
    public Product read() {
        Product product = new Product();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Price: ");
        Double price = ConsoleUtil.getPrice();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        return product;
    }
}
