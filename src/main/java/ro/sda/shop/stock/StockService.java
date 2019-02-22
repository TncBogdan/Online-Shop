package ro.sda.shop.stock;

import ro.sda.shop.common.City;
import ro.sda.shop.product.Product;
import ro.sda.shop.exceptions.NotFoundException;
import ro.sda.shop.product.ProductDAO;

public class StockService {
    private StockDAO stockDAO = new StockDAO();
    private ProductDAO productDAO = new ProductDAO();

    public void initialize() {
        stockDAO.add(new Stock(productDAO.findById(2L), 50, City.Arad));
        stockDAO.add(new Stock(productDAO.findById(0L), 10, City.Cluj));
        stockDAO.add(new Stock(productDAO.findById(1L), 70, City.Iasi));
        stockDAO.add(new Stock(productDAO.findById(3L), 100, City.Bucuresti));
    }

    public void addProductToStock(Product product, int quantity) {
        addProductToStock(product, quantity, Stock.DEFAULT_LOCATION);
    }

    public void addProductToStock(Product product, int quantity, City location) {
        Stock stock = stockDAO.findByProductIdAndLocation(product.getId(), location);
        if (stock == null) {
            stock = new Stock(product, 1, location);
        }
        save(stock);
    }

    public Stock save(Stock stock) {
        Stock updatedStock;
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

    public boolean isInStock(Product product, City location) {
        Stock stock = stockDAO.findByProductIdAndLocation(product.getId(), location);
        if (stock != null && stock.getQuantity() > 0) {
            return true;
        }
        return false;
    }

    public void deliverFromStock(Product product, City location, int quantity) {
        Stock stock = stockDAO.findByProductIdAndLocation(product.getId(), location);
        if (stock != null && stock.getQuantity() >= quantity) {
            int newQuantity = stock.getQuantity() - quantity;
            stock.setQuantity(newQuantity);
            save(stock);
        } else {
            throw new NotFoundException("Product not in stock.");
        }
    }

    public void returnToStock(Product product, City location, int quantity) {
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
