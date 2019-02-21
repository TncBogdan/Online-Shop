package ro.sda.shop.product;

import ro.sda.shop.common.ConsoleReader;
import ro.sda.shop.common.ConsoleUtil;

import java.util.Scanner;

public class ProductReader implements ConsoleReader<Product> {
    public Product read() {
        Product product = new Product();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("You must enter a name: ");
            name = scanner.nextLine().trim();
        }
        product.setName(ConsoleUtil.capitalizeEachWord(name));
        System.out.print("Description: ");
        String description = scanner.nextLine().trim();
        while (description.isEmpty()) {
            System.out.print("You must enter a description: ");
            description = scanner.nextLine().trim();
        }
        product.setDescription(ConsoleUtil.capitalizeFirstLetter(description));
        System.out.print("Price: ");
        product.setPrice(ConsoleUtil.getPrice(product));
        return product;
    }
}
