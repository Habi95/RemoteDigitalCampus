package com.company.Database.models;

public class DeliveryPlaces {

    private int id;
    private String place;
    private double price;

    public DeliveryPlaces(int id, String place, double price) {
        this.id = id;
        this.place = place;
        this.price = price;
    }

    @Override
    public String toString() {
        return id + " - " + place + " - " + price ;
    }

    public int getId() {
        return id;
    }

    public String getPlace() {
        return place;
    }

    public double getPrice() {
        return price;
    }

}
