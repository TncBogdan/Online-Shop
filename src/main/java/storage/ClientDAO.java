package storage;

import model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements GenericDAO<Client> {
    private static List<Client> clients = new ArrayList<>();

    public void initialize() {
        clients.add(new Client(1L, "Gigel", "12345", "56789", "cuca macaii"));
        clients.add(new Client(1L, "Coman", "67890", "13456", "la 'facultate'"));
    }

    public List<Client> findAll() {
        return clients;
    }

    public Client findById(Long id) {
        for (Client client : clients) {
            if (client.getId().equals(id)) {
                return client;
            }
        }
        return null;
    }

    public void update(Client client) {
        delete(client);
        add(client);
    }

    public Client add(Client client) {
        if (client.getId() == null) {
            client.setId(generateNewId());
        }
        clients.add(client);
        return client;
    }

    public void delete(Client client) {
        deleteById(client.getId());
    }

    public Client deleteById(Long id) {
        Client deletedClient = null;
        for (Client client : clients) {
            if (client.getId().equals(id)) {
                deletedClient = client;
            }
        }
        clients.remove(deletedClient);
        return deletedClient;
    }

    private Long generateNewId() {
        return findMaxId() + 1;
    }

    private Long findMaxId() {
        Long max = -1L;
        for (Client client : clients) {
            if (max < client.getId()) {
                max = client.getId();
            }
        }
        return max;
    }
}
