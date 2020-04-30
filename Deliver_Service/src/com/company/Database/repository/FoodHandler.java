package com.company.Database.repository;

import com.company.Database.models.Dishes;
import com.company.Database.models.Restaurant;

import java.util.ArrayList;

public class FoodHandler {


    private DishRepo dishRepo;
    private IngridientsRepo ingridientsRepo;



    public FoodHandler(DishRepo dishRepo, IngridientsRepo ingridientsRepo) {
        this.dishRepo = dishRepo;
        this.ingridientsRepo = ingridientsRepo;

    }
    public void createNewDishes(Restaurant restaurant , int hostID) {
       restaurant.dischesList =  dishRepo.findAll(hostID) ;
       dishesReceiveIng(restaurant.dischesList);
    }

    public  void createNewIngridients(Restaurant restaurant , int hostID) {
        restaurant.ingridientsList = ingridientsRepo.findAll(hostID);

    }

    public  void dishesReceiveIng(ArrayList<Dishes> disheList) {
        for (int i = 0; i < disheList.size(); i++) {
            disheList.get(i).dishIngridients = ingridientsRepo.findDishIng(disheList.get(i).getDishId());
        }
    }



}
