package ro.sda.shop.common;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUtil {
    private static Scanner scanner = new Scanner(System.in);
    private static final Integer MAX_RETRIES = 3;

    public static Long readLong(String invalidInputMessage) {
        Long result = null;
        int retries = 0;
        while (result == null) {
            try {
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

    public static Double getPrice(Entity entity) {
        Scanner scanner1 = new Scanner(System.in); // nu inteleg de ce scannerul de sus sare introducerea inputului
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

//    public static String toSentenceCase(String input) {
//        String[] sentences = input.split(".");
//        String finalString = input;
//        for (int i = 0; i < sentences.length; i++) {
//            String tempString = "";
//            if (!sentences[i].isEmpty()) {
//                String[] words = sentences[i].split(" ");
//                String firstWord = sentences[i].split(" ")[0];
//                if(!)
//                for (int j = 0; j < words.length; j++) {
//                    if (!words[j].isEmpty()) {
//                        words[j] = capitalizeFirstLetter(words[j]);
//                        if (j < words.length - 1) {
//                            words[j] += " ";
//                        }
//                        tempString += words[i];
//                    }
//                }
//            }
//        }
//        return finalString;
//    }

    public static String capitalizeEachWord(String input) {
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
