package ro.sda.shop.client;

import org.hazlewood.connor.bottema.emailaddress.EmailAddressValidator;
import ro.sda.shop.common.City;
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
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("You must enter a name: ");
            name = scanner.nextLine().trim();
        }
        client.setName(ConsoleUtil.toTitleCase(name));
//        System.out.print("Gender (m/f): ");
//        char gender = getGender();
        System.out.print("Phone number: ");
        client.setPhoneNumber(getPhoneNumber(client));
        System.out.print("Email: ");
        client.setEmail(getEmail(client));
        System.out.print("Social ID: ");
        String socialId = getSocialId(client);
        client.setSocialId(socialId);
        client.setGender(getGender(socialId));
//        System.out.print("Date of birth (yyyy-mm-dd): ");
//        LocalDate dateOfBirth = getDateOfBirth();
        client.setDateOfBirth(getDateOfBirth(socialId));
        List<Address> addresses = new ArrayList<>();
        addresses.add(getAddress());
        client.setAddresses(addresses);
        client.setActive(true);
        return client;
    }

    String getPhoneNumber(Client client) {
        String phoneNumber = scanner.nextLine().trim();
        if (client.getId() == null || (client.getId() != null && !phoneNumber.isEmpty())) {
            while (!phoneNumber.matches("0[0-9]{9}")) {
                System.out.print("Incorrect number. Insert again: ");
                phoneNumber = scanner.nextLine().trim();
            }
            return phoneNumber;
        }
        return client.getPhoneNumber();
    }

    String getEmail(Client client) {
        String email = scanner.nextLine().trim();
        if (client.getId() == null || (client.getId() != null && !email.isEmpty())) {
            boolean isValid = EmailAddressValidator.isValid(email);
            while (!isValid) {
                System.out.print("Incorrect email. Insert again: ");
                email = scanner.nextLine().trim();
                isValid = EmailAddressValidator.isValid(email);
            }
            return email;
        }
        return client.getEmail();
    }

    char getGender(String socialId) {
        if (socialId.charAt(0) == '1' || socialId.charAt(0) == '5') {
            return 'M';
        } else {
            return 'F';
        }
    }

    String getSocialId(Client client) {
        String socialId = scanner.nextLine().trim();
        if (client.getId() == null || (client.getId() != null && !socialId.isEmpty())) {
            LocalDate dateOfBirth = LocalDate.of(0, 1, 1);
            while (true) {
                if (!socialId.matches("[1|2|5|6][0-9]{12}")) {
                    System.out.print("Incorrect ID. Insert again: ");
                    socialId = scanner.nextLine().trim();
                } else {
                    try {
                        dateOfBirth = getDateOfBirth(socialId);
                    } catch (DateTimeException e) {
                        System.out.println(e.getMessage());
                    }
                    if (dateOfBirth.isAfter(LocalDate.now()) || dateOfBirth.getYear() < 1900) {
                        System.out.print("Incorrect ID. Insert again: ");
                        socialId = scanner.nextLine().trim();
                    } else break;
                }
            }
            return socialId;
        }
        return client.getSocialId();
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
        System.out.print("Address: ");
        String location = scanner.nextLine().trim();
        while (location.isEmpty()) {
            System.out.print("You must enter an adress: ");
            location = scanner.nextLine().trim();
        }
        address.setAddress(ConsoleUtil.capitalizeFirstLetter(location));
        System.out.print("City: ");
        address.setCity(getCity());
        System.out.print("County: ");
        address.setCounty(ConsoleUtil.toTitleCase(getCounty()));
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
        String county = scanner.nextLine().trim();
        while (!county.matches("[a-zA-Z \\-]{1,}")) {
            System.out.print("Incorrect county. Try again: ");
            county = scanner.nextLine().trim();
        }
        return county;
    }
}
