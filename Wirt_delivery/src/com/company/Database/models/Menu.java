package com.company.Database.models;

public class Menu {
    private int hostID;
    private int dishID;

    public Menu(int hostID, int dishID) {
        this.hostID = hostID;
        this.dishID = dishID;
    }

    public int getHostID() {
        return hostID;
    }

    public int getDishID() {
        return dishID;
    }
}
