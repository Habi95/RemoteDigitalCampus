package com.company;

public class Ingridients {

    int id;
    String name;

    public Ingridients(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return  id +
                " - " + name ;
    }
}
