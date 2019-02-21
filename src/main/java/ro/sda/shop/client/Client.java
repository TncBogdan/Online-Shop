package ro.sda.shop.client;

import ro.sda.shop.common.Entity;
import ro.sda.shop.order.Order;

import java.time.LocalDate;
import java.time.Period;
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

    void setName(String name) {
        this.name = name;
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    String getSocialId() {
        return socialId;
    }

    void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    char getGender() {
        return gender;
    }

    void setGender(char gender) {
        this.gender = gender;
    }

    LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    List<Address> getAddresses() {
        return addresses;
    }

    void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    boolean isActive() {
        return active;
    }

    void setActive(boolean active) {
        this.active = active;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    int calculateAge() {
//        return LocalDate.now().getYear() - dateOfBirth.getYear();
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
