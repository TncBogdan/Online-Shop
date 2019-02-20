package ro.sda.shop.client;

import ro.sda.shop.common.Entity;
import ro.sda.shop.order.Order;

import java.time.LocalDate;
import java.util.List;

public class Client extends Entity {
    private String name;
    private String phoneNumber;
    private String email;
    private String socialId;
    private char gender;
    private LocalDate dateOfBirth;
    private List<Address> addresses;
    private boolean active;
    private List<Order> orders;

    public Client() {
    }

    public Client(String name, String phoneNumber, String email, String socialId, char gender, LocalDate dateOfBirth, List<Address> addresses, boolean active, List<Order> orders) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.socialId = socialId;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.addresses = addresses;
        this.active = active;
        this.orders = orders;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
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
