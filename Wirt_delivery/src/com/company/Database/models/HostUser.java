package com.company.Database.models;

public class HostUser {

    int id;
    String name;
    String email;
    String password;
    String place;

    public HostUser(int id, String name, String email, String password, String place) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPlace() {
        return place;
    }
}
