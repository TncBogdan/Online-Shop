package ro.sda.shop.client;

import org.hazlewood.connor.bottema.emailaddress.EmailAddressValidator;
import ro.sda.shop.common.ConsoleReader;
import ro.sda.shop.common.ConsoleUtil;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientReader implements ConsoleReader<Client> {
    private Scanner scanner = new Scanner(System.in);

    public Client read() {
        Client client = new Client();
        System.out.print("Name: ");
        client.setName(ConsoleUtil.capitalizeEachWord(scanner.nextLine()));
        //System.out.print("Gender (m/f): ");
        //char gender = getGender();
        System.out.print("Phone number: ");
        client.setPhoneNumber(getPhoneNumber());
        System.out.print("Email: ");
        client.setEmail(getEmail());
        System.out.print("Social ID: ");
        String socialId = getSocialId();
        if (socialId.charAt(0) == '1' || socialId.charAt(0) == '5') {
            client.setGender('M');
        } else {
            client.setGender('F');
        }
        client.setSocialId(socialId);
//        System.out.print("Date of birth (yyyy-mm-dd): ");
//        LocalDate dateOfBirth = getDateOfBirth();
        client.setDateOfBirth(getDateOfBirth(socialId));
        List<Address> addresses = new ArrayList<>();
        addresses.add(getAddress());
        client.setAddresses(addresses);
        client.setActive(true);
        return client;
    }

    String getPhoneNumber() {
        String phoneNumber = scanner.nextLine().trim();
        while (!phoneNumber.matches("0[0-9]{9}")) {
            System.out.print("Incorrect number. Insert again: ");
            phoneNumber = scanner.nextLine().trim();
        }
        return phoneNumber;
    }

    String getEmail() {
        String email = scanner.nextLine().trim();
        boolean isValid = EmailAddressValidator.isValid(email);
        while (!isValid) {
            System.out.print("Incorrect email. Insert again: ");
            email = scanner.nextLine().trim();
            isValid = EmailAddressValidator.isValid(email);
        }
        return email;
    }

    public char getGender() {
        char gender = scanner.nextLine().toUpperCase().charAt(0);
        while (gender != 'M' && gender != 'F') {
            System.out.print("Incorrect gender. Insert again: ");
            gender = scanner.nextLine().toUpperCase().charAt(0);
        }
        return gender;
    }

    String getSocialId() {
        String socialId = scanner.nextLine().trim();
        LocalDate dateOfBirth = LocalDate.of(0, 1, 1);
        while (true) {
            if (!socialId.matches("[1|2|5|6][0-9]{12}")) {
                System.out.print("Incorrect ID. Insert again: ");
                socialId = scanner.nextLine().trim();
            } else {
                try {
                    dateOfBirth = getDateOfBirth(socialId);
                } catch (DateTimeException e) {
//                    System.out.println(e.getMessage());
                }
                if (dateOfBirth.isAfter(LocalDate.now()) || dateOfBirth.getYear() < 1900) {
                    System.out.print("Incorrect ID. Insert again: ");
                    socialId = scanner.nextLine().trim();
                } else break;
            }
        }
        return socialId;
    }

    LocalDate getDateOfBirth(String socialId) {
        int year = 1900 + Integer.parseInt(socialId.substring(1, 3));
        int month = Integer.parseInt(socialId.substring(3, 5));
        int day = Integer.parseInt(socialId.substring(5, 7));
        if (socialId.charAt(0) == '5' || socialId.charAt(0) == '6') {
            year += 100;
        }
        return LocalDate.of(year, month, day);
    }

    Address getAddress() {
        Address address = new Address();
//        System.out.print("Street: ");
//        address.setStreet(scanner.nextLine());
//        System.out.print("Number: ");
//        address.setNumber(scanner.nextLine());
//        System.out.print("Block: ");
//        address.setBlock(scanner.nextLine());
//        System.out.print("Staircase: ");
//        address.setStaircase(scanner.nextLine());
//        System.out.print("Floor: ");
//        address.setFloor(scanner.nextLine());
//        System.out.print("Apartment: ");
//        address.setApartment(scanner.nextLine());
        System.out.print("Adress: ");
        address.setAddress(ConsoleUtil.capitalizeEachWord(scanner.nextLine()));
        System.out.print("City: ");
        address.setCity(getCity());
        System.out.print("County: ");
        address.setCounty(ConsoleUtil.capitalizeEachWord(getCounty()));
        System.out.print("Zip code: ");
        address.setZipCode(getZipCode());
        return address;
    }

    City getCity() {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            for (City city : City.values()) {
                if (input.equals(city.toString().toLowerCase())) {
                    return city;
                }
            }
            System.out.print("Invalid city. Try again: ");
        }
    }

    private String getZipCode() {
        String zipCode = scanner.nextLine().trim();
        while (!zipCode.matches("[0-9]{6}")) {
            System.out.print("Incorrect zip code. Try again: ");
            zipCode = scanner.nextLine().trim();
        }
        return zipCode;
    }

    private String getCounty() {
        String county = scanner.nextLine();
        while (!county.matches("[a-zA-Z \\-]{1,}")) {
            System.out.print("Incorrect county. Try again: ");
            county = scanner.nextLine().trim();
        }
        return county;
    }
}
