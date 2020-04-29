package com.company.view;

import com.company.Database.models.Dishes;
import com.company.Database.models.Ingridients;
import com.company.Database.repository.DishRepo;

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

}
