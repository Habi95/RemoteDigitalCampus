package com.company;

import com.company.Database.models.Customer;
import com.company.Database.repository.*;
import com.company.controller.Delivery;
import com.company.view.TerminalOutput;

public class Main {
//TODO Customer
    //TODO Restaurant spezifizieren
    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/lieferservicetest?user=root";
        DB_Connector db_connector = new DB_Connector(url);
        TerminalOutput myOutPut = new TerminalOutput();


        DishRepo dishRepo = new DishRepo(db_connector, myOutPut);
        IngridientsRepo ingridientsRepo = new IngridientsRepo(db_connector, myOutPut);
        UserRepo userRepo = new UserRepo(db_connector, myOutPut);
        DeliveryPlaceHandler deliveryPlaceHandler = new DeliveryPlaceHandler(db_connector);
        Delivery delivery = new Delivery("QuickFood",db_connector, userRepo);
        Customer customer = new Customer(db_connector,userRepo,myOutPut);
        FoodHandler foodHandler = new FoodHandler(dishRepo,ingridientsRepo);
        RestaurantRepo restaurant = new RestaurantRepo(db_connector,myOutPut);
        deliveryPlaceHandler.crateDeliveryPlaceList(delivery);



        delivery.logIn(customer, restaurant, myOutPut, foodHandler);

    }

}
