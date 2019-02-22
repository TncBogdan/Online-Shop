package ro.sda.shop.stock;

import ro.sda.shop.common.City;
import ro.sda.shop.common.Entity;
import ro.sda.shop.product.Product;

public class Stock extends Entity {
    public static final City DEFAULT_LOCATION = City.Iasi;
    private Product product;
    private Integer quantity;
    private City location;

    public Stock() {
    }

    public Stock(Product product, Integer quantity, City location) {
        this.product = product;
        this.quantity = quantity;
        this.location = location;
    }

    public Product getProduct() {
        return product;
    }

    public void setProducts(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public City getLocation() {
        return location;
    }

    public void setLocation(City location) {
        this.location = location;
    }
}
