package com.company.controller;

import com.company.Database.models.*;
import com.company.Database.repository.DB_Connector;
import com.company.Database.repository.UserRepo;
import com.company.view.TerminalOutput;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Delivery {
    private String name;
    private DB_Connector db_connector;
    private UserRepo userRepo;
    private Scanner scanner = new Scanner(System.in);
    private int logInCounter = 0;
    private int orderCounter = 0;
    private int newOrderNR;
    private int isFreeDelivery = 0; //boolean
    private int dishID;
    private int orderDetailID;
    private double userDeliveryPrice;
    private double bill;
    private int host;
    public User user;
    private Dishes orderDish;
    public ArrayList<DeliveryPlaces> deliveryPlaces = new ArrayList<>();
    public ArrayList<Dishes> orderDishes = new ArrayList<>();
    public ArrayList<Ingridients> addedIng = new ArrayList<>();
    public ArrayList<Ingridients> removeIng = new ArrayList<>();

    public Delivery(String name ,DB_Connector db_connector,UserRepo userRepo) {
        this.name = name;
        this.db_connector = db_connector;
        this.userRepo = userRepo;
    }

    /*
    Scanner eingabe muss man manchmal doppelt eingeben ...
    ursache noch nicht genau heraus gefunden.. nextLine durch next ersetzt geht besser
    aber noch nicht zu 100%
    */

    //ToDO get methode logIn changeable for other programms and the class Customer in a new project and handle like DB_Connector
    public void logIn(Customer customer, Restaurant restaurant, TerminalOutput myOutPut) {
        String title = "Willkommen bei "+this.name+"\nEinlogen (1) oder Account erstellen (2) ?";
        myOutPut.outPutString(title);
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("2")) {
            customer.createUser();
            logIn(customer, restaurant, myOutPut);  //go to createUser
        } else if (choice.equalsIgnoreCase("1")) {  //login
            String email = "Email eingeben";
            myOutPut.outPutStringLanding(email);
            String userEmail = scanner.nextLine();
            String pw = "Bitte Passwort eingeben";
            myOutPut.outPutStringLanding(pw);
            String password = scanner.nextLine();
            if (!customer.isRightLogin(userEmail, password)) {  //check right login
                String falseLogin = "Falsche Login Daten";
                myOutPut.outPutStringLanding(falseLogin);
                this.logInCounter++;
                if (this.logInCounter % 3 == 0) {
                    String info = "Bitte geben Sie UserID, Email und Wohnort an für Account Detials";
                    myOutPut.outPutStringLanding(info);
                    String userId = "UserID";
                    myOutPut.outPutStringLanding(userId);
                    int userID = scanner.nextInt();
                    String email1 = "Email eingeben";
                    myOutPut.outPutStringLanding(email1);
                    String bug = scanner.nextLine();
                    String userEMail = scanner.nextLine();   //get your acc information
                    String livingPlace = "Wohnort";
                    myOutPut.outPutStringLanding(livingPlace);
                    String place = scanner.nextLine();
                    customer.custAccInfo(userID, userEMail, place);
                    logIn(customer, restaurant, myOutPut);
                }
                logIn(customer, restaurant, myOutPut);
            } else {
                user = userRepo.findWithEmail(userEmail);   //safe user + check deliveryPlace + get the last ordernumber
                if (isDelivery()) {                 // to get the new number for the new order
                   String loggedIn = "loged in";
                   myOutPut.outPutStringLanding(loggedIn);
                    lastOrderNumber( myOutPut);
                    this.newOrderNR++;
                    printDeliverPlaces(myOutPut);
                    whichMenu(restaurant, myOutPut);
                } else {
                    String sorry = "Wir liefern nicht zu Ihrem Wohnort SORRY";
                    myOutPut.outPutStringLanding(sorry);  // ToDO maby new funktion to get the food by the customer self
                    System.exit(0);          // ToDo delivery price = 0 + mabay pizza cost only 8 €
                }
            }

        }  else{
            System.out.println("Diese eingabe Kenne ich nicht");
            logIn(customer, restaurant, myOutPut);
        }
    }

    public boolean isDelivery() {  //check deliveryPlaces with them from the logged in customer
        boolean isDelivery = false;
        for (DeliveryPlaces deliveryPlace : deliveryPlaces) {
            if (deliveryPlace.getPlace().equalsIgnoreCase(user.getPlace())) {
                isDelivery = true;
                break;
            }
        }
        return isDelivery;
    }

    public void choiceRestaurant (Restaurant restaurant, TerminalOutput output) {
        output.outPutStringLanding("In welchem Restaurant wollen Sie bestellen?");
    }

    public void whichMenu(Restaurant restaurant, TerminalOutput output) {
        String head = "\t\t\t * * * Unsere Speisekarte * * * \n" +
                "Willst du Vorspeisen (VS) - Hauptspeisen (HS)  - ALLE sehen (all)";
        output.outPutStringLanding(head);
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("VS")) {
            headOfDish(output);
            output.printMenu(restaurant,choice);
            toOrder(restaurant, output);
        } else if (choice.equalsIgnoreCase("HS")) {
            headOfDish(output);
            output.printMenu(restaurant,choice);
            toOrder(restaurant, output);
        } else if (choice.equalsIgnoreCase("all")) {
            headOfDish(output);
            output.printMenu(restaurant,choice);
            toOrder(restaurant, output);
        } else {
            whichMenu(restaurant, output);
        }
    }

    public void toOrder(Restaurant restaurant, TerminalOutput output) {
        output.outPutStringLanding("Was möchtest du bestellen? ID bitte eingeben");
        this.dishID = scanner.nextInt();
        createOrderDish(restaurant,output);
        output.outPutStringLanding("Richtige Auswahl? (J) JA / (N) NEIN ");
        String bug = scanner.nextLine();
        String tempSTring = scanner.nextLine();
        if (tempSTring.equalsIgnoreCase("n")) {
            this.orderDish = null;
            this.orderDishes.remove(this.orderCounter);
            this.dishID = 0;
            whichMenu(restaurant,output);
           // toOrder(restaurant, output);
        } else if (tempSTring.equalsIgnoreCase("j")) {
            output.outPutString("Möchtest du was verändern? (J) JA / (N) NEIN \n Jede zusätzliche Zutat € 0,75");
            String tempString = scanner.nextLine();
            if (tempString.equalsIgnoreCase("j")) {
                changeIngridients(restaurant,output);
                wantMore(restaurant, output);

            } else if (tempString.equalsIgnoreCase("n")) {
                printOrderDish(output);
                wantMore(restaurant, output);
            }
        }
    }

    public void wantMore(Restaurant restaurant, TerminalOutput output) {
        int freeDelivery = 15;
        if (this.bill >= freeDelivery) {
            output.outPutString("Ihr momentaner Bestellwert: " + this.bill + "\nWir Liefern Gratis !");
        } else {
            output.outPutString("Ab einem Bestellwert von € 15 liefern wir Gratis\nIhr Momentaner Bestellwert: " + this.bill);
            output.outPutString("Ihre Lieferkosten betragen:");
            output.printDeliveryPlaceInfo(this.deliveryPlaces, this.user);
            for (DeliveryPlaces deliveryPlace : deliveryPlaces) {
                if (deliveryPlace.getPlace().equalsIgnoreCase(user.getPlace())) {
                    this.userDeliveryPrice = deliveryPlace.getPrice();
                }
            }
        }
        output.outPutStringLanding("Wollen Sie noch eine Bestellung aufgeben JA (J) / NEIN (N)");
        String tempString = scanner.next();
        if (tempString.equalsIgnoreCase("J")) {
            this.orderCounter++;
            whichMenu(restaurant, output);
        } else if (tempString.equalsIgnoreCase("N")) {
            if (this.bill >= freeDelivery) {
                this.isFreeDelivery = 1;
                goTPay(output);
            } else {
                this.bill += this.userDeliveryPrice;
                goTPay(output);
            }
        }
    }

    public void goTPay(TerminalOutput output) {
        output.outPutStringLanding("Ihre Bestellung zur 'adresse' " + user.getPlace());
        orderDishes.forEach(dishes -> output.outPutString(dishes.getName() + " - " + dishes.getPrice()));
       output.outPutString("Ihre Rechnung: " + this.bill);
        insertOrder();
    }

    public void createOrderDish(Restaurant restaurant, TerminalOutput output) {
        for (int i = 0; i < restaurant.dischesList.size(); i++) {
            if (restaurant.dischesList.get(i).getDishId() == dishID) {
                orderDish = new Dishes(restaurant.dischesList.get(i).getName(),
                        restaurant.dischesList.get(i).getDishId(),
                        restaurant.dischesList.get(i).getDishTyp(),
                        restaurant.dischesList.get(i).getPrice());
                orderDishes.add(orderDish);
                for (int j = 0; j < restaurant.dischesList.get(i).dishIngridients.size(); j++) {
                    orderDishes.get(this.orderCounter).dishIngridients.add(restaurant.dischesList.get(i).dishIngridients.get(j));
                }
                output.outPutStringLanding(orderDishes.get(orderCounter).getName());
                output.outPutString("Zutaten");
                orderDish.dishIngridients.forEach(ingridients -> output.outPutString(ingridients.getId() + " - " + ingridients.getName()));
            }
        }
    }

    public void changeIngridients(Restaurant restaurant, TerminalOutput output) {
        output.outPutStringLanding("Wollen Sie hinzufügen (1) oder Zutat löschen (2) ?");
        String tempString = scanner.nextLine();
        if (tempString.equalsIgnoreCase("1")) {
            int index = this.orderDishes.indexOf(orderDish);
            this.orderDishes.get(index).setHasAddIng(1);
            output.outPutString("Verfügbare Zutaten");
            output.outPutString("ID - Zutat");
            for (int i = 0; i < restaurant.ingridientsList.size(); i++) {
                output.outPutString(i + " - " + restaurant.ingridientsList.get(i).getName());
            }
            output.outPutString("ID für Eingabe bitte weiterverwenden");
            output.outPutString("Welche Zutat wollen Sie hinzufügen?");
            int tempInt = scanner.nextInt();
            orderDishes.get(this.orderCounter).dishIngridients.add(restaurant.ingridientsList.get(tempInt));
            this.addedIng.add(restaurant.ingridientsList.get(tempInt));
            double plusIngridient = 0.75;
            orderDish.setPrice(orderDish.getPrice() + plusIngridient);
            printOrderDish(output);
        } else if (tempString.equalsIgnoreCase("2")) {
            int index = this.orderDishes.indexOf(orderDish);
            this.orderDishes.get(index).setHasDeleteING(1);
            output.outPutStringLanding("Welche Zutat wollen Sie weglassen?");
            for (int i = 0; i < orderDish.dishIngridients.size(); i++) {
                output.outPutString(i + " - " + orderDishes.get(this.orderCounter).dishIngridients.get(i).getName());
            }
            int temInt = scanner.nextInt();
            orderDish.dishIngridients.remove(temInt);
            this.removeIng.add(restaurant.ingridientsList.get(temInt));
            printOrderDish(output);
        }
    }

    public void insertUserIngWish() {
        String query;
        for (Dishes dish : orderDishes) {
            query = "SELECT order_detail.id FROM `order_detail`" +
                    " WHERE order_detail.order_id =" + this.newOrderNR + " AND order_detail.dish_order_id =" + dish.getDishId();
            ResultSet rs = db_connector.fetchData(query); //searching last order_id
            try {
                while (rs.next()) {
                    this.orderDetailID = rs.getInt("id");
                }
                if (dish.getHasDeleteING() > 0) {  //check to delete Ingriedents + insert
                    for (int j = 0; j < removeIng.size(); j++) {
                        String sql = " INSERT INTO `order_detail_ingriedients`" +
                                "( `order_detail_id`, `ing_order_id`, `removed_ing`, `added_ing`) " +
                                "VALUES ('" + this.orderDetailID + "' , '" + this.removeIng.get(j).getId() + "' , '" + orderDishes.get(j).getHasDeleteING() +
                                "' , '" + orderDishes.get(j).getHasAddIng() + "' )";
                        db_connector.insert(sql);
                    }
                } else if (dish.getHasAddIng() > 0) { //check to adding Ingriedients + insert
                    for (int k = 0; k < addedIng.size(); k++) {
                        String sql = " INSERT INTO `order_detail_ingriedients`" +
                                "( `order_detail_id`, `ing_order_id`, `removed_ing`, `added_ing`) " +
                                "VALUES ('" + this.orderDetailID + "' , '" + this.addedIng.get(k).getId() + "' , '" + orderDishes.get(k).getHasDeleteING() +
                                "' , '" + orderDishes.get(k).getHasAddIng() + "' )";
                        db_connector.insert(sql);
                    }
                }
            } catch (SQLException e) {
                throw new Error("Problem ", e);
            } finally {
                db_connector.closeConnection();
            }
        }
    }

    public void insertOrder() {
        /*

        SELECT DISTINCT menu.wirt_id FROM `dish_ingridients`
INNER JOIN menu ON menu.dish_id = dish_ingridients.dish_id
WHERE dish_ingridients.dish_id = 20  = dishID

wirt sucht
    */
                    String sql = "INSERT INTO `user_order`" +
                            "(`id`, `user_id`, `total_price`, `wirt_id`) " +
                            "VALUES" +
                            " ('" + this.newOrderNR + "' , '" + user.getUserID() + "' , '" + this.bill + "' , '" + userRepo.whichHost(orderDish.getDishId()) + "')";
                    db_connector.insert(sql);

        for (Dishes dish : orderDishes) {
            String sql2 = "INSERT INTO `order_detail`" +
                    "(`order_id`, `dish_order_id`, `price_order_dish_id`, `is_free_delivery`)"
                    + " VALUES " +
                    "('" + this.newOrderNR + "' , '" + dish.getDishId() + "' , '"
                    + dish.getDishId() + "' , '" + this.isFreeDelivery + "' )";
            db_connector.insert(sql2);
        }
                insertUserIngWish();
    }

    public void lastOrderNumber(TerminalOutput output) {

            String query = "SELECT * FROM `user_order` ORDER by user_order.id DESC LIMIT 1";
            ResultSet rs = db_connector.fetchData(query);
            if (rs == null) {
                output.outPutStringLanding("rs == null, problem check query or conn");
                return;
            }
            try {
                while (rs.next()) {
                    this.newOrderNR = rs.getInt("id");
                }
            } catch (SQLException e) {
                throw new Error("Problem ", e);
            } finally {
               db_connector.closeConnection();
            }
    }

    public void printOrderDish(TerminalOutput output) {
        output.printOrder(orderDishes , this.orderCounter);
        this.bill += orderDishes.get(orderCounter).getPrice();
        output.outPutStringLanding("Ihr waren wert: " + this.bill);
    }

    public void headOfDish(TerminalOutput output) {
       output.outPutString("ID  -  Name  -  Typ - Preis in €");
    }

    public void printDeliverPlaces(TerminalOutput output) {
        output.printDeliveryPlaces(this.deliveryPlaces
        );
    }

}
