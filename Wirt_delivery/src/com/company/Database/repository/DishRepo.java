package com.company.Database.repository;

import com.company.Database.models.DishEvaluation;
import com.company.Database.models.Dishes;
import com.company.Database.models.Menu;
import com.company.view.TerminalOutput;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DishRepo implements Repository<Dishes> {

    private DB_Connector db_connector;
    private TerminalOutput output;

    public DishRepo(DB_Connector db_connector, TerminalOutput output) {
        this.db_connector = db_connector;
        this.output = output;

    }

    @Override
    public ArrayList<Dishes> findAll() {
        ArrayList<Dishes> dishes = new ArrayList<>();
        String sql = "SELECT menu.id AS menu , dishes.dish_name as name, typ_of_dish.name as art,  price.price as price_in_€ , dish_ingridients.dish_id FROM `price` \n" +
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


                dishes.add(new Dishes(dishName, menuID, dishTyp, price));

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
        String sql = "SELECT * FROM `dishes` WHERE id =" + id;
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return null;
        }

        try {
            while (result.next()) {
                int menuID = result.getInt("id");
                String dishName = result.getString("dish_name");
                String dishTyp = result.getString("type");


                resultDish = new Dishes(dishName, menuID, dishTyp, 0);

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
        String sql = "INSERT INTO `dishes`( `dish_name`, `type`) VALUES ('" + entity.getName() + "' , '" + entity.getDishTyp() + "')";
        db_connector.insert(sql);
    }

    public Integer lastDishID() {
        int dishID = 0;
        String sql = "SELECT id FROM `dishes` GROUP BY id DESC LIMIT 1";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return null;
        }

        try {
            while (result.next()) {
                dishID = result.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            output.outPutStringLanding(e.getMessage());
        } finally {
            db_connector.closeConnection();
            return dishID;
        }
    }

    public Dishes lastAdded() {
        Dishes dishes = null;
        String sql = "SELECT dishes.id,dish_name,  typ_of_dish.name FROM `dishes`\n" +
                "INNER Join typ_of_dish ON typ_of_dish.id = dishes.type\n" +
                "ORDER BY id DESC LIMIT 1";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return null;
        }

        try {
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("dish_name");
                String typ = result.getString("name");

                dishes = new Dishes(name, id, typ, 0);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            output.outPutStringLanding(e.getMessage());
        } finally {
            db_connector.closeConnection();
            return dishes;
        }
    }

    public void addToMenu(Menu entity) {
        String sql = "INSERT INTO `menu`(`wirt_id`, `dish_id`)" +
                " VALUES " +
                "(" + entity.getHostID() + "," + entity.getDishID() + ")";
        db_connector.insert(sql);
    }

    public void addPrice(Dishes entity) {
        String sql = "INSERT INTO `price`(`dish_id`,`price`)" +
                "  VALUES " +
                "(" + entity.getDishId() + " , " + entity.getPrice() + ")";
        db_connector.insert(sql);
    }

    public DishEvaluation mostOf ()  {
        DishEvaluation dishes = null;
        String sql = "SELECT COUNT(order_detail.dish_order_id) as 'x'  , dishes.dish_name  FROM `order_detail`\n" +
                "INNER JOIN dishes ON dishes.id = order_detail.dish_order_id\n" +
                "GROUP BY dish_order_id\n" +
                "ORDER BY `x`  DESC\n" +
                "LIMIT 1";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return null;
        }

        try {
            while (result.next()) {
                int x = result.getInt("x");
                String dish = result.getString("dish_name");

                dishes = new DishEvaluation(dish,x);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            output.outPutStringLanding(e.getMessage());
        } finally {
            db_connector.closeConnection();
            return dishes;
        }
    }

    public ArrayList<DishEvaluation> dishGroupByPopular () {
        ArrayList<DishEvaluation> dishes = new ArrayList<>();
        String sql = "SELECT COUNT(order_detail.dish_order_id) as 'x'  , dishes.dish_name  FROM `order_detail`\n" +
                "INNER JOIN dishes ON dishes.id = order_detail.dish_order_id\n" +
                "GROUP BY dish_order_id\n" +
                "ORDER BY `x`  DESC";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return null;
        }

        try {
            while (result.next()) {
                int x = result.getInt("x");
                String dish = result.getString("dish_name");
                dishes.add(new DishEvaluation(dish,x));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            output.outPutStringLanding(e.getMessage());
        } finally {
            db_connector.closeConnection();
            return dishes;
        }
    }


}
