package ro.sda.shop.stock;

import ro.sda.shop.common.ConsoleWriter;

import java.util.List;

public class StockWriter implements ConsoleWriter<Stock> {
    public void write(Stock stock) {
        System.out.println("Id: " + stock.getId());
//        System.out.println("Product: " + stock.getProducts().getName());
//        System.out.println("Quantity: " + stock.getQuantity());
        System.out.println("Location: " + stock.getLocation());
    }

    public void writeAll(List<Stock> stocks) {
        if (stocks.size() == 0) {
            System.out.println("No stocks available.");
        } else {
            System.out.println("Stock list: ");
            for (Stock stock : stocks) {
                writeSummary(stock);
            }
        }
    }

    private void writeSummary(Stock stock) {
        System.out.print("Id: " + stock.getId());
//        System.out.print("  Product: " + stock.getProducts().getName());
//        System.out.print("  Quantity: " + stock.getQuantity());
        System.out.println("  Location: " + stock.getLocation());
    }
}
