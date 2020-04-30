package com.company.Database.repository;

import com.company.Database.models.HostUser;
import com.company.Database.models.User;
import com.company.view.TerminalOutput;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RestaurantRepo implements Repository<HostUser> {
    DB_Connector db_connector;
    TerminalOutput output;

    public RestaurantRepo(DB_Connector db_connector, TerminalOutput output) {
        this.db_connector = db_connector;
        this.output = output;
    }

    @Override
    public ArrayList<HostUser> findAll() {
        ArrayList<HostUser> users = new ArrayList<>();

        String sql =  "SELECT * FROM `wirt";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return null;
        }

        try {
            while (result.next()) {
                int ID = result.getInt("id");
                String name = result.getString("name");
                String email = result.getString("email");
                String password = result.getString("password");
                String place = result.getString("place");
                String type = result.getString("typ");

                users.add(new HostUser(ID , name, email , password ,place,type));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            output.outPutStringLanding(e.getMessage());
        } finally {
            db_connector.closeConnection();
            return  users;
        }
    }

    @Override
    public HostUser findOne(int id){
        HostUser resultUser = null;
        String sql =  "SELECT * FROM `wirt` WHERE id =" + id;
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return null;
        }
        try {
            while (result.next()) {
                int ID = result.getInt("id");
                String name = result.getString("name");
                String email = result.getString("email");
                String password = result.getString("password");
                String place = result.getString("place");
                String type = result.getString("typ");


                resultUser = new HostUser(ID , name, email , password ,place , type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            output.outPutStringLanding(e.getMessage());
        } finally {
            db_connector.closeConnection();
            return resultUser;
        }
    }

    @Override
    public void create(HostUser entity) {
        String sql = "INSERT INTO `wirt`( `name`, `email`, `password`, `place`, typ) VALUES ('" + entity.getName() + "' , '" + entity.getEmail() + "' , '" + entity.getPassword() + "' , '" +
                entity.getPlace() + "' , '" + entity.getType() + "')";
        db_connector.insert(sql);
    }

    public HostUser findWithEmail (String email) {
        HostUser resultUser = null;
        String sql =  "SELECT * FROM `wirt` WHERE email ='" + email + "'";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return null;
        }

        try {
            while (result.next()) {
                int ID = result.getInt("id");
                String name = result.getString("name");
                String email1 = result.getString("email");
                String password = result.getString("password");
                String place = result.getString("place");
                String type = result.getString("typ");

                resultUser = new HostUser(ID , name, email1 , password ,place,type);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            output.outPutStringLanding(e.getMessage());
        } finally {
            db_connector.closeConnection();
            return resultUser;
        }
    }

    public Integer lastUserId () {
        int newUserID = 0;
        String sql =  "SELECT id FROM `wirt` ORDER By user_id DESC LIMIT 1";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return null;
        }
        try {
            while (result.next()) {
                newUserID = result.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            output.outPutStringLanding(e.getMessage());
        } finally {
            db_connector.closeConnection();
            return newUserID;
        }
    }

    public boolean isFreeEmail (String email) {
        int count = 0;
        boolean freeEmail = false;
        String sql =  "SELECT COUNT(*) as anzahl FROM `wirt` WHERE email = '" + email + "'";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return false;
        }
        try {
            while (result.next()) {
                count = result.getInt("anzahl");
                if (count < 1) {
                    freeEmail = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            output.outPutStringLanding(e.getMessage());
        } finally {
            db_connector.closeConnection();
            return freeEmail;
        }
    }

    public Integer whichHost (int dishId) {
        int ID = 0;
        String sql =  "SELECT DISTINCT menu.wirt_id FROM `dish_ingridients`\n" +
                "INNER JOIN menu ON menu.dish_id = dish_ingridients.dish_id\n" +
                "WHERE dish_ingridients.dish_id = " + dishId;
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return null;
        }
        try {
            while (result.next()) {
                ID = result.getInt("wirt_id");

            }
        } catch (SQLException e) {
            e.printStackTrace();
            output.outPutStringLanding(e.getMessage());
        } finally {
            db_connector.closeConnection();
            return ID;
        }
    }

}
