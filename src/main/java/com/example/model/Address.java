package com.example.model;

public class Address {
    private int id;
    private String addressline1;
    private String addressline2;
    private String city;
    private String state;
    private String country;
    private String pincode;

    public Address() {
        // Default constructor
    }

    public Address(int Id, String AddressLine1, String AddressLine2, String City, String State, String Country, String PinCode) {
        this.id = Id;
        this.addressline1 = AddressLine1;
        this.addressline2 = AddressLine2;
        this.city = City;
        this.state = State;
        this.country = Country;
        this.pincode = PinCode;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public String getAddressLine1() {
        return addressline1;
    }

    public void setAddressLine1(String AddressLine1) {
        this.addressline1 = AddressLine1;
    }

    public String getAddressLine2() {
        return addressline2;
    }

    public void setAddressLine2(String AddressLine2) {
        this.addressline2 = AddressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String City) {
        this.city = City;
    }

    public String getState() {
        return state;
    }

    public void setState(String State) {
        this.state = State;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String Country) {
        this.country = Country;
    }

    public String getPinCode() {
        return pincode;
    }

    public void setPinCode(String PinCode) {
        this.pincode = PinCode;
    }
}
