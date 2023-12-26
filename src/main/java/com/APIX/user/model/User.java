package com.APIX.user.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private long id;
    private String username;
    private String email;
    private String password;
    private double balance;

    public User(
                @JsonProperty("username") String username,
                @JsonProperty("email") String email,
                @JsonProperty("password") String password,
                @JsonProperty("balance") double balance) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.balance=balance;
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
}
