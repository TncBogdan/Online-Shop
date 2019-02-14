package model;

import java.util.List;

public class Client {
    private Long id;
    private String name;
    private String phoneNumber;
    private String socialId;
    private String address;
//    List<Order> orders; to be implemented

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

//    @Override
//    public String toString() {
//        return "Id: " + id +
//                " Name: " + name +
//                " Phone number: " + phoneNumber +
//                " Social ID: " + socialId +
//                " Address: " + address;
//    }
}
