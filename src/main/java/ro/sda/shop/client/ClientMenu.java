package ro.sda.shop.client;

import ro.sda.shop.common.AbstractMenu;
import ro.sda.shop.common.ConsoleUtil;

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
                System.out.print("Enter new name: ");
                foundClient.setName(scanner.next());
                System.out.print("Enter new phone number: ");
                foundClient.setPhoneNumber(reader.getPhoneNumber());
                System.out.print("Enter new email: ");
                foundClient.setEmail(reader.getEmail());
                System.out.print("Enter new social ID: "); // nu prea e ok sa schimbam cnp-ul, pt. ca ar trebui sa fie unic si "imutabil"
                String socialId = reader.getSocialId();
                if (socialId.charAt(0) == '1' || socialId.charAt(0) == '5') {
                    foundClient.setGender('M');
                } else {
                    foundClient.setGender('F');
                }
                foundClient.setSocialId(socialId);
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
            boolean isDeactivated = service.deactivateClientAccount(ConsoleUtil.readLong());
            if (!isDeactivated) {
                System.out.println("Client not found");
            } else {
                System.out.println("Client deactivated");
            }
        }
    }
}