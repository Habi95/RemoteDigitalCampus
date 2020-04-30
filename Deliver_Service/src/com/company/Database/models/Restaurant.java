package com.company.Database.models;

import java.util.ArrayList;

public class Restaurant {
    int id;
    String name;
    String email;
    String password;
    String place;
    String type;
    public ArrayList<Dishes> dischesList = new ArrayList<>();
    public ArrayList<Ingridients> ingridientsList = new ArrayList<>();
    private String dishTypeStarter = "Vorspeise";
    private String dishTypeMain = "Hauptspeise";

    public Restaurant(int id, String name, String email, String password, String place, String type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.place = place;
        this.type = type;
    }



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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPlace() {
        return place;
    }

    public String getType() {
        return type;
    }
}
