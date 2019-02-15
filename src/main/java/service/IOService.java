package service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IOService {
    public static Long getNumericInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextLong();
        } catch (InputMismatchException e) {
            scanner.nextLine();
        }
        return -1L;
    }
}
