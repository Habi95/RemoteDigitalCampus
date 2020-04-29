package com.company;


import com.company.Database.repository.IngridientsRepo;
import com.company.Database.repository.*;
import com.company.controller.Evaluation;
import com.company.controller.GUI;
import com.company.controller.Restaurant;
import com.company.view.TerminalOutput;
import com.company.view.Writer;

public class Main {
    /*gericht aus der DB löschen
    1. menu
    2. price
    3. dish_ingridients
    3. dishes
     */
    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/lieferservice?user=root";
        DB_Connector dataBaseConnector = new DB_Connector(url);
        TerminalOutput output = new TerminalOutput();
        DishRepo dishRepo = new DishRepo(dataBaseConnector, output);
        UserRepo userRepo = new UserRepo(dataBaseConnector,output);
        IngridientsRepo ingridientsRepo = new IngridientsRepo(dataBaseConnector, output);
        Writer writer = new Writer();
        Restaurant castello = new Restaurant(dataBaseConnector,output,dishRepo,ingridientsRepo);
        Evaluation evaluation = new Evaluation(dataBaseConnector,output,dishRepo,ingridientsRepo,userRepo,writer);
        GUI gui = new GUI(castello,evaluation,output);
       gui.run();


    }
}
