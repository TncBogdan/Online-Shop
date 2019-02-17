package ro.sda.shop.model;

import java.util.List;

public class Client extends Entity{
    private String name;
    private String phoneNumber;
    private String socialId;
    private String address;
    private boolean active;
    private List<Order> orders;

    public Client() {
    }

    public Client(String name, String phoneNumber, String socialId, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.socialId = socialId;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
