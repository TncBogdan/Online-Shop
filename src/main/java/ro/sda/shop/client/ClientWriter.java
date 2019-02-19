package ro.sda.shop.client;

import ro.sda.shop.common.ConsoleWriter;
import ro.sda.shop.order.Order;

import java.util.List;

public class ClientWriter implements ConsoleWriter<Client> {
    public void write(Client client) {
        System.out.println("Id: " + client.getId());
        System.out.println("Name: " + client.getName());
        System.out.println("Phone number: " + client.getPhoneNumber());
        System.out.println("Social ID: " + client.getSocialId());
        System.out.println("Gender: " + client.getGender());
        System.out.println("Date of birth: " + client.getDateOfBirth());
        int i = 1;
        for (Address address : client.getAddresses()) {
            System.out.print("Address #" + i + ": " + address);
            i++;
        }
        System.out.println("Status: " + (client.isActive() ? "active" : "inactive"));
        if (client.getOrders() != null) {
            System.out.println("Orders: ");
            for (Order order : client.getOrders()) {
                System.out.print(order.getId() + ", ");
            }
        } else {
            System.out.println("Orders: none");
        }
    }

    public void writeAll(List<Client> clients) {
        if (clients.size() == 0) {
            System.out.println("No clients available.");
        } else {
            System.out.println("Client list: ");
            for (Client client : clients) {
                writeSummary(client);
            }
        }
    }

    public void writeSummary(Client client) {
        System.out.print("Id: " + client.getId());
        System.out.print("  Name: " + client.getName());
        System.out.print("  Phone number: " + client.getPhoneNumber());
        System.out.print("  Social ID: " + client.getSocialId());
        System.out.print("  Gender: " + client.getGender());
        System.out.print("  Date of birth: " + client.getDateOfBirth());
        int i = 1;
        for (Address address : client.getAddresses()) {
            System.out.print("  Address #" + i + ": " + address);
            i++;
        }
        System.out.print("  Status: " + (client.isActive() ? "active" : "inactive"));
        if (client.getOrders() != null) {
            System.out.println("  Orders: ");
            for (Order order : client.getOrders()) {
                System.out.print(order.getId() + ", ");
            }
        } else {
            System.out.println("  Orders: none");
        }
    }
}
