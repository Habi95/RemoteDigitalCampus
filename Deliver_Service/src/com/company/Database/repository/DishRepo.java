package com.company.Database.repository;

import com.company.Database.models.Dishes;
import com.company.Database.models.Ingridients;
import com.company.view.TerminalOutput;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DishRepo implements Repository<Dishes> {

    private DB_Connector db_connector;
    private TerminalOutput output;

    public DishRepo (DB_Connector db_connector,   TerminalOutput output ) {
        this.db_connector = db_connector;
        this.output = output;

    }
    @Override
    public ArrayList<Dishes> findAll() {
        ArrayList<Dishes> dishes = new ArrayList<>();

            String sql =  "SELECT menu.id AS menu , dishes.dish_name as name, typ_of_dish.name as art,  price.price as price_in_€ , dish_ingridients.dish_id FROM `price` \n" +
                    "INNER Join dish_ingridients ON price.dish_id = dish_ingridients.dish_id\n" +
                    "INNER Join dishes On dishes.id = dish_ingridients.dish_id\n" +
                    "INNER Join typ_of_dish On typ_of_dish.id = dishes.type\n" +
                    "INNER JOIN menu ON menu.dish_id = dish_ingridients.dish_id\n" +
                    "GROUP BY menu.id ORDER BY menu.id ASC\n";
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

    @Override
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

    @Override
    public void create(Dishes entity) {

    }
    public  void dishesReceiveIng(Dishes dishes)
    {
        String sql = "SELECT ingridients.id, ingridients.ing_name FROM `dish_ingridients` \n" +
                "INNER JOIN ingridients On ingridients.id = dish_ingridients.ing_id\n" +
                "WHERE dish_id =" + dishes.getDishId();;
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            System.out.println("something go wrong");
            return;
        }

        try {
            Ingridients ingridients;
            while (result.next()) {
                int id = result.getInt("id");
                String ing_name = result.getString("ing_name");

                ingridients = new Ingridients(id, ing_name);
                dishes.dishIngridients.add(ingridients);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            db_connector.closeConnection();
        }
    }
}
