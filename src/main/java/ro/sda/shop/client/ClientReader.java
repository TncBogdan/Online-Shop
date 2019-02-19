package ro.sda.shop.client;

import ro.sda.shop.common.ConsoleReader;
import ro.sda.shop.common.ConsoleUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientReader implements ConsoleReader<Client> {
    private Scanner scanner = new Scanner(System.in);

    public Client read() {
        Client client = new Client();
        System.out.print("Name: ");
        client.setName(scanner.nextLine());
        //System.out.print("Gender (m/f): ");
        //char gender = ConsoleUtil.getGender();
        System.out.print("Phone number: ");
        client.setPhoneNumber(ConsoleUtil.getPhoneNumber());
        System.out.print("Social ID: ");
        String socialId = ConsoleUtil.getSocialId();
        if (socialId.charAt(0) == '1' || socialId.charAt(0) == '5') {
            client.setGender('M');
        } else {
            client.setGender('F');
        }
        client.setSocialId(socialId);
//        System.out.print("Date of birth (yyyy-mm-dd): ");
//        LocalDate dateOfBirth = ConsoleUtil.getDateOfBirth();
        client.setDateOfBirth(ConsoleUtil.getDateOfBirth(socialId));
        System.out.println("Address: ");
        List<Address> addresses = new ArrayList<>();
        addresses.add(getAdress());
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
