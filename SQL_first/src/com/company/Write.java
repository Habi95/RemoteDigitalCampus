package com.company;

import java.sql.*;
import java.util.Scanner;

public class Write {
    Scanner scanner = new Scanner(System.in);

    public void writeIn () {

        Connection conn = null;

        try {

            String url = "jdbc:mysql://localhost:3306/digital_notice?user=root";
            conn = DriverManager.getConnection(url);



            Statement stmt = null;
            String query = "select * from notice_book";
            try {

                stmt = conn.createStatement();

                //datensatz einlesen

                System.out.println("Titel eingeben");
                String notice_Head =  scanner.nextLine();
                System.out.println("Text eingeben");
                String notice1 = scanner.nextLine();

                String sql = "INSERT INTO notice_book "
                        + " (`notice_head`, `notice`)"
                        + " VALUES ( '" + notice_Head + "' , '" + notice1+"' )";

                stmt.executeUpdate(sql);






            } catch (SQLException e) {
                throw new Error("Problem " , e);
            }finally {
                if (stmt != null) {
                    stmt.close();
                }
            }

        } catch (SQLException e) {
            throw new Error("Problem " , e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch ( SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}
