package com.example.bankapplication;

import java.util.ArrayList;

public class User {
    private String name="", address="", number="", email="", password;
    public ArrayList<Account> accountArrayList = new ArrayList<>();
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String getAddress() {
        return address;
    }

    void setAddress(String address) {
        this.address = address;
    }

    String getNumber() {
        return number;
    }

    void setNumber(String number) {
        this.number = number;
    }

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    void createUser(String n, String p)   {
        password = p;
        name = n;
    }

}
