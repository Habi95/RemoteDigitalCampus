package com.company.Database.repository;

import com.company.Database.models.Dishes;
import com.company.view.TerminalOutput;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DishRepo {

    private DB_Connector db_connector;
    private TerminalOutput output;

    public DishRepo (DB_Connector db_connector,   TerminalOutput output ) {
        this.db_connector = db_connector;
        this.output = output;

    }

    public ArrayList<Dishes> findAll(int hostID) {
        ArrayList<Dishes> dishes = new ArrayList<>();

            String sql =  "SELECT menu.id AS menu , dishes.dish_name as name, typ_of_dish.name as art,  price.price as price_in_€ , dish_ingridients.dish_id FROM `price` \n" +
                    "INNER Join dish_ingridients ON price.dish_id = dish_ingridients.dish_id\n" +
                    "INNER Join dishes On dishes.id = dish_ingridients.dish_id\n" +
                    "INNER Join typ_of_dish On typ_of_dish.id = dishes.type\n" +
                    "INNER JOIN menu ON menu.dish_id = dish_ingridients.dish_id\n" +
                    "WHERE menu.wirt_id =" + hostID +
                    " GROUP BY menu.id ORDER BY menu.id ASC\n";
            ResultSet result = db_connector.fetchData(sql);
            if (result == null) {
                output.outPutStringLanding("something go wrong");
                return null;
            }

            try {
                while (result.next()) {
                    int menuID = result.getInt("menu");
                    String dishName = result.getString("name");
                    String dishTyp = result.getString("art");
                    double price = result.getDouble("price_in_€");


                 dishes.add(new Dishes(dishName,menuID,dishTyp,price));

                }
            } catch (SQLException e) {
                e.printStackTrace();
                output.outPutStringLanding(e.getMessage());
            } finally {
                db_connector.closeConnection();
                return dishes;
            }
    }


    public Dishes findOne(int id) {
        Dishes resultDish = null;
        String sql =  "SELECT DISTINCT menu.id AS menu , dishes.dish_name as name, typ_of_dish.name as art,  price.price as price_in_€ , dish_ingridients.dish_id FROM `price` \n" +
                "                    INNER Join dish_ingridients ON price.dish_id = dish_ingridients.dish_id\n" +
                "                    INNER Join dishes On dishes.id = dish_ingridients.dish_id\n" +
                "                    INNER Join typ_of_dish On typ_of_dish.id = dishes.type\n" +
                "                    INNER JOIN menu ON menu.dish_id = dish_ingridients.dish_id\n" +
                "                    WHERE menu.id =" + id;
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return null;
        }

        try {
            while (result.next()) {
                int menuID = result.getInt("menu");
                String dishName = result.getString("name");
                String dishTyp = result.getString("art");
                double price = result.getDouble("price_in_€");


                resultDish = new Dishes(dishName,menuID,dishTyp,price);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            output.outPutStringLanding(e.getMessage());
        } finally {
            db_connector.closeConnection();
            return resultDish;
        }
    }


    public void create(Dishes entity) {

    }

}
