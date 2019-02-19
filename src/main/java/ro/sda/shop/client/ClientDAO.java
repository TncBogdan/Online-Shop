package ro.sda.shop.client;

import ro.sda.shop.common.GenericDAO;

import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends GenericDAO<Client> {
    private static List<Client> clients = new ArrayList<>();

    @Override
    protected List<Client> getItems() {
        return clients;
    }
}
