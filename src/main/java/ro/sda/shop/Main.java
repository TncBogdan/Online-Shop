package ro.sda.shop;

import ro.sda.shop.client.ClientService;
import ro.sda.shop.product.ProductService;
import ro.sda.shop.stock.StockService;

public class Main {

    public static void main(String[] args) {
        new ProductService().initialize();
        new ClientService().initialize();
        new StockService().initialize();
        MainMenu menu = new MainMenu();
        menu.displayMenu();
    }
}
