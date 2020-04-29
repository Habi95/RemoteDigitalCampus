package com.company.Database.models;

public class IngridientsEvaluation {

    private String name;
    private int count;

    public IngridientsEvaluation(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
