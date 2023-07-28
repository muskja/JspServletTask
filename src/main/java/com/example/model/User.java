package com.example.model;

import java.util.List;

public class User {
    private int Id;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;

    private String PhoneNumber;
    private String Gender;

    private List<Address> addresses;

//    public User(long userId, String firstName, String lastName, String email, String phoneNumber, String gender) {
//        // Default constructor
//    }

    public User(int Id,  String FirstName, String LastName, String Email, String Password, String PhoneNumber, String Gender) {
        this.Id = Id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Email = Email;
        this.Password = Password;
        this.PhoneNumber = PhoneNumber;
        this.Gender = Gender;

        this.addresses = addresses;
    }

    public User() {
    }

    // Getters and setters

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }


}
