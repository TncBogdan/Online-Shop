package ro.sda.shop.stock;

import ro.sda.shop.common.GenericDAO;

import java.util.ArrayList;
import java.util.List;

public class StockDAO extends GenericDAO<Stock> {
    private static List<Stock> stocks = new ArrayList<>();

    @Override
    protected List<Stock> getItems() {
        return stocks;
    }

    public Stock findByProductIdAndLocation(Long id, String location) {
        for (Stock stock : getItems()) {
            if (stock.getProduct().getId() == id && stock.getLocation().equals(location)) {
                return stock;
            }
        }
        return null;
    }
}

