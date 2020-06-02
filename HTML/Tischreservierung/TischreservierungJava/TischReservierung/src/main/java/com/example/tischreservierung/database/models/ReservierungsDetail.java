package com.example.tischreservierung.database.models;

import java.sql.Date;
import java.sql.Time;

public class ReservierungsDetail {
    private int id;
    private int howMuch;
    private Date date;
    private Time time;
    private int deskID;
    private int seatID;

    public ReservierungsDetail(int id, int howMuch, Date date, Time time, int deskID, int seatID) {
        this.id = id;
        this.howMuch = howMuch;
        this.date = date;
        this.time = time;
        this.deskID = deskID;
        this.seatID = seatID;
    }

    public int getId() {
        return id;
    }

    public int getHowMuch() {
        return howMuch;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public int getDeskID() {
        return deskID;
    }

    public int getSeatID() {
        return seatID;
    }
}
