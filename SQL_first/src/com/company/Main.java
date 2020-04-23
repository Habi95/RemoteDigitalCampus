package com.company;

import com.mysql.cj.x.protobuf.Mysqlx;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean isRun = true;
        Scanner scanner = new Scanner(System.in);
        Write write = new Write();
        Read read = new Read();

        while (isRun) {
            System.out.println("Willkommen zu Ihrem persönlichen Notizbuch\nWollen sie Ihr Notizbuch lesen (1) oder schreiben (2)  oder beenden (3)");
            String temp = scanner.nextLine();
            if (temp.equalsIgnoreCase("1")) {
                read.readIn();
                System.out.println();
            } else if (temp.equalsIgnoreCase("2")) {
                write.writeIn();
                System.out.println();
            } else if (temp.equalsIgnoreCase("3")) {
                System.out.println("Danke für Ihr Vertrauen");
                isRun = false;
            }

        }


//        Connection conn = null;
//
//        try {
//
//            String url = "jdbc:mysql://localhost:3306/digital_notice?user=root";
//            conn = DriverManager.getConnection(url);
//
//            System.out.println("Connectet to DB");
//
//
//            Statement stmt = null;
//            String query = "select * from notice_book";
//            try {
//
//                 stmt = conn.createStatement();
//
//                 //datensatz einlesen
//
//                 String notice_Head = " es funkt";
//                 String notice1 = "Das geht ja wunderrrbar";
//
//                 String sql = "INSERT INTO notice_book "
//                            + " (`notice_head`, `notice`)"
//                            + " VALUES ( '" + notice_Head + "' , '" + notice1+"' )";
//
//                 stmt.executeUpdate(sql);
//
//
//                System.out.println("Insert complete");
//
//
//                /*
//                DB tabelle auslesen
//                */
//
//                ResultSet rs = stmt.executeQuery(query);
//                while (rs.next()) {
//                    int numberOfPage = rs.getInt("number_of_pages");
//                    String date = rs.getDate("date").toString();
//                    String noticeHead = rs.getString("notice_head");
//                    String notice = rs.getString("notice");
//
//                    System.out.println(numberOfPage + ", " + date + ", " + noticeHead + "\n" + notice);
//                }
//
//            } catch (SQLException e) {
//                throw new Error("Problem " , e);
//            }finally {
//                if (stmt != null) {
//                    stmt.close();
//                }
//            }
//
//        } catch (SQLException e) {
//            throw new Error("Problem " , e);
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch ( SQLException ex) {
//                System.out.println(ex.getMessage());
//            }
//        }
//
    }
}
