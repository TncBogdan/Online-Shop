package ro.sda.shop.client;

import ro.sda.shop.common.ConsoleReader;
import ro.sda.shop.common.ConsoleUtil;
import ro.sda.shop.order.OrderDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientReader implements ConsoleReader<Client> {
    private OrderDAO orderDAO = new OrderDAO();
    private Scanner scanner = new Scanner(System.in);

    public Client read() {
        Client client = new Client();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        //System.out.print("Gender (m/f): ");
        //char gender = ConsoleUtil.getGender();
        System.out.print("Phone number: ");
        String phoneNumber = ConsoleUtil.getPhoneNumber();
//        System.out.print("Date of birth (yyyy-mm-dd): ");
//        LocalDate dateOfBirth = ConsoleUtil.getDateOfBirth();
        System.out.print("Social ID: ");
        String socialId = ConsoleUtil.getSocialId();
        if (socialId.charAt(0) == '1' || socialId.charAt(0) == '5') {
            client.setGender('M');
        } else {
            client.setGender('F');
        }
        int year = 1900 + Integer.parseInt(socialId.substring(1, 3));
        int month = Integer.parseInt(socialId.substring(3, 5));
        int day = Integer.parseInt(socialId.substring(5, 7));
        if (socialId.charAt(0) == '5' || socialId.charAt(0) == '6')
            year += 100;
        client.setDateOfBirth(LocalDate.of(year, month, day));
        System.out.println("Address: ");
        List<Address> addresses = new ArrayList<>();
        addresses.add(getAdress());
        client.setName(name);

        client.setPhoneNumber(phoneNumber);
        client.setSocialId(socialId);
        client.setAddresses(addresses);
        client.setActive(true);
        return client;
    }

    public Address getAdress() {
        Address address = new Address();
        System.out.print("Street: ");
        address.setStreet(scanner.nextLine());
        System.out.print("Number: ");
        address.setNumber(scanner.nextLine());
        System.out.print("Block: ");
        address.setBlock(scanner.nextLine());
        System.out.print("Staircase: ");
        address.setStaircase(scanner.nextLine());
        System.out.print("Floor: ");
        address.setFloor(scanner.nextLine());
        System.out.print("Apartment: ");
        address.setApartment(scanner.nextLine());
        System.out.print("City: ");
        String city = scanner.nextLine().toUpperCase();
//        String[] availableCities = City.values();
//        while (!City.values().equals(city)) {
//            System.out.print("Incorrect city. Insert again: ");
//            city = scanner.nextLine().toUpperCase();
//        }
//        address.setCity(city);
//        System.out.print("County: ");
//        address.getLocation().setCounty(scanner.nextLine());
//        System.out.print("Zip code: ");
//        address.getLocation().setZipCode(scanner.nextInt());
        return address;
    }
}
