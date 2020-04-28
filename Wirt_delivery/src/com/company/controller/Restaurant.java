package com.company.controller;

import com.company.DataBaseConnector;
import com.company.Database.models.Dish;
import com.company.Database.models.Dishes;
import com.company.Database.models.Ingridients;
import com.company.Database.repository.DB_Connector;
import com.company.Database.repository.DishRepo;
import com.company.Database.repository.IngridientsRepo;
import com.company.view.TerminalOutput;

import java.sql.*;
import java.util.Scanner;

public class Restaurant {


    //zutaten und Ingridients werden erst im DeliveryProgramm als objekt dargestellt hab ich hier noch nicht gemacht ...

    Connection conn = null;
    String url = "jdbc:mysql://localhost:3306/lieferservice?user=root";
    Scanner scanner = new Scanner(System.in);
    DB_Connector db_connector;
    TerminalOutput output;
    DishRepo dishRepo;
    IngridientsRepo ingridientsRepo;
    int wirt = 1;
    int vorspeise = 1;
    String hauptspeise = "2";
    int lastDish;

    public Restaurant(DB_Connector db_connector, TerminalOutput output, DishRepo dishRepo, IngridientsRepo ingridientsRepo) {
        this.db_connector = db_connector;
        this.output = output;
        this.dishRepo = dishRepo;
        this.ingridientsRepo = ingridientsRepo;
    }

    public void writeDish() {

        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = null;

            try {
                stmt = conn.createStatement();

                output.outPutStringLanding("Wollen Sie eine Hauptspeise (HS) oder eine Vorspeise (VS) erstellen?");
                String temp1 = scanner.nextLine();
                if (temp1.equalsIgnoreCase("VS")) {
                   output.outPutString("Wie heißt die Vorspeise?");
                    String temp = scanner.nextLine();
                    dishRepo.create(new Dishes(temp, vorspeise ));
//                    String sql = "INSERT INTO `dishes`( `dish_name`, `type`)" +
//                            " VALUES " +
//                            "('" + temp + "'," + vorspeise + ")";
//
//                    stmt.executeUpdate(sql);
                    addIngridient();


                }
                if (temp1.equalsIgnoreCase("HS")) {
                    System.out.println("Wie heißt die Hauptspeise?");
                    String temp = scanner.nextLine();
                    dishRepo.create(new Dishes(temp,hauptspeise));
//                    String sql = "INSERT INTO `dishes`( `dish_name`, `type`) " +
//                            "VALUES " +
//                            "('" + temp + "'," + hauptspeise + ")";
//
//                    stmt.executeUpdate(sql);
                    addIngridient();


                } else {
                    System.out.println("Diese Eingabe war nicht korrekt");
                    writeDish();
                }


            } catch (SQLException e) {
                throw new Error("problem ", e);
            } finally {
                try {
                    if (stmt != null)
                        stmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }


        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {
            try {
                if (conn != null)
                    conn.close();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void addIngridient () {

        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = null;

            try {
                stmt = conn.createStatement();
                boolean run = true;
                while (run) {
                    lastDishID();
                    ingOfDish();
                    System.out.println();
                    ingriedientsList();

                    System.out.println("Wollen Sie noch eine Zutata hinzufügen? Ja / Nein");
                    String temp2 = scanner.nextLine();
                    String temp1 = scanner.nextLine();
                    if (temp1.equalsIgnoreCase("JA")) {
                        System.out.println("bitte in Zutaten ID eingeben");
                        int tempIng = scanner.nextByte();

                        String sql = " INSERT INTO `dish_ingridients`\n" +
                                "(`dish_id`, `ing_id`)\n" +
                                "VALUES \n" +
                                "(" + this.lastDish + "," + tempIng + ")";

                        stmt.executeUpdate(sql);



                    } else if (temp1.equalsIgnoreCase("Nein")) {
                        run = false;
                        System.out.println("Danke für die Eingabe");
                        addToMenu();
                        addPrice();


                    }
//                    else {
//                        System.out.println("Diese eingabe kenne ich nicht");
//                        addIngridient();
//                    }

                }


            } catch (SQLException e) {
                throw new Error("problem ", e);
            } finally {
                try {
                    if (stmt != null)
                        stmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }


        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {
            try {
                if (conn != null)
                    conn.close();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void lastDishID () {

        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = null;
            String query = "SELECT dishes.id,dish_name,  typ_of_dish.name FROM `dishes` \n" +
                    "INNER Join typ_of_dish ON typ_of_dish.id = dishes.type\n" +
                    "ORDER BY id DESC LIMIT 1";
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                System.out.println("Letzt hinzugefügtes Gericht");
                System.out.println("ID  \t--\t\t Name \t\t--\t\t Typ");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    this.lastDish = id;
                    String name = rs.getString("dish_name");
                    String typ = rs.getString("name");

                    System.out.println(id + " - " + name + " - " + typ);


                }


            } catch (SQLException e) {
                throw new Error("problem ", e);
            } finally {
                try {
                    if (stmt != null)
                        stmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }


        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {
            try {
                if (conn != null)
                    conn.close();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void ingOfDish () {

        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = null;
            String query = "SELECT ingridients.ing_name FROM `dish_ingridients`\n" +
                    "Inner JOIN ingridients ON ingridients.id = dish_ingridients.ing_id\n" +
                    "WHERE dish_id = " + this.lastDish;
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                System.out.println("Zutaten: ");
                while (rs.next()) {

                    String ingName = rs.getString("ing_name");


                    System.out.println( ingName );

                }


            } catch (SQLException e) {
                throw new Error("problem ", e);
            } finally {
                try {
                    if (stmt != null)
                        stmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }


        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {
            try {
                if (conn != null)
                    conn.close();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void ingriedientsList() {

        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = null;
            String query = "SELECT * FROM `ingridients`";
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                System.out.println("Ihre Zutaten Liste");
                System.out.println("ID  -- Name");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String ing = rs.getString("ing_name");

                    System.out.println(id + " - " + ing);
                }


            } catch (SQLException e) {
                throw new Error("problem ", e);
            } finally {
                try {
                    if (stmt != null)
                        stmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }


        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {
            try {
                if (conn != null)
                    conn.close();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void addToMenu () {

        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = null;

            try {
                stmt = conn.createStatement();

                String sql = "INSERT INTO `menu`(`wirt_id`, `dish_id`)" +
                        " VALUES " +
                        "(" + this.wirt + "," + this.lastDish +")";

                stmt.executeUpdate(sql);


            } catch (SQLException e) {
                throw new Error("problem ", e);
            } finally {
                try {
                    if (stmt != null)
                        stmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }


        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {
            try {
                if (conn != null)
                    conn.close();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    public void addPrice () {

        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = null;

            try {
                stmt = conn.createStatement();
                System.out.println("Preis eingeben: Info , als kommer");
                double price = scanner.nextDouble();
                String sql ="INSERT INTO `price`(`dish_id`,`price`)" +
                        " VALUES " +
                        "(" + this.lastDish + "," + price +")";

                stmt.executeUpdate(sql);

                System.out.println("Danke für die Eingabe");

                System.exit(0);


            } catch (SQLException e) {
                throw new Error("problem ", e);
            } finally {
                try {
                    if (stmt != null)
                        stmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }


        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {
            try {
                if (conn != null)
                    conn.close();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }



}
