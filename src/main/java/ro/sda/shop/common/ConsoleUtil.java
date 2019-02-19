package ro.sda.shop.common;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUtil {
    private static Scanner scanner = new Scanner(System.in);
    static final Integer MAX_RETRIES = 2;

    public static Long readLong(String inputMessage, String invalidInputMessage) {
        Long result = null;
        int retries = 0;
        while (retries <= MAX_RETRIES && result == null) {
            try {
//                System.out.println();
                System.out.print(inputMessage);
                result = scanner.nextLong();
            } catch (InputMismatchException exception) {
                scanner.nextLine();
                System.out.println(invalidInputMessage);
            }
            retries++;
        }
        return result;
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

    public static char getGender() {
        char gender = scanner.nextLine().toUpperCase().charAt(0);
        while (gender != 'M' && gender != 'F') {
            System.out.print("Incorrect gender. Insert again: ");
            gender = scanner.nextLine().toUpperCase().charAt(0);
        }
        return gender;
    }

    public static String getPhoneNumber() {
        String phoneNumber = scanner.nextLine().trim();
        while (!phoneNumber.matches("0[0-9]{9}")) {
            System.out.print("Incorrect number. Insert again: ");
            phoneNumber = scanner.nextLine().trim();
        }
        return phoneNumber;
    }

    public static LocalDate getDateOfBirth() {
        LocalDate dateOfBirth = LocalDate.of(0, 1, 1);
        while (true) {
            try {
                dateOfBirth = LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
//                scanner.nextLine();
            }
            if (dateOfBirth.isAfter(LocalDate.now()) || dateOfBirth.getYear() < 1900) {
                System.out.print("Incorrect date. Insert again: ");
            } else break;
        }
        return dateOfBirth;
    }

    public static String getSocialId() {
        String socialId = scanner.nextLine().trim();
        while (!socialId.matches("[1|2|5|6][0-9]{12}")) {
            System.out.print("Incorrect ID. Insert again: ");
            socialId = scanner.nextLine().trim();
        }
        return socialId;
    }
}
