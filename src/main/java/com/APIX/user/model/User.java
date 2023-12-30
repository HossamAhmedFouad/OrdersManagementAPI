package com.APIX.user.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private static Long nextID = 1L;
    private Long id;
    private String username;
    private String email;
    private String password;
    private double balance;
    private String address;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String phoneNumber;

    private Language language;

    public User(
                @JsonProperty("username") String username,
                @JsonProperty("email") String email,
                @JsonProperty("password") String password,
                @JsonProperty("balance") double balance,
                @JsonProperty("address") String address,
                @JsonProperty("phoneNumber") String phoneNumber) {
        this.id = nextID++;
        this.username = username;
        this.email = email;
        this.password = password;
        this.balance=balance;
        this.address =address;
        this.language = Language.ENG;
        this.phoneNumber = phoneNumber;
    }



    public long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
