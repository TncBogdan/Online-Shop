package ro.sda.shop.model;

import java.util.Map;

public class Stock extends Entity {
    public static final String DEFAULT_LOCATION = "Oradea";
    private Product product;
    private Integer quantity;
    private String location;

    public Stock() {
    }

    public Stock(Product product, Integer quantity, String location) {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
