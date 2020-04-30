package com.company.Database.models;

public class HostUser {

    int id;
    String name;
    String email;
    String password;
    String place;
    String type;

    public HostUser(int id, String name, String email, String password, String place, String type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.place = place;
        this.type = type;
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

    public String getType() {
        return type;
    }
}
