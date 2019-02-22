package ro.sda.shop.common;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUtil {
    private static Scanner scanner = new Scanner(System.in);
    private static final Integer MAX_RETRIES = 3;

    public static Long readLong(String invalidInputMessage) {
        Long result = null;
//        int retries = 0; // crapa daca introducem caractere de MAX_RETRIES ori
        while (result == null) {
            try {
                result = scanner.nextLong();
            } catch (InputMismatchException exception) {
                scanner.nextLine();
                System.out.print(invalidInputMessage);
            }
//            retries++;
        }
        return result;
    }

    public static Long readLong() {
        return readLong("Invalid id. Please retry: ");
    }

    public static Double getPrice(Entity entity) {
        Scanner scanner1 = new Scanner(System.in); // nu inteleg de ce scannerul clasei sare introducerea inputului cand editez un item
        String input = scanner1.nextLine().trim();
        Double price = 0d;
        if (entity.getId() == null || (entity.getId() != null && !input.isEmpty())) {
            while (price <= 0) {
                boolean isValid = true;
                if (input.matches("[0-9.]{1,}")) {
                    try {
                        price = Double.parseDouble(input);
                    } catch (NumberFormatException e) {
                        isValid = false;
                    }
                } else {
                    isValid = false;
                }
                if (!isValid) {
                    System.out.print("Incorrect price. Insert again: ");
                    input = scanner1.nextLine().trim();
                }
            }
        }
        return price;
    }

    public static String capitalizeFirstLetter(String input) {
        char firstLetter = input.charAt(0);
        if (firstLetter >= 'a' && firstLetter <= 'z') {
            firstLetter -= 32;
            return firstLetter + input.substring(1);
        }
        return input;
    }

    public static String toTitleCase(String input) {
        String[] separators = {" ", "-"};
        String finalString = input;
        for (String separator : separators) {
            String tempString = "";
            String[] words = finalString.split(separator);
            for (int i = 0; i < words.length; i++) {
                if (!words[i].isEmpty()) {
                    words[i] = capitalizeFirstLetter(words[i]);
                    if (i < words.length - 1) {
                        words[i] += separator;
                    }
                    tempString += words[i];
                }
            }
            finalString = tempString;
        }
        return finalString;
    }

    public static String toSentenceCase(String input) {
        String[] separators = {"\\.", "\\?", "\\!"}; // am pus escape character la toate din cauza stupidului de "?"
        String finalString = input;
        char lastLetter = input.charAt(input.length() - 1);
        for (String separator : separators) {
            String tempString = "";
            String[] sentences = finalString.split(separator);
            for (int i = 0; i < sentences.length; i++) {
                if (!sentences[i].isEmpty()) {
                    sentences[i] = capitalizeFirstLetter(sentences[i].trim());
                    if (i < sentences.length - 1) {
                        sentences[i] += separator.charAt(1) + " ";
                    }
                    if (i == sentences.length - 1 && separator.charAt(1) == lastLetter) {
                        sentences[i] += lastLetter;
                    }
                    tempString += sentences[i];
                }
            }
            finalString = tempString;
        }
        return finalString;
    }

    public static int getInteger() {
        int value = 0;
        while (value <= 0) {
            try {
                value = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Incorrect value. Insert again: ");
            }
        }
        return value;
    }
}
