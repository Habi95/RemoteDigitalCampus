package com.company.Database.models;

public class User {

    private int userID;
   private String email;
    private String password;
    private String place;

    public User(int userID, String email, String password, String place) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.place = place;
    }

    public int getUserID() {
        return userID;
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
