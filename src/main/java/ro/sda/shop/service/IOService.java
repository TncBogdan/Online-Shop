package ro.sda.shop.service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IOService {
    static Scanner scanner = new Scanner(System.in);

    public static Long getNumericInput() {
        try {
            return scanner.nextLong();
        } catch (InputMismatchException e) {
            scanner.nextLine();
        }
        return -1L;
    }

    public static Double getPrice() {
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
}
