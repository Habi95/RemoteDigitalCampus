package com.company;

import com.mysql.cj.x.protobuf.Mysqlx;

import java.sql.*;

public class Main {

    public static void main(String[] args) {

        Connection conn = null;

        try {

            String url = "jdbc:mysql://localhost:3306/digital_notice?user=root";
            conn = DriverManager.getConnection(url);

            System.out.println("Connectet to DB");


            Statement stmt = null;
            String query = "select * from notice_book";
            try {

                 stmt = conn.createStatement();

                 //datensatz einlesen

                 String notice_Head = " es funkt";
                 String notice1 = "Das geht ja wunderrrbar";

                 String sql = "INSERT INTO notice_book "
                            + " (`notice_head`, `notice`)"
                            + " VALUES ( '" + notice_Head + "' , '" + notice1+"' )";

                 stmt.executeUpdate(sql);


                System.out.println("Insert complete");


                /*
                DB tabelle auslesen
                */

                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int numberOfPage = rs.getInt("number_of_pages");
                    String date = rs.getDate("date").toString();
                    String noticeHead = rs.getString("notice_head");
                    String notice = rs.getString("notice");

                    System.out.println(numberOfPage + ", " + date + ", " + noticeHead + "\n" + notice);
                }

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
