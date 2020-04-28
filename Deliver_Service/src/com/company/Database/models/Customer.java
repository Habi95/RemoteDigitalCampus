package com.company.Database.models;


import com.company.Database.repository.DB_Connector;
import com.company.Database.repository.UserRepo;
import com.company.view.TerminalOutput;

import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
    private Scanner scanner = new Scanner(System.in);
    private DB_Connector db_connector;
    private UserRepo userRepo;
    private TerminalOutput output;

    public Customer(DB_Connector db_connector, UserRepo userRepo, TerminalOutput output) {
        this.db_connector = db_connector;
        this.userRepo = userRepo;
        this.output = output;
    }

    public void createUser() {
        output.outPutString("Willkommen zur Account erstellung");
        output.outPutString("Email");
        String email = scanner.nextLine();

        if (!isEmailFree(email)) {
            createUser();
        } else {
            output.outPutString("Passwort");
            String password = scanner.nextLine();
            output.outPutString("ORT");
            String place = scanner.nextLine();
            userRepo.create(new User(userRepo.lastUserId() + 1, email, password, place));
            output.outPutString("Account wurde erstellt\nIhre Daten: \n");
            accInfo(userRepo.lastUserId());

        }
    }

    public void accInfo(int id) {
        User newUser = userRepo.findOne(id);

        output.outPutString("Ihre Anmelde Daten lauten wie folgt:\nUser ID: " + newUser.getUserID() +
                "\nEmail: " + newUser.getEmail() +
                "\nPasswort: " + newUser.getPassword() +
                "\nLieferadresse: " + newUser.getPlace());
    }

    public void custAccInfo(int userId, String userEmail, String userPlace) {
        User tempUser = userRepo.findOne(userId);
        if (tempUser.getEmail().equals(userEmail) && tempUser.getPlace().equals(userPlace)) {
            accInfo(tempUser.getUserID());
        } else {
            output.outPutString("Wrong login data");
        }
    }

    public boolean isRightLogin(String userEmail, String userPassword) {
        ArrayList<User> tempUser = userRepo.findAll();
        boolean isRightLogin = false;
        for (int i = 0; i < tempUser.size(); i++) {
            if (tempUser.get(i).getEmail().equalsIgnoreCase(userEmail) &&
                    tempUser.get(i).getPassword().equalsIgnoreCase(userPassword)) {
                isRightLogin = true;
            }
        }

        return isRightLogin;
    }

    public boolean isEmailFree(String email) {
        int freeEmail = userRepo.freeEmail(email);
        boolean isEmailFree = false;
        if (freeEmail > 0) {
            output.outPutString("Email vergeben");
        } else {
            isEmailFree = true;
        }
        return isEmailFree;

    }

}
