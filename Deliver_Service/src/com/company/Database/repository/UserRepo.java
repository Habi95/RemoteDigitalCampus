package com.company.Database.repository;

import com.company.Database.models.User;
import com.company.view.TerminalOutput;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepo implements Repository<User> {
    private DB_Connector db_connector;
    private TerminalOutput output;

    public UserRepo (DB_Connector db_connector, TerminalOutput output) {
        this.db_connector = db_connector;
        this.output = output;
    }

    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> users = new ArrayList<>();

        String sql =  "SELECT * FROM `user";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return null;
        }

        try {
            while (result.next()) {
                int ID = result.getInt("user_id");
                String email = result.getString("email");
                String password = result.getString("password");
                String place = result.getString("place");


                users.add(new User(ID , email , password ,place));

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
    public User findOne(int id){
       User resultUser = null;
        String sql =  "SELECT * FROM `user` WHERE user_id =" + id;
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return null;
        }
        try {
            while (result.next()) {
                int ID = result.getInt("user_id");
                String email = result.getString("email");
                String password = result.getString("password");
                String place = result.getString("place");


                resultUser = new User(ID , email , password ,place);
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
    public void create(User entity) {
        String sql = "INSERT INTO `user`( `email`, `password`, `place`) VALUES ('" + entity.getEmail() + "' , '" + entity.getPassword() + "' , '" +
        entity.getPlace() + "')";
        db_connector.insert(sql);
    }

    public User findWithEmail (String email) {
        User resultUser = null;
        String sql =  "SELECT * FROM `user` WHERE user.email ='" + email + "'";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return null;
        }

        try {
            while (result.next()) {
                int ID = result.getInt("user_id");
                String email1 = result.getString("email");
                String password = result.getString("password");
                String place = result.getString("place");


                resultUser = new User(ID , email1 , password ,place);

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
        String sql =  "SELECT user_id FROM `user` ORDER By user_id DESC LIMIT 1";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return null;
        }
        try {
            while (result.next()) {
                newUserID = result.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            output.outPutStringLanding(e.getMessage());
        } finally {
            db_connector.closeConnection();
            return newUserID;
        }
    }

    public Integer freeEmail (String email) {
        int freeEmail = 0;
        String sql =  "SELECT COUNT(*) as anzahl FROM `user` WHERE email = '" + email + "'";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return null;
        }
        try {
            while (result.next()) freeEmail = result.getInt("anzahl");
        } catch (SQLException e) {
            e.printStackTrace();
            output.outPutStringLanding(e.getMessage());
        } finally {
            db_connector.closeConnection();
            return freeEmail;
        }
    }
}
