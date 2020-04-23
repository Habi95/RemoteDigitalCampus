package com.company;

public class DeliveryPlaces {

    int id;
    String place;
    double price;

    public DeliveryPlaces(int id, String place, double price) {
        this.id = id;
        this.place = place;
        this.price = price;
    }

    @Override
    public String toString() {
        return id + " - " + place + " - " + price ;
    }
}
