package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;


public class Evaluation    {
    String url = "jdbc:mysql://localhost:3306/lieferservice?user=root";
    DB_Connector db_connector = new DB_Connector(url);
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Dish> mostOfOrderdDish = new ArrayList<>();
    ArrayList<Dish> dishes = new ArrayList<>();
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

    public void howMuchOrder ()
    {
        String sql = "SELECT count(user_order.id) FROM user_order";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            System.out.println("something go wrong");
            return;
        }

        try {
            while (result.next()) {
                int x = result.getInt("count(user_order.id)");
                System.out.println("Bis jetzt wurden gesamt " + x + " Bestellungen aufgegeben");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            db_connector.closeConnection();
        }
    }

    public void perCustomOrder ()
    {
        String sql = "SELECT count(order_detail.order_id), user.email FROM `order_detail`\n" +
                "INNER JOIN user_order ON user_order.id = order_detail.order_id\n" +
                "INNER JOIN user ON user.user_id = user_order.user_id\n" +
                "GROUP BY user.email";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            System.out.println("something go wrong");
            return;
        }

        try {
            while (result.next()) {
                int x = result.getInt("count(order_detail.order_id)");
                String email = result.getString("email");
                User user = new User(email,x);
                users.add(user);
                System.out.println("Der Kunde mit der Email: " + email + " hat bis jetzt " + x + " mal bestellt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            db_connector.closeConnection();
        }
    }

    public void perTown ()
    {
        String sql = "SELECT count(order_detail.order_id),user.place FROM `order_detail`\n" +
                "INNER JOIN user_order ON user_order.id = order_detail.order_id\n" +
                "INNER JOIN user ON user.user_id = user_order.user_id\n" +
                "GROUP BY user.place";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            System.out.println("something go wrong");
            return;
        }
        try {
            while (result.next()) {
                int x = result.getInt("count(order_detail.order_id)");
                String place = result.getString("place");

                System.out.println("Es wurde in " + place + " gesamt: " + x + " mal bestellt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            db_connector.closeConnection();
        }
    }

    public void mostOfDish ()
    {
        String sql = "SELECT COUNT(order_detail.dish_order_id) as 'x'  , dishes.dish_name  FROM `order_detail`\n" +
                "INNER JOIN dishes ON dishes.id = order_detail.dish_order_id\n" +
                "GROUP BY dish_order_id  \n" +
                "ORDER BY `x`  DESC\n" +
                "LIMIT 1";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            System.out.println("something go wrong");
            return;
        }
        try {
            while (result.next()) {
                int x = result.getInt("x");
                String dish = result.getString("dish_name");
                Dish mostDish = new Dish(dish,x);
                mostOfOrderdDish.add(mostDish);
                System.out.println("Das Gericht: " + dish + "wurde am meisten Bestellt.\nEs wurde " + x + " mal Bestellt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            db_connector.closeConnection();
        }
    }

    public void dishOrderd ()
    {
        String sql = "SELECT COUNT(order_detail.dish_order_id) as 'x'  , dishes.dish_name  FROM `order_detail`\n" +
                "INNER JOIN dishes ON dishes.id = order_detail.dish_order_id\n" +
                "GROUP BY dish_order_id  \n" +
                "ORDER BY `x`  DESC";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            System.out.println("something go wrong");
            return;
        }
        try {
            System.out.println("Bestellte Gerichte in absteigender Reihenfolge:\nWieOft  -  Name ");
            while (result.next()) {
                int x = result.getInt("x");
                String dish = result.getString("dish_name");
                Dish dishes1 = new Dish(dish,x);
                dishes.add(dishes1);
                System.out.println( x + "  -  " + dish);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            db_connector.closeConnection();
        }
    }

    public void writeList ()
    {
        String sql = "SELECT `id`, `user_id`, `total_price`, `time` FROM `user_order`";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            System.out.println("something go wrong");
            return;
        }
        try {
            while (result.next()) {
                int id = result.getInt("id");
                int user_id = result.getInt("user_id");
                double bill = result.getDouble("total_price");
                Time time = result.getTime("time");
                {

                    try {
                        billWriter.write("\n" + id + " ; " + user_id + " ; " + bill + "€ ; " + time);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                billWriter.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            db_connector.closeConnection();
        }
    }

    public void writeBill ()
    {
        String sql = "SELECT `id`, `user_id`, `total_price`, `time` FROM `user_order`";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            System.out.println("something go wrong");
            return;
        }
        try {
            while (result.next()) {
                int id = result.getInt("id");
                int user_id = result.getInt("user_id");
                double bill = result.getDouble("total_price");
                Time time = result.getTime("time");
                {

                    try {
                        billWriter.write("\n" + id + " ; " + user_id + " ; " + bill + "€ ; " + time);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                billWriter.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            db_connector.closeConnection();
        }
    }

}



