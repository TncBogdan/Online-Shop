package ro.sda.shop.presentation;

import ro.sda.shop.model.Client;
import ro.sda.shop.storage.OrderDAO;

import java.util.Scanner;

public class ClientReader implements ConsoleReader<Client> {
    private OrderDAO orderDAO = new OrderDAO();

    public Client read() {
        Client client = new Client();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Phone number: ");
        String phoneNumber = scanner.nextLine().trim();
        while (!phoneNumber.matches("0[0-9]{9}")) {
            System.out.print("Incorrect number. Insert again: ");
            phoneNumber = scanner.nextLine().trim();
        }
        System.out.print("Social ID: ");
        String socialId = scanner.nextLine().trim();
        while (!socialId.matches("[1|2][0-9]{12}")) {
            System.out.print("Incorrect ID. Insert again: ");
            socialId = scanner.nextLine().trim();
        }
        System.out.print("Address: ");
        String address = scanner.nextLine();
        client.setName(name);
        client.setPhoneNumber(phoneNumber);
        client.setSocialId(socialId);
        client.setAddress(address);
        return client;
    }
}
