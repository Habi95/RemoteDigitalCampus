package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Delivery {
    Connection conn = null;
    String url = "jdbc:mysql://localhost:3306/lieferservice?user=root";
    Scanner scanner = new Scanner(System.in);
    int logInCounter = 0;
    int orderCounter = 0;
    int freeDelivery = 15;
    int newOrderNR;
    int isFreeDelivery = 0; //boolean
    int dishID;
    int  orderDetailID;
    double userDeliveryPrice;
    double userDeliveryID;
    double bill;
    double plusIngridient = 0.75;
    User user;
    Dishes orderDish;
    ArrayList<DeliveryPlaces> deliveryPlaces = new ArrayList<>();
    ArrayList<Dishes> orderDishes = new ArrayList<>();
    ArrayList<Ingridients> addedIng = new ArrayList<>();
    ArrayList<Ingridients> removeIng = new ArrayList<>();


    /*
    Scanner eingabe muss man manchmal doppelt eingeben ...
    ursache noch nicht genau heraus gefunden.. nextLine durch next ersetzt geht besser
    aber noch nicht zu 100%
    */




    public void logIn(Customer customer, Restaurant restaurant) {

        try {
            System.out.println("Willkommen bei QuickFood\nEinlogen (1) oder Account erstellen (2) ?");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("2")) {
                customer.createUser();
                logIn(customer, restaurant);
            } else if (choice.equalsIgnoreCase("1")){
                System.out.println("Email eingeben");
                String userEmail = scanner.nextLine();
                System.out.println("Bitte Passwort eingeben");
                String password = scanner.nextLine();
                if (!customer.isRightLogin(userEmail, password)) {
                    System.out.println("Falsche Login Daten");
                    this.logInCounter++;

                    if (this.logInCounter % 3 == 0) {
                        System.out.println("Bitte geben Sie UserID, Email und Wohnort an für Account Detials");

                        System.out.println("UserID");
                        int userID = scanner.nextInt();

                        System.out.println("Email");
                        String enter = scanner.nextLine();
                        String userEMail = scanner.nextLine();


                        System.out.println("Wohnort");
                        String place = scanner.nextLine();

                        customer.custAccInfo(userID, userEMail, place);
                        logIn(customer, restaurant);
                    }
                    logIn(customer, restaurant);
                } else {
                    conn = DriverManager.getConnection(url);
                    Statement stmt = null;

                    String query = "SELECT  `user_id` , place FROM `user` Where user.email = '" + userEmail + "'";


                    try {
                        stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(query);

                        while (rs.next()) {
                            int userID = rs.getInt("user_id");
                            String place = rs.getString("place");
                            user = new User(userID, userEmail, place);
                            if (isDelivery() == true) {
                                System.out.println("loged in");
                                lastOrderNumber();
                                this.newOrderNR++;
                                System.out.println();
                                whichMenu(restaurant);
                            } else {
                                System.out.println("Wir liefern nicht zu Ihrem Wohnort SORRY");
                                System.exit(0);

                            }




                        }


                    } catch (SQLException e) {
                        throw new Error("Problem ", e);
                    } finally {
                        try {
                            if (stmt != null)
                                stmt.close();
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }


            } else {
                System.out.println("Diese eingabe Kenne ich nicht");
                logIn(customer,restaurant);
            }
        } catch (SQLException e) {
            throw new Error("Problem ", e);
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public boolean isDelivery () {
        boolean isDelivery = false;
        for (int i = 0; i < deliveryPlaces.size() ; i++) {
            if (deliveryPlaces.get(i).place.equalsIgnoreCase(user.place)) {
                isDelivery = true;
            }

        }
       return isDelivery;
    }

    public void whichMenu(Restaurant restaurant) {

        System.out.println("\t\t\t * * * Unsere Speisekarte * * * \n");
        System.out.println("Willst du Vorspeisen (VS) - Hauptspeisen (HS)  - ALLE sehen (all)");
        String choice = scanner.nextLine();


        if (choice.equalsIgnoreCase("VS")) {
            headOfDish();
            for (int j = 0; j < restaurant.dischesList.size(); j++) {

                if (restaurant.dischesList.get(j).dishTyp.equalsIgnoreCase(restaurant.dishTypeStarter)) {
                    System.out.println();
                    System.out.println(restaurant.dischesList.get(j));
                    System.out.println("Zutaten:");
                    for (int i = 0; i < restaurant.dischesList.get(j).dishIngridients.size(); i++) {
                        System.out.println(
                                restaurant.dischesList.get(j).dishIngridients.get(i)
                        );
                    }

                }

            }
            toOrder(restaurant);

        } else if (choice.equalsIgnoreCase("HS")) {
            headOfDish();
            for (int j = 0; j < restaurant.dischesList.size(); j++) {

                if (restaurant.dischesList.get(j).dishTyp.equalsIgnoreCase(restaurant.dishTypeMain)) {
                    System.out.println();
                    System.out.println(restaurant.dischesList.get(j));
                    System.out.println("Zutaten:");
                    for (int i = 0; i < restaurant.dischesList.get(j).dishIngridients.size(); i++) {
                        System.out.println(
                                restaurant.dischesList.get(j).dishIngridients.get(i)
                        );
                    }

                }

            }
            toOrder(restaurant);
        } else if (choice.equalsIgnoreCase("all")) {
            headOfDish();
            for (int j = 0; j < restaurant.dischesList.size(); j++) {

                System.out.println();
                System.out.println(restaurant.dischesList.get(j));
                System.out.println("Zutaten:");
                for (int i = 0; i < restaurant.dischesList.get(j).dishIngridients.size(); i++) {
                    System.out.println(
                            restaurant.dischesList.get(j).dishIngridients.get(i)
                    );
                }
            }
            toOrder(restaurant);
        } else {
            whichMenu(restaurant);
        }


    }

    public void toOrder(Restaurant restaurant) {

        System.out.println("Was möchtest du bestellen? ID bitte eingeben");
        this.dishID = scanner.nextInt();
        createOrderDish(restaurant);
        System.out.println("Richtige Auswahl? (J) JA / (N) NEIN ");
        String bug = scanner.nextLine();
        String tempSTring = scanner.nextLine();
        if (tempSTring.equalsIgnoreCase("n")) {
            this.dishID = 0;
            toOrder(restaurant);
        } else if (tempSTring.equalsIgnoreCase("j")) {
            System.out.println("Möchtest du was verändern? (J) JA / (N) NEIN \n Jede zusätzliche Zutat € 0,75");
            String tempString = scanner.nextLine();
            if (tempString.equalsIgnoreCase("j")) {
                changeIngridients(restaurant);
               wantMore(restaurant);

            } else if (tempString.equalsIgnoreCase("n")) {
                printOrderDish();
                    wantMore(restaurant);

            }
        }


    }

    public void wantMore(Restaurant restaurant) {
        if (this.bill >= this.freeDelivery) {
            System.out.println("Ihr momentaner Bestellwert: " + this.bill+"\nWir Liefern Gratis !");
        } else {
            System.out.println("Ab einem Bestellwert von € 15 liefern wir Gratis\nIhr Momentaner Bestellwert: " + this.bill);
            System.out.println("Ihre Lieferkosten betragen:");

            for (int i = 0; i < deliveryPlaces.size(); i++) {
                if (deliveryPlaces.get(i).place.equalsIgnoreCase(user.place)) {
                    System.out.println(deliveryPlaces.get(i));
                    this.userDeliveryPrice = deliveryPlaces.get(i).price;
                    this.userDeliveryID = deliveryPlaces.get(i).id;
                    System.out.println("");
                }


            }
        }

        System.out.println("Wollen Sie noch eine Bestellung aufgeben JA (J) / NEIN (N)");
        String tempString = scanner.next();
        if (tempString.equalsIgnoreCase("J")) {
            this.orderCounter++;
            whichMenu(restaurant);

        } else if (tempString.equalsIgnoreCase("N")) {
            if (this.bill >= this.freeDelivery) {
                this.isFreeDelivery = 1;
                goTPay(restaurant);
            } else { this.bill += this.userDeliveryPrice;
                    goTPay(restaurant);

            }

        }
    }

    public void goTPay (Restaurant restaurant) {
        System.out.println("Ihre Bestellung zur 'adresse' " + user.place);
       orderDishes.forEach(dishes -> {
           System.out.println(dishes.name + " - " + dishes.price);
       });
        System.out.println("Ihre Rechnung: " + this.bill);
        insertOrder(restaurant);
    }

    public void createOrderDish(Restaurant restaurant) {

        for (int i = 0; i < restaurant.dischesList.size(); i++) {
            if (restaurant.dischesList.get(i).dishId == dishID) {
                orderDish = new Dishes(restaurant.dischesList.get(i).name,
                        restaurant.dischesList.get(i).dishId,
                        restaurant.dischesList.get(i).dishTyp,
                        restaurant.dischesList.get(i).price);
                orderDishes.add(orderDish);
                for (int j = 0; j < restaurant.dischesList.get(i).dishIngridients.size(); j++) {
                    orderDishes.get(this.orderCounter).dishIngridients.add(restaurant.dischesList.get(i).dishIngridients.get(j));
                }
                System.out.println(orderDishes.get(orderCounter));
                System.out.println("Zutaten");
                orderDish.dishIngridients.forEach(ingridients -> {
                    System.out.println(ingridients.id + " - " + ingridients.name);
                });
            }
        }

    }

    public void changeIngridients(Restaurant restaurant) {
        System.out.println("Wollen Sie hinzufügen (1) oder Zutat löschen (2) ?");
        String tempString = scanner.nextLine();
        if (tempString.equalsIgnoreCase("1")) {
            int index = this.orderDishes.indexOf(orderDish);
           this.orderDishes.get(index).HasAddIng = 1;
            System.out.println("Verfügbare Zutaten");
            System.out.println("ID - Zutat");
            for (int i = 0; i < restaurant.ingridientsList.size(); i++) {
                System.out.println(i + " - " + restaurant.ingridientsList.get(i).name);
            }
            System.out.println("ID für Eingabe bitte weiterverwenden");
            System.out.println("Welche Zutat wollen Sie hinzufügen?");
            int tempInt = scanner.nextInt();
            orderDishes.get(this.orderCounter).dishIngridients.add(restaurant.ingridientsList.get(tempInt));
            this.addedIng.add(restaurant.ingridientsList.get(tempInt));
            orderDish.price += this.plusIngridient;
            printOrderDish();


        } else if (tempString.equalsIgnoreCase("2")) {
            int index = this.orderDishes.indexOf(orderDish);
            this.orderDishes.get(index).HasDeleteING = 1;
            System.out.println("Welche Zutat wollen Sie weglassen?");
            for (int i = 0; i < orderDish.dishIngridients.size(); i++) {
                System.out.println(i + " - " + orderDishes.get(this.orderCounter).dishIngridients.get(i).name);
            }

            int temInt = scanner.nextInt();
            orderDish.dishIngridients.remove(temInt);
            this.removeIng.add(restaurant.ingridientsList.get(temInt));
            printOrderDish();
        }

    }

    public void insertUserIngWish (Restaurant restaurant) {


        try {

            conn = DriverManager.getConnection(url);



            Statement stmt = null;
            String query = "SELECT order_detail.id FROM `order_detail` ORDER by order_detail.id DESC LIMIT 1";
            try {

                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    int order_detail_id = rs.getInt("id");
                    this.orderDetailID = order_detail_id;
                }
                for (int i = 0; i < orderDishes.size() ; i++) {
                    if ( orderDishes.get(i).HasDeleteING > 0 ){

                        for (int j = 0; j < removeIng.size() ; j++) {
                            String sql = " INSERT INTO `order_detail_ingriedients`" +
                                    "( `order_detail_id`, `ing_order_id`, `removed_ing`, `added_ing`) " +
                                    "VALUES ('" + this.orderDetailID + "' , '" + this.removeIng.get(j).id + "' , '" + orderDishes.get(j).HasDeleteING + "' , '" + orderDishes.get(j).HasAddIng +"' )";
                            stmt.executeUpdate(sql);
                        }


                    } else  if ( orderDishes.get(i).HasAddIng > 0 ) {
                        for (int k = 0; k < addedIng.size() ; k++) {
                            String sql = " INSERT INTO `order_detail_ingriedients`" +
                                    "( `order_detail_id`, `ing_order_id`, `removed_ing`, `added_ing`) " +
                                    "VALUES ('" + this.orderDetailID + "' , '" + this.addedIng.get(k).id + "' , '" + orderDishes.get(k).HasDeleteING + "' , '" + orderDishes.get(k).HasAddIng +"' )";
                            stmt.executeUpdate(sql);
                        }
                    }

                }




            } catch (SQLException e) {
                throw new Error("Problem " , e);
            }finally {
                if (stmt != null) {
                    stmt.close();
                }
            }

        } catch (SQLException e) {
            throw new Error("Problem " , e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch ( SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    public void insertOrder (Restaurant restaurant) {


        try {

            conn = DriverManager.getConnection(url);



            Statement stmt = null;

            try {

                stmt = conn.createStatement();


                String sql = "INSERT INTO `user_order`" +
                        "(`id`, `user_id`, `total_price`) " +
                        "VALUES" +
                        " ('" + this.newOrderNR + "' , '" + user.userID + "' , '" + this.bill + "')";
                stmt.executeUpdate(sql);
                for (int i = 0; i < orderDishes.size() ; i++) {
                    String sql2 = "INSERT INTO `order_detail`" +
                            "(`order_id`, `dish_order_id`, `price_order_dish_id`, `is_free_delivery`)" +
                            " VALUES " +
                            "('" +this.newOrderNR + "' , '" + orderDishes.get(i).dishId + "' , '" + orderDishes.get(i).dishId + "' , '" + this.isFreeDelivery + "' )";
                    stmt.executeUpdate(sql2);
                    insertUserIngWish(restaurant);
                }










            } catch (SQLException e) {
                throw new Error("Problem " , e);
            }finally {
                if (stmt != null) {
                    stmt.close();
                }
            }

        } catch (SQLException e) {
            throw new Error("Problem " , e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch ( SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    public void lastOrderNumber () {


        try {

            conn = DriverManager.getConnection(url);




            Statement stmt = null;
            String query = "SELECT * FROM `user_order` ORDER by user_order.id DESC LIMIT 1";
            try {

                stmt = conn.createStatement();


                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int lastOrderNumber = rs.getInt("id");
                    this.newOrderNR = lastOrderNumber;



                }

            } catch (SQLException e) {
                throw new Error("Problem " , e);
            }finally {
                if (stmt != null) {
                    stmt.close();
                }
            }

        } catch (SQLException e) {
            throw new Error("Problem " , e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch ( SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    public void printOrderDish() {
        System.out.println(orderDishes.get(orderCounter));
        System.out.println("Zutaten:");
        orderDishes.get(orderCounter).dishIngridients.forEach(ingridients -> {
            System.out.println(ingridients.id + " - " + ingridients.name);
        });
        this.bill += orderDishes.get(orderCounter).price;
        System.out.println("Ihr waren wert: " + this.bill);

    }

    public void headOfDish() {
        System.out.println("ID  -  Name  -  Typ - Preis in €");
    }

    public void printDeliverPlaces() {
        System.out.println("Wir liefern: ");
        System.out.println("ID - ORT - Preis in €");
        for (int i = 0; i < deliveryPlaces.size(); i++) {
            System.out.println(deliveryPlaces.get(i));

        }

    }

}
