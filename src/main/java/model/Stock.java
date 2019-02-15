package model;

import java.util.Map;

public class Stock {
    private Long id;
    private Map<Product,Integer> products;
//    private Integer quantity;
    private String location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Product,Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product,Integer> products) {
        this.products = products;
    }

//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
