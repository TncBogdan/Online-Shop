package ro.sda.shop.exceptions;

public class ProductNotInStockException extends RuntimeException {
    public ProductNotInStockException() {
    }

    public ProductNotInStockException(String message) {
        super(message);
    }
}
