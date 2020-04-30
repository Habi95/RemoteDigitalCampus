package com.company.controller;

import com.company.Database.models.Customer;
import com.company.Database.models.HostUser;
import com.company.controller.Evaluation;
import com.company.controller.Restaurant;
import com.company.view.TerminalOutput;

import java.util.Scanner;

public class GUI {
  private String name;
  private int logInCounter = 0;
  private Restaurant restaurant;
  private Evaluation evaluation;
  private TerminalOutput output;
  private Customer customer;
  private Scanner scanner = new Scanner(System.in);
  private HostUser hostUser;


    public GUI(String name, Restaurant restaurant, Evaluation evaluation, TerminalOutput output, Customer customer) {
        this.name = name;
        this.restaurant = restaurant;
        this.evaluation = evaluation;
        this.output = output;
        this.customer = customer;
    }

    public void run() {
        boolean isRun = true;

        while (isRun) {
            output.outPutString("Wollen Sie ein neues Gericht (1) oder eine Zutat hinzufügen (2) oder auf Ihre Auswertungen (3) zu greifen? Beenden (4)");
            String tempString = scanner.next();
            switch (tempString) {
                case "1":
                    writeDish();
                    output.outPutString("Wollen Sie noch ein Gericht hinzufügen? J/N");
                    String tempString2 = scanner.next();
                    if (tempString2.equalsIgnoreCase("J")) {
                        writeDish();
                    } else if (tempString2.equalsIgnoreCase("N")) {

                    }
                    break;
                case "2":
                    restaurant.createIng(hostUser.getId()); break;

                case "3":
                    evalutionChoice();
                    break;
                case "4":
                    isRun = false;
                    output.outPutString("Dankeschön\nSchönen Tag noch");
                    break;
            }

        }
    }

    public void writeDish() {
        this.restaurant.writeDish(hostUser.getId());
        run();
    }

    public void evalutionChoice() {
        boolean isRun = true;

        while (isRun) {
            output.outPutStringLanding("Was möchten Sie wissen?\n1. Wie viel bestellt wurde\n2. Bestllung pro Kunde\n" +
                    "3. Bestellung pro Dorf\n4. Meist verkauftes Gericht\n5. Verkaufte Gerichte in Absteigender Reihenfolge\n" +
                    "6. Einkausliste drucken\n7. Rechnungen drucken\n8. beenden");
            String tempString = scanner.next();
            switch (tempString) {
                case "1":
                    evaluation.howMuchOrder(hostUser.getId());
                    break;
                case "2":
                    evaluation.perCustomOrder(hostUser.getId());
                    break;
                case "3":
                    evaluation.perTown(hostUser.getId());
                    break;
                case "4":
                    evaluation.mostOfDish(hostUser.getId());
                    break;
                case "5":
                    evaluation.dishSorted(hostUser.getId());
                    break;
                case "6":
                    evaluation.writeList(hostUser.getId());
                    break;
                case "7":
                    evaluation.writeBill(hostUser.getId());
                    break;
                case "8":
                    run();
                    break;
            }
        }

    }

    public void logIn() {
        String title = "Willkommen bei "+this.name+"\nEinlogen (1) oder Account erstellen (2) ?";
        output.outPutString(title);
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("2")) {
            customer.createUser();
            logIn();  //go to createUser
        } else if (choice.equalsIgnoreCase("1")) {  //login
            String email = "Email eingeben";
            output.outPutStringLanding(email);
            String userEmail = scanner.nextLine();
            String pw = "Bitte Passwort eingeben";
            output.outPutStringLanding(pw);
            String password = scanner.nextLine();
            if (!customer.isRightLogin(userEmail, password)) {  //check right login
                String falseLogin = "Falsche Login Daten";
                output.outPutStringLanding(falseLogin);
                this.logInCounter++;
                if (this.logInCounter % 3 == 0) {
                    String info = "Bitte geben Sie UserID, Email und Wohnort an für Account Detials";
                    output.outPutStringLanding(info);
                    String userId = "UserID";
                    output.outPutStringLanding(userId);
                    int userID = scanner.nextInt();
                    String email1 = "Email eingeben";
                    output.outPutStringLanding(email1);
                    String bug = scanner.nextLine();
                    String userEMail = scanner.nextLine();   //get your acc information
                    String livingPlace = "Wohnort";
                    output.outPutStringLanding(livingPlace);
                    String place = scanner.nextLine();
                    customer.custAccInfo(userID, userEMail, place);
                    logIn();
                }
                logIn();
            } else {
                hostUser = customer.hostUser(userEmail);
                output.outPutStringLanding("logged in");
               run();
            }

        }  else{
            System.out.println("Diese eingabe Kenne ich nicht");
            logIn();
        }
        run();
    }
}
