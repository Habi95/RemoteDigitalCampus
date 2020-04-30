package com.company.Database.models;

import java.util.ArrayList;

public class Restaurant {
//
//    String name;
//    String ort;
//    int hostID;
//
//    public Restaurant(String name, String ort, int hostID) {
//        this.name = name;
//        this.ort = ort;
//        this.hostID = hostID;
//    }

    public ArrayList<Dishes> dischesList = new ArrayList<>();
    public ArrayList<Ingridients> ingridientsList = new ArrayList<>();
    private String dishTypeStarter = "Vorspeise";
    private String dishTypeMain = "Hauptspeise";
    private String dishTypeDessert = "Nachspeise";

    public String getDishTypeStarter() {
        return dishTypeStarter;
    }

    public void setDishTypeStarter(String dishTypeStarter) {
        this.dishTypeStarter = dishTypeStarter;
    }

    public String getDishTypeMain() {
        return dishTypeMain;
    }

    public void setDishTypeMain(String dishTypeMain) {
        this.dishTypeMain = dishTypeMain;
    }

    public String getDishTypeDessert() {
        return dishTypeDessert;
    }

    public void setDishTypeDessert(String dishTypeDessert) {
        this.dishTypeDessert = dishTypeDessert;
    }
}
