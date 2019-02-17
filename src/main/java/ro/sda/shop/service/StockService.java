package ro.sda.shop.service;

import ro.sda.shop.model.Product;
import ro.sda.shop.model.Stock;
import ro.sda.shop.service.exceptions.NotFoundException;
import ro.sda.shop.storage.StockDAO;

public class StockService {
    private StockDAO stockDAO = new StockDAO();

    public void addProductToStock(Product product, int quantity) {
        addProductToStock(product, quantity, Stock.DEFAULT_LOCATION);
    }

    public void addProductToStock(Product product, int quantity, String location) {
        Stock stock = stockDAO.findByProductIdAndLocation(product.getId(), location);
        if (stock == null) {
            stock = new Stock(product, 1, location);
        }
        save(stock);
    }

    public Stock save(Stock stock) {
        Stock updatedStock = null;
        if (stock.getId() == null) {
            updatedStock = stockDAO.add(stock);
        } else {
            stockDAO.update(stock);
            updatedStock = stock;
        }
        return updatedStock;
    }

    public boolean isInStock(Product product) {
        return isInStock(product, Stock.DEFAULT_LOCATION);
    }

    public boolean isInStock(Product product, String location) {
        Stock stock = stockDAO.findByProductIdAndLocation(product.getId(), location);
        if (stock != null && stock.getQuantity() > 0) {
            return true;
        }
        return false;
    }

    public void deliverFromStock(Product product, String location, int quantity) {
        Stock stock = stockDAO.findByProductIdAndLocation(product.getId(), location);
        if (stock != null && stock.getQuantity() >= quantity) {
            int newQuantity = stock.getQuantity() - quantity;
            stock.setQuantity(newQuantity);
            save(stock);
        } else {
            throw new NotFoundException("Product not in stock.");
        }
    }

    public void returnToStock(Product product, String location, int quantity) {
        Stock stock = stockDAO.findByProductIdAndLocation(product.getId(), location);
        if (stock != null) {
            int newQuantity = stock.getQuantity() + quantity;
            stock.setQuantity(newQuantity);
            save(stock);
        } else {
            throw new NotFoundException("Product not in stock.");
        }
    }

    public void returnToStock(Product product) {
        returnToStock(product, Stock.DEFAULT_LOCATION, 1);
    }

    public void deliverFromStock(Product product) {
        deliverFromStock(product, Stock.DEFAULT_LOCATION, 1);
    }
}
