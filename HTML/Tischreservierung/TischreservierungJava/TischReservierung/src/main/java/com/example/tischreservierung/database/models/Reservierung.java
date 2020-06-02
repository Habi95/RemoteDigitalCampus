package com.example.tischreservierung.database.models;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Timer;

public class Reservierung {

    int id;
    String lastname;
    int howMuch;
    Time time;
    Date date;
    int deskNumber;
    int maxSeatNumber;
    /*seat and desk number are the number from the DB
     not from the Array or List these both
    are agoin - 1  and for the DB deskNumber is the the db id and for the seat
    is the seatnumber - howmuch is the first place */

    public Reservierung(int id, String lastname, int howMuch, Time time, Date date) {
        this.id = id;
        this.lastname = lastname;
        this.howMuch = howMuch;
        this.time = time;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public int getHowMuch() {
        return howMuch;
    }

    public Time getTime() {
        return time;
    }

    public Date getDate() {
        return date;
    }

    public int getDeskNumber() {
        return deskNumber;
    }

    public int getMaxSeatNumber() {
        return maxSeatNumber;
    }

    public void setDeskNumber(int deskNumber) {
        this.deskNumber = deskNumber;
    }

    public void setMaxSeatNumber(int maxSeatNumber) {
        this.maxSeatNumber = maxSeatNumber;
    }
}