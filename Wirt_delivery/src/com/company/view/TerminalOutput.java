package com.company.view;

import com.company.Database.models.*;

import java.util.ArrayList;

public class TerminalOutput {

    public TerminalOutput() {
    }

    public void outPutStringLanding(String outPutString) {
        System.out.println("");
        System.out.println(outPutString);
    }

    public void outPutString(String outPut) {
        System.out.println(outPut);
    }

    public void printIngriedients(ArrayList<Ingridients> ingridients) {

        outPutStringLanding("Ihre Zutaten Liste\nID -- Name");
        for (int i = 0; i < ingridients.size(); i++) {
            outPutString(ingridients.get(i).getId() + " -- " + ingridients.get(i).getName());
        }

    }

    public void printLastAddedDish(Dishes lastDish) {
        outPutString("Letzt hinzugefügtes Gericht");
        outPutString("ID  \t--\t\t Name \t\t--\t\t Typ");
        outPutString(lastDish.getDishId() + " - " + lastDish.getName() +
                    " - " + lastDish.getDishTyp());

    }

    public void printDishIng (ArrayList<Ingridients> ingOfLastDish) {
        outPutString("Zutaten\nID -- Name");
        for (int i = 0; i < ingOfLastDish.size() ; i++) {
            outPutString(ingOfLastDish.get(i).getId() + " -- " + ingOfLastDish.get(i).getName());
        }
    }

    public void evaluationPerCustomer (ArrayList<UserEvaluation> user) {
        for (int i = 0; i < user.size() ; i++) {
            outPutString("Der Kunde mit der Email: " + user.get(i).getName() + " hat bis jetzt " + user.get(i).getOrderCount() + " mal bestellt");
        }

    }

    public void evaluationPerTown (ArrayList<TownEvaluation> town) {
        for (int i = 0; i < town.size() ; i++) {
            outPutString("Es wurde in " + town.get(i).getName() + " gesamt: " + town.get(i).getOrderCount() + " mal bestellt");
        }

    }

    public void printSortedDish (ArrayList<DishEvaluation> sortedDish) {
        outPutString("Bestellte Gerichte in absteigender Reihenfolge:\nWieOft  -  Name ");
        for (int i = 0; i < sortedDish.size() ; i++) {
            outPutString(sortedDish.get(i).getOrderCount() + " - " + sortedDish.get(i).getDishName());
        }
    }

}
