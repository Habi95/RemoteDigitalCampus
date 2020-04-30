package com.company.Database.models;


import com.company.Database.repository.DB_Connector;
import com.company.Database.repository.RestaurantRepo;
import com.company.Database.repository.UserRepo;
import com.company.view.TerminalOutput;

import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
    private Scanner scanner = new Scanner(System.in);
    private DB_Connector db_connector;
    private RestaurantRepo userRepo;
    private TerminalOutput output;

    public Customer(DB_Connector db_connector, RestaurantRepo userRepo, TerminalOutput output) {
        this.db_connector = db_connector;
        this.userRepo = userRepo;
        this.output = output;
    }

    public void createUser() {
        output.outPutString("Willkommen zur Account erstellung");
        output.outPutString("Wie heißt dein Restaurant?");
        String name = scanner.next();
        output.outPutString("Email");
        String email = scanner.nextLine();

        if (!isEmailFree(email)) {
            createUser();
        } else {
            output.outPutString("Passwort");
            String password = scanner.nextLine();
            output.outPutString("ORT");
            String place = scanner.nextLine();
            output.outPutString("Was für eine art Restaurant sind Sie?");
            String typ = scanner.nextLine();
            userRepo.create(new HostUser(userRepo.lastUserId() + 1, name, email, password, place,typ));
            output.outPutString("Account wurde erstellt\nIhre Daten: \n");
            accInfo(userRepo.lastUserId());

        }
    }

    public void accInfo(int id) {
        HostUser newUser = userRepo.findOne(id);

        output.outPutString("Ihre Anmelde Daten lauten wie folgt:\nUser ID: " + newUser.getId() +
                "\nName: " + newUser.getName() +
                "\nEmail: " + newUser.getEmail() +
                "\nPasswort: " + newUser.getPassword() +
                "\nLieferadresse: " + newUser.getPlace());
    }

    public void custAccInfo(int userId, String userEmail, String userPlace) {
        HostUser tempUser = userRepo.findOne(userId);
        if (tempUser.getEmail().equals(userEmail) && tempUser.getPlace().equals(userPlace)) {
            accInfo(tempUser.getId());
        } else {
            output.outPutString("Wrong login data");
        }
    }

    public boolean isRightLogin(String userEmail, String userPassword) {
        ArrayList<HostUser> tempUser = userRepo.findAll();
        boolean isRightLogin = false;
        for (int i = 0; i < tempUser.size(); i++) {
            if (tempUser.get(i).getEmail().equalsIgnoreCase(userEmail)  &&
                    tempUser.get(i).getPassword().equalsIgnoreCase(userPassword)) {
                isRightLogin = true;
            }
        }

        return isRightLogin;
    }

    public boolean isEmailFree(String email) {
        boolean isEmailFree = false;
        if (!userRepo.isFreeEmail(email)) {
            output.outPutString("Email vergeben");
        } else {
            isEmailFree = true;
        }
        return isEmailFree;

    }

    public HostUser hostUser (String userEmail) {
        HostUser hostUser = userRepo.findWithEmail(userEmail);
       return hostUser;
    }

}
