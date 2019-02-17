package ro.sda.shop.presentation;

import ro.sda.shop.model.Client;
import ro.sda.shop.storage.ClientDAO;

import java.util.Scanner;

public class ClientMenu extends AbstractMenu {
    private ClientDAO clientDAO = new ClientDAO();
    private ClientReader reader = new ClientReader();
    private ClientWriter writer = new ClientWriter();

    protected void displayOptions() {
//        clientDAO.initialize();
        System.out.println("\nClients menu");
        System.out.println("1 - View all clients");
        System.out.println("2 - View client details");
        System.out.println("3 - Edit client");
        System.out.println("4 - Add client");
        System.out.println("5 - Delete client");
        System.out.println("0 - Exit");
    }

    protected void executeCmd(Integer option) {
        switch (option) {
            case 1:
                writer.writeAll(clientDAO.findAll());
                break;
            case 2:
                if (clientDAO.findAll().isEmpty()) {
                    System.out.println("No clients available.");
                } else {
                    writer.writeAll(clientDAO.findAll());
                    System.out.print("Select client to view: ");
                    displayClientDetails();
                }
                break;
            case 3:
                if (clientDAO.findAll().isEmpty()) {
                    System.out.println("No clients available.");
                } else {
                    writer.writeAll(clientDAO.findAll());
                    System.out.print("Select client to edit: ");
                    editClient();
                }
                break;
            case 4:
                Client newClient = reader.read();
                clientDAO.add(newClient);
                System.out.println("Client added");
                break;
            case 5:
                if (clientDAO.findAll().isEmpty()) {
                    System.out.println("No clients available.");
                } else {
                    writer.writeAll(clientDAO.findAll());
                    System.out.print("Select client to delete: ");
                    String inputMessage = " ClientID: ";
                    String invalidMessage = "Invalid Client Id. Please, retry!";
                    Long id = ConsoleUtil.readLong(inputMessage, invalidMessage);
                    boolean isDeleted = clientDAO.deleteById(id);
                    if (!isDeleted) {
                        System.out.println("Client not found");
                    } else {
                        System.out.println("Client deleted");
                    }
                }
                break;
            case 0:
                System.out.println("Exiting to main menu");
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    private void editClient() {
        Scanner scanner = new Scanner(System.in);
        String inputMessage = " ClientID: ";
        String invalidMessage = "Invalid Client Id. Please, retry!";
        Client foundClient = clientDAO.findById(ConsoleUtil.readLong(inputMessage, invalidMessage));
        if (foundClient == null) {
            System.out.println("Client not found");
        } else {
            System.out.print("Enter new name: ");
            foundClient.setName(scanner.next());
            System.out.print("Enter new phone number: ");
            String phoneNumber = scanner.next().trim();
            if (!phoneNumber.matches("0[0-9]{9}")) {
                System.out.println("Phone number not changed");
            } else {
                foundClient.setPhoneNumber(phoneNumber);
            }
            System.out.print("Enter new social ID: "); // nu prea e ok sa schimbam cnp-ul, pt. ca ar trebui sa fie unic si "imutabil"
            String socialId = scanner.next().trim();
            if (!socialId.matches("0[0-9]{9}")) {
                System.out.println("ID not changed");
            } else {
                foundClient.setSocialId(socialId);
            }
            System.out.print("Enter new adress: ");
            foundClient.setAddress(scanner.next());
            clientDAO.update(foundClient);
            System.out.println("Client updated");
        }
    }

    private void displayClientDetails() {
        String inputMessage = " ClientID: ";
        String invalidMessage = "Invalid Client Id. Please, retry!";
        Client foundClient = clientDAO.findById(ConsoleUtil.readLong(inputMessage, invalidMessage));
        if (foundClient == null) {
            System.out.println("Client not found");
        } else {
            System.out.println("Client details are: ");
            writer.write(foundClient);
        }
    }
}