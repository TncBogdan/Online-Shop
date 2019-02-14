package storage;

import model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements GenericDAO<Client> {
    static List<Client> clients = new ArrayList<>();

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

    public boolean deleteById(Long id) {
        Client deletedClient = null;
        for (Client client : clients) {
            if (client.getId().equals(id)) {
                deletedClient = client;
            }
        }
        if (deletedClient == null) {
            System.out.println("Client not found");
            return false;
        } else {
            clients.remove(deletedClient);
            return true;
        }
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
