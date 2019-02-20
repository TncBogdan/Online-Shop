package ro.sda.shop;

import ro.sda.shop.client.ClientService;
import ro.sda.shop.product.ProductService;

public class Main {

    public static void main(String[] args) {
        new ProductService().initialize();
        new ClientService().initialize();
        MainMenu menu = new MainMenu();
        menu.displayMenu();
    }
}
