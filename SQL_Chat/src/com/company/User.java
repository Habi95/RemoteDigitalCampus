package com.company;

import java.sql.*;

public abstract class User implements IChat {

    String user;
    int luca = 1;
    int peter = 2;

    Connection conn = null;
    String url = "jdbc:mysql://localhost:3306/chat?user=root";

    public User(String user) {
        this.user = user;
    }

    @Override
    public void readMessage()
    {

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

    }
}
