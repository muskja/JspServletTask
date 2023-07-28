package com.example.model;

public class Address {
    private int Id;
    private String AddressLine1;
    private String AddressLine2;
    private String City;
    private String State;
    private String Country;
    private String PinCode;

    public Address() {
        // Default constructor
    }

    public Address(int Id, String AddressLine1, String AddressLine2, String City, String State, String Country, String PinCode) {
        this.Id = Id;
        this.AddressLine1 = AddressLine1;
        this.AddressLine2 = AddressLine2;
        this.City = City;
        this.State = State;
        this.Country = Country;
        this.PinCode = PinCode;
    }

    // Getters and setters

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getAddressLine1() {
        return AddressLine1;
    }

    public void setAddressLine1(String AddressLine1) {
        this.AddressLine1 = AddressLine1;
    }

    public String getAddressLine2() {
        return AddressLine2;
    }

    public void setAddressLine2(String AddressLine2) {
        this.AddressLine2 = AddressLine2;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getPinCode() {
        return PinCode;
    }

    public void setPinCode(String PinCode) {
        this.PinCode = PinCode;
    }
}
