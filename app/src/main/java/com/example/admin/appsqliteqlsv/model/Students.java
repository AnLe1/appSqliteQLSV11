package com.example.admin.appsqliteqlsv.model;

/**
 * Created by Admin on 29/12/2017.
 */

public class Students {
    private int id;
    private String name;
    private String number;
    private String email;
    private String address;

    public Students() {
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Students(String name, String number, String email, String address) {

        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
    }

    public Students(int id, String name, String number, String email, String address) {
        this.id = id;

        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
    }
}
