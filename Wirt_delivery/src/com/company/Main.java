package com.company;

import com.company.Database.repository.DB_Connector;
import com.company.Database.repository.DishRepo;
import com.company.Database.repository.IngridientsRepo;
import com.company.Database.repository.UserRepo;
import com.company.controller.Restaurant;
import com.company.controller.Evaluation;
import com.company.view.TerminalOutput;

public class Main {

    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/lieferservice?user=root";
        DB_Connector dataBaseConnector = new DB_Connector(url);
        TerminalOutput output = new TerminalOutput();
        DishRepo dishRepo = new DishRepo(dataBaseConnector, output);
        UserRepo userRepo = new UserRepo(dataBaseConnector,output);
        IngridientsRepo ingridientsRepo = new IngridientsRepo(dataBaseConnector, output);
        Restaurant castello = new Restaurant(dataBaseConnector,output,dishRepo,ingridientsRepo);
        Evaluation evaluation = new Evaluation(dataBaseConnector,output,dishRepo,ingridientsRepo,userRepo);
        castello.writeDish();
        //castello.ingriedientsList();
        //evaluation.writeList();
    }
}
