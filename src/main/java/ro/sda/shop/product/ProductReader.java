package ro.sda.shop.product;

import ro.sda.shop.common.ConsoleReader;
import ro.sda.shop.common.ConsoleUtil;

import java.util.Scanner;

public class ProductReader implements ConsoleReader<Product> {
    public Product read() {
        Product product = new Product();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        product.setName(ConsoleUtil.capitalizeEachWord(scanner.nextLine()));
        System.out.print("Description: ");
        product.setDescription(ConsoleUtil.toSentenceCase(scanner.nextLine()));
        System.out.print("Price: ");
        product.setPrice(ConsoleUtil.getPrice());
        return product;
    }
}
