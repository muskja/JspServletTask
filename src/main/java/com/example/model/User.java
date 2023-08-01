package com.example.model;

import java.util.List;

public class User {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    private String phonenumber;
    private String gender;

    private List<Address> addresses;


    public User(int Id,  String FirstName, String LastName, String Email, String Password, String PhoneNumber, String Gender) {
        this.id = Id;
        this.firstname = FirstName;
        this.lastname = LastName;
        this.email = Email;
        this.password = Password;
        this.phonenumber = PhoneNumber;
        this.gender = Gender;

        this.addresses = addresses;
    }

    public User() {
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String FirstName) {
        this.firstname = FirstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String LastName) {
        this.lastname = LastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        this.password = Password;
    }

    public String getPhoneNumber() {
        return phonenumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.phonenumber = PhoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String Gender) {
        this.gender = Gender;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

}
