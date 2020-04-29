package com.company.Database.models;

public class DishEvaluation {

   private String dishName;
   private int orderCount;

    public DishEvaluation(String dishName, int orderCount) {
        this.dishName = dishName;
        this.orderCount = orderCount;
    }

    public String getDishName() {
        return dishName;
    }

    public int getOrderCount() {
        return orderCount;
    }
}
