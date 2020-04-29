package com.company.Database.models;

import java.sql.Time;

public class BillEvaluation {

    int id;
    int userId;
    double bill;
    Time time;

    public BillEvaluation(int id, int userId, double bill, Time time) {
        this.id = id;
        this.userId = userId;
        this.bill = bill;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public double getBill() {
        return bill;
    }

    public Time getTime() {
        return time;
    }
}
