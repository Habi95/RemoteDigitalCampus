package com.company.controller;

import com.company.Database.models.*;
import com.company.Database.repository.DB_Connector;
import com.company.Database.repository.DishRepo;
import com.company.Database.repository.IngridientsRepo;
import com.company.Database.repository.UserRepo;
import com.company.view.TerminalOutput;
import com.company.view.Writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;


public class Evaluation    {
    DB_Connector db_connector;
    TerminalOutput output;
    DishRepo dishRepo;
    IngridientsRepo ingridientsRepo;
    UserRepo userRepo;
    Writer writer;
    ArrayList<BillEvaluation> billEvaluations = new ArrayList<>();
    ArrayList<IngridientsEvaluation> ingridientsEvaluations = new ArrayList<>();
    ArrayList<UserEvaluation> users = new ArrayList<>();
    ArrayList<TownEvaluation> town = new ArrayList<>();
    ArrayList<DishEvaluation> dishes = new ArrayList<>();
    String filePath = "C:\\Users\\DCV\\Desktop\\HelloWorld\\RemoteDigitalCampus\\Zutatenliste_Wirt_delivery.txt";
    String filePath2 = "C:\\Users\\DCV\\Desktop\\HelloWorld\\RemoteDigitalCampus\\Order_Wirt_Delivery.txt";
    File ingridientsList = new File(filePath);
    File billList = new File(filePath2);
    FileWriter listWriter;
    FileWriter billWriter;
    {
        try {
            listWriter = new FileWriter(ingridientsList,true);
            billWriter = new FileWriter(billList,true);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public Evaluation(DB_Connector db_connector, TerminalOutput output, DishRepo dishRepo, IngridientsRepo ingridientsRepo, UserRepo userRepo, Writer writer) {
        this.db_connector = db_connector;
        this.output = output;
        this.dishRepo = dishRepo;
        this.ingridientsRepo = ingridientsRepo;
        this.userRepo = userRepo;
        this.writer = writer;
    }

    public void howMuchOrder ()
    {
        output.outPutStringLanding("Bis jetzt wurden gesamt " + userRepo.howMuchOrdering()
                                    + " Bestellungen aufgegeben");
    }

    public void perCustomOrder () {
        users = userRepo.orderPerCustomer();
        output.evaluationPerCustomer(users);
    }

    public void perTown () {
        town = userRepo.orderPerTown();
        output.evaluationPerTown(town);
    }

    public void mostOfDish () {
        output.outPutString("Das Gericht: " + dishRepo.mostOf().getDishName() + "wurde am meisten Bestellt.\nEs wurde " +
                                    dishRepo.mostOf().getOrderCount() + " mal Bestellt");
    }

    public void dishSorted () {
        dishes = dishRepo.dishGroupByPopular();
        output.printSortedDish(dishes);
    }

    public void writeList () {
        ingridientsEvaluations = ingridientsRepo.ingridientsEvaluations();
        writer.writeIngList(listWriter,ingridientsEvaluations);
    }

    public void writeBill () {
        billEvaluations = userRepo.bill();
        writer.writeBill(billWriter,billEvaluations);
    }

}



