package com.company.Database.repository;

import com.company.Database.models.Ingridients;
import com.company.Database.models.IngridientsEvaluation;
import com.company.view.TerminalOutput;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngridientsRepo implements Repository<Ingridients> {

    private DB_Connector db_connector;
    private TerminalOutput output;

    public IngridientsRepo(DB_Connector db_connector, TerminalOutput output) {
        this.db_connector = db_connector;
        this.output = output;
    }

    @Override
    public ArrayList<Ingridients> findAll() {
        ArrayList<Ingridients> ingridients = new ArrayList<>();

        String sql =  "SELECT * FROM `ingridients";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return null;
        }

        try {
            while (result.next()) {
                int ID = result.getInt("id");
                String name = result.getString("ing_name");



                ingridients.add(new Ingridients(ID , name));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            output.outPutStringLanding(e.getMessage());
        } finally {
            db_connector.closeConnection();
            return  ingridients;
        }
    }

    @Override
    public Ingridients findOne(int id) {
        Ingridients ingridients = null;
        String sql =  "SELECT * FROM `ingridients` WHERE id =" + id;
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return null;
        }

        try {
            while (result.next()) {
                int ID = result.getInt("id");
                String name = result.getString("ing_name");



                ingridients = new Ingridients(ID , name);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            output.outPutStringLanding(e.getMessage());
        } finally {
            db_connector.closeConnection();
            return ingridients;
        }
    }

    public ArrayList<Ingridients> findDishIng(int id) {
        ArrayList<Ingridients> ingridients = new ArrayList<>();
        String sql =  "SELECT ingridients.id, ingridients.ing_name FROM `dish_ingridients` \n" +
                "INNER JOIN ingridients On ingridients.id = dish_ingridients.ing_id\n" +
                "WHERE dish_id =" + id;
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return null;
        }

        try {
            while (result.next()) {
                int ID = result.getInt("id");
                String name = result.getString("ing_name");

                ingridients.add( new Ingridients(ID , name));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            output.outPutStringLanding(e.getMessage());
        } finally {
            db_connector.closeConnection();
            return ingridients;
        }
    }

    @Override
    public void create(Ingridients entity) {
        String sql = " INSERT INTO `dish_ingridients`(`dish_id`, `ing_id`) VALUES (" + entity.getId() + " , " + entity.getName() + ")";
        db_connector.insert(sql);
    }
    
    public ArrayList<IngridientsEvaluation> ingridientsEvaluations () {
        ArrayList<IngridientsEvaluation> ingList = new ArrayList<>();
        String sql = "select ingridients.ing_name, count(*) + (select count(*) from order_detail_ingriedients\n" +
                "INNER JOIN ingridients ON ingridients.id = order_detail_ingriedients.ing_order_id\n" +
                "WHERE order_detail_ingriedients.added_ing = 1\n" +
                "AND ingridients.ing_name = ing_name) - (select count(*) from order_detail_ingriedients\n" +
                "INNER JOIN ingridients ON ingridients.id = order_detail_ingriedients.ing_order_id\n" +
                "WHERE order_detail_ingriedients.removed_ing = 1\n" +
                "AND ingridients.ing_name = ing_name) as 'count'  from order_detail\n" +
                "INNER JOIN dish_ingridients ON dish_ingridients.dish_id = order_detail.dish_order_id\n" +
                "INNER JOIN ingridients ON ingridients.id = dish_ingridients.ing_id\n" +
                "group by ingridients.ing_name";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return null;
        }

        try {
            while (result.next()) {
                String ingName = result.getString("ing_name");
                int x = result.getInt("count");
                ingList.add(new IngridientsEvaluation(ingName,x));




            }
        } catch (SQLException e) {
            e.printStackTrace();
            output.outPutStringLanding(e.getMessage());
        } finally {
            db_connector.closeConnection();
            return ingList;
        }
    }


}
