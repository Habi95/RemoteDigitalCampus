package com.company.Database.models;

public class Ingridients {

    private int id;
    private String name;

    public Ingridients(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }
}
