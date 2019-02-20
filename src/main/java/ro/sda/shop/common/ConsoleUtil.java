package ro.sda.shop.common;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUtil {
    private static Scanner scanner = new Scanner(System.in);
    static final Integer MAX_RETRIES = 3;

    public static Long readLong(String invalidInputMessage) {
        Long result = null;
        int retries = 0;
        while (retries < MAX_RETRIES && result == null) {
            try {
//                System.out.println();
                result = scanner.nextLong();
            } catch (InputMismatchException exception) {
                scanner.nextLine();
                System.out.print(invalidInputMessage);
            }
            retries++;
        }
        return result;
    }

    public static Long readLong() {
        return readLong("Invalid id. Please retry: ");
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

    public static String toSentenceCase(String input) {
        char firstLetter = input.charAt(0);
        if (firstLetter >= 'a' && firstLetter <= 'z') {
            firstLetter -= 32;
            return firstLetter + input.substring(1);
        }
        return input;
    }

    public static String capitalizeEachWord(String input) {
        String[] separators = {" ", "-"};
        String finalString = input.trim();
        for (int j = 0; j < separators.length; j++) {
            String tempString = "";
            String[] words = finalString.split(separators[j]);
            for (int i = 0; i < words.length; i++) {
                if (!words[i].isEmpty()) {
                    words[i] = toSentenceCase(words[i]);
                    if (i < words.length - 1) {
                        words[i] += separators[j];
                    }
                    tempString += words[i];
                }
            }
            finalString = tempString;
        }
        return finalString;
    }
}
