package com.company;

public enum FuelTyp {

    SUPER95("Super95"),
    DIESEL("Diesel");

    private String name;

    FuelTyp(String name) {
        this.name = name;
    }

    public  String getName() {
        return name;
    }


}
