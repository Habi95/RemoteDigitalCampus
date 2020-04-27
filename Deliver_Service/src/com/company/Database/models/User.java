package com.company.Database.models;

public class User {

    private int userID;
   private String email;
    private int password;
    private String place;

    public User(int userID, String email, int password, String place) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.place = place;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
