package com.company;

import java.sql.*;
import java.util.Scanner;

public class User2 implements IChat {
    String user = "Peter";
    int luca = 1;
    int peter = 2;
    Connection conn = null;
    String url = "jdbc:mysql://localhost:3306/chat?user=root";

    @Override
    public void readMessage() {

        try {



            conn = DriverManager.getConnection(url);

            Statement stmt = null;

            String query = "SELECT sender_user.name as sender, receiver_user.name as receiver, message.message, message.time \n" +
                    "FROM `message`\n" +
                    "INNER JOIN person as receiver ON message.receiver_id = receiver.id\n" +
                    "INNER JOIN person as sender ON message.sender_id = sender.id\n" +
                    "INNER JOIN user as receiver_user ON  receiver_user.id = receiver.id\n" +
                    "INNER JOIN user as sender_user ON  sender_user.id = sender.id"
                    + " WHERE receiver.id = 2";


            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
//
//
                while (rs.next()) {
                    String sender = rs.getString("sender");
                    String recevier = rs.getString("receiver");
                    String message = rs.getString("message");
                    String time = String.valueOf(rs.getTime("time"));



                    System.out.println("sender: " + sender +", empfänger: " + recevier +
                            "\n Nachricht: " + message + " um: " + time);
                }
            } catch (SQLException e) {
                throw new Error("Problem " , e);
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (SQLException e) {
            throw new Error("Problem " , e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    public void writeMessage() {
        Scanner input = new Scanner(System.in);

        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = null;
            System.out.println("Nachricht eingeben");
            String writeMessage = input.nextLine();
            stmt = conn.createStatement();
            String sql ="INSERT INTO `message`" +
                    "( `receiver_id`, `sender_id`, `message`)\n" +
                    "VALUES ('" + luca + "' , '" + peter + "' , '" + writeMessage + "')";

            stmt.executeUpdate(sql);




        } catch (SQLException e) {
            throw new Error("Problem " , e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }





//
//                String sql = "INSERT INTO person1 "
//                        + " (`name` , `messageP1`) "
//                        + " VALUES ('Peter' , 'hallo')";
//
//                stmt.executeUpdate(sql);
//                System.out.println("Done");




    }
}
