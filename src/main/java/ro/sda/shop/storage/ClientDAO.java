package ro.sda.shop.storage;

import ro.sda.shop.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends GenericDAO<Client> {
    private static List<Client> clients=new ArrayList<>();

    @Override
    protected List<Client> getItems() {
        return clients;
    }
}
