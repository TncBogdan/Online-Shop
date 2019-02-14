package presentation;

import model.Product;

import java.util.InputMismatchException;
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
        Double price = 0d;
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
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        return product;
    }
}
