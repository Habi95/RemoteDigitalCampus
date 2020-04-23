package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        Connection conn = null;

        try {

            String url ="jdbc:mysql://localhost:3306/lieferservice?user=root";
            conn = DriverManager.getConnection(url);

            Statement stmt = null;
            String query = " ";

            try {

                stmt = conn.createStatement();
                for (int row = 1; row < 2 ; row++) {
                    for (int colums = 1; colums <= 200 ; colums++) {
                        String sql = " INSERT INTO `cinemex_saal_plan`(`cine_id`, `movie_id`) VALUES (" + row + "," + colums +")";
                        stmt.executeUpdate(sql);
                        System.out.println("Insert complete");
                    }
                }



            } catch (SQLException e) {
                throw new Error("Problem" , e);
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

            }


        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}

