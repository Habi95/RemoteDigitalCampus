//package com.company.Database.repository;
//
//import com.company.Database.models.Restaurant;
//import com.company.Database.models.User;
//import com.company.view.TerminalOutput;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class RestaurantRepo implements Repository<Restaurant> {
//    private DB_Connector db_connector;
//    TerminalOutput output;
//
//    public RestaurantRepo(DB_Connector db_connector, TerminalOutput output) {
//        this.db_connector = db_connector;
//        this.output = output;
//    }
//
//    @Override
//    public ArrayList<Restaurant> findAll() {
//        ArrayList<Restaurant> restaurant = new ArrayList<>();
//
//        String sql =  "SELECT id , name, email, place FROM `wirt`";
//        ResultSet result = db_connector.fetchData(sql);
//        if (result == null) {
//            output.outPutStringLanding("something go wrong");
//            return null;
//        }
//
//        try {
//            while (result.next()) {
//                int ID = result.getInt("user_id");
//                String email = result.getString("email");
//                String password = result.getString("password");
//                String place = result.getString("place");
//
//
//                restaurant.add(new Restaurant(ID , email , password ,place));
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            output.outPutStringLanding(e.getMessage());
//        } finally {
//            db_connector.closeConnection();
//            return  restaurant;
//        }
//    }
//
//    @Override
//    public Restaurant findOne(int id) {
//        return null;
//    }
//
//    @Override
//    public void create(Restaurant entity) {
//
//    }
//}
