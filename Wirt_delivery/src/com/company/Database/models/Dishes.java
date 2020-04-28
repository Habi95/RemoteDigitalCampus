package com.company.Database.models;

import java.util.ArrayList;

public class Dishes {

    private String name;
    private int dishId;
    private String dishTyp;
    private double price;
    public ArrayList<Ingridients> dishIngridients = new ArrayList<>();

//    public Dishes(String name, int dishTyp) {
//        this.name = name;
//        this.dishTyp = dishTyp;
//    }

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


    public String getName() {
        return name;
    }


    public int getDishId() {
        return dishId;
    }


    public String getDishTyp() {
        return dishTyp;
    }


    public double getPrice() {
        return price;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
