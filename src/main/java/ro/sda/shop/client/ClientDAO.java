package ro.sda.shop.client;

import ro.sda.shop.common.GenericDAO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClientDAO extends GenericDAO<Client> {
    private static List<Client> clients = new ArrayList<>();

    @Override
    protected List<Client> getItems() {
        clients.sort(Comparator.comparing(Client::getId));
        // am sortat lista dupa id-uri pt. ca in metoda de update se sterge si apoi se adauga iar clientul sters, fiind astfel mutat pe ultima pozitie din lista
        return clients;
    }
}
