package com.company.controller;

import com.company.Database.models.Dishes;
import com.company.Database.models.Ingridients;
import com.company.Database.models.Menu;
import com.company.Database.repository.DB_Connector;
import com.company.Database.repository.DishRepo;
import com.company.Database.repository.IngridientsRepo;
import com.company.view.TerminalOutput;

import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant {
    Scanner scanner = new Scanner(System.in);
    DB_Connector db_connector;
    TerminalOutput output;
    DishRepo dishRepo;
    IngridientsRepo ingridientsRepo;
    int host;
    String vorspeise = "1";
    String hauptspeise = "2";
    ArrayList<Ingridients> ingridientsList = new ArrayList<>();
    ArrayList<Ingridients> ingOfLastDish = new ArrayList<>();

    public Restaurant(DB_Connector db_connector, TerminalOutput output, DishRepo dishRepo, IngridientsRepo ingridientsRepo) {
        this.db_connector = db_connector;
        this.output = output;
        this.dishRepo = dishRepo;
        this.ingridientsRepo = ingridientsRepo;
    }

    public void writeDish(int hostId) {
        this.host = hostId;
        output.outPutStringLanding("Wollen Sie eine Hauptspeise (HS) oder eine Vorspeise (VS) erstellen?");
        String temp1 = scanner.nextLine();
        if (temp1.equalsIgnoreCase("VS")) {
            output.outPutString("Wie heißt die Vorspeise?");
            String temp = scanner.nextLine();
            dishRepo.create(new Dishes(temp, dishRepo.lastDishID() + 1, vorspeise, 0));
            addIngridient();
        }
        if (temp1.equalsIgnoreCase("HS")) {
            output.outPutString("Wie heißt die Hauptspeise?");
            String temp = scanner.nextLine();
            dishRepo.create(new Dishes(temp, dishRepo.lastDishID() + 1, hauptspeise, 0));
            addIngridient();
        } else {
            output.outPutString("Diese Eingabe war nicht korrekt");
            writeDish(hostId);
        }
    }

    public void addIngridient() {
        Ingridients ingridients;
        boolean run = true;
        while (run) {
            lastAddedDish();
            ingOfDish();
            ingriedientsList();
            output.outPutString("Wollen Sie noch eine Zutata hinzufügen? J / N");
            String bug1 = scanner.nextLine();
            String temp1 = scanner.nextLine();
            if (temp1.equalsIgnoreCase("J")) {
                output.outPutString("bitte in Zutaten ID eingeben");
                String tempIng = scanner.nextLine();
                ingridients = new Ingridients(dishRepo.lastDishID(), tempIng);
                ingridientsRepo.dishGetIng(ingridients);
            } else if (temp1.equalsIgnoreCase("N")) {
                run = false;
                output.outPutString("Danke für die Eingabe");
                addToMenu();
                addPrice();
            }
        }
    }

    public void lastAddedDish() {
        Dishes lastDish = dishRepo.lastAdded();
        output.printLastAddedDish(lastDish);
    }

    public void ingOfDish() {
        ingOfLastDish = ingridientsRepo.findDishIng(dishRepo.lastDishID());
        output.printIngriedients(ingOfLastDish);
    }

    public void ingriedientsList() {
        ingridientsList = ingridientsRepo.findAll(this.host);
        output.printIngriedients(ingridientsList);
    }

    public void addToMenu() {
        dishRepo.addToMenu(new Menu(this.host, dishRepo.lastDishID()));
    }

    public void addPrice() {
        output.outPutString("Preis eingeben: Info , als kommer");
        double price = scanner.nextDouble();
        Dishes dishes = dishRepo.findOne(dishRepo.lastDishID());
        dishes.setPrice(price);
        dishRepo.addPrice(dishes);
        output.outPutString("Danke für die Eingabe");

    }

    public void createIng (int hostID) {
        output.outPutString("Wie heißt die Zutat?");
        String name = scanner.nextLine();
        ingridientsRepo.createIng( new Ingridients(hostID , name));
        output.outPutString("Wurde Erfolgreich hinzugefügt");
    }
}
