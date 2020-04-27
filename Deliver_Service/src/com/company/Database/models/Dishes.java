package com.company.Database.models;

import java.util.ArrayList;

public class Dishes {

    private String name;
    private int dishId;
    private String dishTyp;
    private double price;
    private int HasAddIng = 0; //boolean
    private int HasDeleteING = 0; //boolean
    public ArrayList<Ingridients> dishIngridients = new ArrayList<>();

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

    public void setName(String name) {
        this.name = name;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getDishTyp() {
        return dishTyp;
    }

    public void setDishTyp(String dishTyp) {
        this.dishTyp = dishTyp;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getHasAddIng() {
        return HasAddIng;
    }

    public void setHasAddIng(int hasAddIng) {
        HasAddIng = hasAddIng;
    }

    public int getHasDeleteING() {
        return HasDeleteING;
    }

    public void setHasDeleteING(int hasDeleteING) {
        HasDeleteING = hasDeleteING;
    }
}
