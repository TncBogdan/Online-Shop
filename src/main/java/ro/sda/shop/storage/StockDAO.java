package ro.sda.shop.storage;

import ro.sda.shop.model.Stock;

import java.util.ArrayList;
import java.util.List;

public class StockDAO extends GenericDAO<Stock> {
    private static List<Stock> stocks = new ArrayList<>();

    @Override
    protected List<Stock> getItems() {
        return stocks;
    }
}

