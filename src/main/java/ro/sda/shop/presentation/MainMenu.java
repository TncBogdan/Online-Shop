package ro.sda.shop.presentation;


public class MainMenu extends AbstractMenu {

    protected void displayOptions() {
        System.out.println("\nro.sda.shop.Main menu");
        System.out.println("1 - Products");
        System.out.println("2 - Clients");
        System.out.println("3 - Orders");
        System.out.println("4 - Stocks");
        System.out.println("0 - Exit");

    }

    protected void executeCmd(Integer option) {
        switch (option) {
            case 1:
                ProductMenu productMenu = new ProductMenu();
                productMenu.displayMenu();
                break;
            case 2:
                ClientMenu client = new ClientMenu();
                client.displayMenu();
                break;
            case 3:
                OrderMenu order = new OrderMenu();
                order.displayMenu();
                break;
            case 4:
                StockMenu stock = new StockMenu();
                stock.displayMenu();
                break;
            case 0:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid option");
        }
    }
}
