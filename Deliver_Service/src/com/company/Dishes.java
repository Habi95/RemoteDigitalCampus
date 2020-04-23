package com.company;

import java.util.ArrayList;

public class Dishes {

    String name;
    int dishId;
    String dishTyp;
    double price;
    int HasAddIng = 0; //boolean
    int HasDeleteING = 0; //boolean
    ArrayList<Ingridients> dishIngridients = new ArrayList<>();

    public Dishes(String name, int id, String dishTyp, double price) {
        this.name = name;
        this.dishId = id;
        this.dishTyp = dishTyp;
        this.price = price;
    }

    @Override
    public String toString() {
        return dishId + " - " + name + " - " + dishTyp + " - " + price ;
    }




}
