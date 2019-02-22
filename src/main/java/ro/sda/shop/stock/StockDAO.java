package ro.sda.shop.stock;

import ro.sda.shop.common.City;
import ro.sda.shop.common.GenericDAO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StockDAO extends GenericDAO<Stock> {
    private static List<Stock> stocks = new ArrayList<>();

    @Override
    public List<Stock> getItems() {
        stocks.sort(Comparator.comparing(Stock::getId));
        return stocks;
    }

    public Stock findByProductIdAndLocation(Long id, City location) {
        for (Stock stock : getItems()) {
            if (stock.getProduct().getId() == id && stock.getLocation().equals(location.toString())) {
                return stock;
            }
        }
        return null;
    }
}

