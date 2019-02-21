package ro.sda.shop.client;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private ClientDAO clientDAO = new ClientDAO();

    public void initialize() {
        List<Address> addresses1 = new ArrayList<>();
        addresses1.add(new Address("strada Plopului", City.Bucuresti, "vaslui", "012"));
        clientDAO.add(new Client("Gigel", "12345", "someone@domain.com",
                "5120229225588", 'm', LocalDate.of(2017, 2, 28),
                addresses1, true, null));
        List<Address> addresses2 = new ArrayList<>();
        addresses2.add(new Address("aleea rozelor", City.Iasi, "vaslui", "123456789"));
        clientDAO.add(new Client("Cornel", "abcdef", "blabla",
                "1231213890123", 'b', LocalDate.of(1992,2,29),
                addresses2, true, null));
    }

    List<Client> getAllClients() {
        return clientDAO.findAll();
    }

    Client getClient(Long id) {
        return clientDAO.findById(id);
    }

    Client save(Client client) {
        Client updatedClient;
        if (client.getId() == null) {
            updatedClient = clientDAO.add(client);
        } else {
            clientDAO.update(client);
            updatedClient = client;
        }
        return updatedClient;
    }

    boolean deactivateClientAccount(Long id) {
        Client client = getClient(id);
        if (client == null) {
//            throw new NotFoundException("Client with id " + id + " not found");
            return false;
        }
        client.setActive(false);
        save(client);
        return true;
    }
}
