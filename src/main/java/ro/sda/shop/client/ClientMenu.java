package ro.sda.shop.client;

import ro.sda.shop.common.AbstractMenu;
import ro.sda.shop.common.ConsoleUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientMenu extends AbstractMenu {
    private ClientService service = new ClientService();
    private ClientReader reader = new ClientReader();
    private ClientWriter writer = new ClientWriter();

    protected void displayOptions() {
        System.out.println("\nClients menu");
        System.out.println("1 - View all clients");
        System.out.println("2 - View client details");
        System.out.println("3 - Edit client");
        System.out.println("4 - Add client");
        System.out.println("5 - Deactivate client");
        System.out.println("6 - Search clients by city");
        System.out.println("7 - Search clients by age");
        System.out.println("0 - Exit");
    }

    protected void executeCmd(Integer option) {
        switch (option) {
            case 1:
                writer.writeAll(service.getAllClients());
                break;
            case 2:
                viewClientDetails();
                break;
            case 3:
                editClient();
                break;
            case 4:
                Client newClient = reader.read();
                service.save(newClient);
                System.out.println("Client added");
                break;
            case 5:
                deactivateClient();
                break;
            case 6:
                searchByCity();
                break;
            case 7:
                searchByAge();
                break;
            case 0:
                System.out.println("Exiting to main menu");
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    private void viewClientDetails() {
        if (service.getAllClients().isEmpty()) {
            System.out.println("No clients available.");
        } else {
            writer.writeAll(service.getAllClients());
            System.out.print("Select client to view: ");
            Client foundClient = service.getClient(ConsoleUtil.readLong());
            if (foundClient == null) {
                System.out.println("Client not found");
            } else {
                System.out.println("Client details are: ");
                writer.write(foundClient);
            }
        }
    }

    private void editClient() {
        if (service.getAllClients().isEmpty()) {
            System.out.println("No clients available.");
        } else {
            writer.writeAll(service.getAllClients());
            System.out.print("Select client to edit: ");
            Client foundClient = service.getClient(ConsoleUtil.readLong());
            if (foundClient == null) {
                System.out.println("Client not found");
            } else {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter new name (leave empty if the same): ");
                String name = scanner.nextLine().trim();
                if (!name.isEmpty()) {
                    foundClient.setName(ConsoleUtil.capitalizeEachWord(name));
                }
                System.out.print("Enter new phone number (leave empty if the same): ");
                String phoneNumber = reader.getPhoneNumber(foundClient);
                foundClient.setPhoneNumber(phoneNumber);
                System.out.print("Enter new email (leave empty if the same): ");
                String email = reader.getEmail(foundClient);
                foundClient.setEmail(email);
                System.out.print("Enter new social ID (leave empty if the same): "); // nu prea e ok sa schimbam cnp-ul, pt. ca ar trebui sa fie unic si "imutabil"
                String socialId = reader.getSocialId(foundClient);
                foundClient.setSocialId(socialId);
                foundClient.setGender(reader.getGender(socialId));
                foundClient.setDateOfBirth(reader.getDateOfBirth(socialId));
                System.out.println("Enter new adress: ");
                List<Address> addresses = foundClient.getAddresses();
                addresses.add(reader.getAddress());
                foundClient.setAddresses(addresses);
                service.save(foundClient);
                System.out.println("Client updated");
            }
        }
    }

    private void deactivateClient() {
        if (service.getAllClients().isEmpty()) {
            System.out.println("No clients available.");
        } else {
            writer.writeAll(service.getAllClients());
            System.out.print("Select client to deactivate: ");
            service.deactivateClient(ConsoleUtil.readLong());
            System.out.println("Client deactivated");
        }
    }

    private void searchByCity() {
        List<Client> clients = service.getAllClients();
        if (clients.isEmpty()) {
            System.out.println("No clients available.");
        } else {
            System.out.print("Enter city: ");
            City city = reader.getCity();
            List<Client> filteredClients = new ArrayList<>();
            for (Client client : clients) {
                for (Address address : client.getAddresses()) {
                    if (city.equals(address.getCity())) {
                        filteredClients.add(client);
                    }
                }
            }
            if (filteredClients.isEmpty()) {
                System.out.println("No clients match your criteria");
            } else {
                System.out.println("Found clients: ");
                writer.writeAll(filteredClients);
            }
        }
    }

    private void searchByAge() {
        List<Client> clients = service.getAllClients();
        if (clients.isEmpty()) {
            System.out.println("No clients available.");
        } else {
            System.out.print("Enter min age: ");
            int min = ConsoleUtil.getInteger();
            System.out.print("Enter max age: ");
            int max = ConsoleUtil.getInteger();
            while (max < min) {
                System.out.print("Max limit should be >= min limit. Try again: ");
                max = ConsoleUtil.getInteger();
            }
            List<Client> filteredClients = new ArrayList<>();
            for (Client client : clients) {
                if (client.calculateAge() >= min && client.calculateAge() <= max) {
                    filteredClients.add(client);
                }
            }
            if (filteredClients.isEmpty()) {
                System.out.println("No clients match your criteria");
            } else {
                System.out.println("Found clients: ");
                writer.writeAll(filteredClients);
            }
        }
    }
}