package com.company.controller;

import com.company.controller.Evaluation;
import com.company.controller.Restaurant;
import com.company.view.TerminalOutput;

import java.util.Scanner;

public class GUI {
    Restaurant restaurant;
    Evaluation evaluation;
    TerminalOutput output;
    Scanner scanner = new Scanner(System.in);

    public GUI(Restaurant restaurant, Evaluation evaluation, TerminalOutput output) {
        this.restaurant = restaurant;
        this.evaluation = evaluation;
        this.output = output;
    }

    public void run () {
        boolean isRun = true;

        while (isRun) {
            output.outPutString("Wollen Sie ein neues Gericht (1) oder auf Ihre Auswertungen (2) zu greifen? Beenden (3)");
            String tempString = scanner.next();
            switch (tempString) {
                case "1": writeDish();
                    output.outPutString("Wollen Sie noch ein Gericht hinzufügen? J/N");
                    String tempString2 = scanner.next();
                    if (tempString2.equalsIgnoreCase("J")) {
                        writeDish();
                    }  else if (tempString2.equalsIgnoreCase("N")) {

                    }break;
                case "2": evalutionChoice(); break;
                case "3": isRun = false; output.outPutString("Dankeschön\nSchönen Tag noch"); break;
            }

        }
    }

    public void writeDish () {
        this.restaurant.writeDish();
    }

    public void evalutionChoice () {
        boolean isRun = true;

        while (isRun){
            output.outPutStringLanding("Was möchten Sie wissen?\n1. Wie viel bestellt wurde\n2. Bestllung pro Kunde\n" +
                                        "3. Bestellung pro Dorf\n4. Meist verkauftes Gericht\n5. Verkaufte Gerichte in Absteigender Reihenfolge\n" +
                                        "6. Einkausliste drucken\n7. Rechnungen drucken\n8. beenden");
            String tempString = scanner.next();
            switch (tempString) {
                case "1": evaluation.howMuchOrder(); break;
                case "2": evaluation.perCustomOrder(); break;
                case "3": evaluation.perTown(); break;
                case "4": evaluation.mostOfDish(); break;
                case "5": evaluation.dishSorted(); break;
                case "6": evaluation.writeList();break;
                case "7": evaluation.writeBill(); break;
                case "8": run(); break;
            }
        }

    }
}
