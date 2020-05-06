package com.company.Database.models;

public class UserEvaluation {


    private String name;
    private int orderCount;


    public UserEvaluation(String name, int orderCount) {
        this.name = name;
        this.orderCount = orderCount;

    }

    public String getName() {
        return name;
    }

    public int getOrderCount() {
        return orderCount;
    }
}
