package ro.sda.shop.client;

public class Address {
    //    private String street;
//    private String number;
//    private String block;
//    private String staircase;
//    private String floor;
//    private String apartment;
    private String address;
    private City city;
    private String county;
    private String zipCode;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
