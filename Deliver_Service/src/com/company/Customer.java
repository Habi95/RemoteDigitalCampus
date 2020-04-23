package com.company;


import java.sql.*;
import java.util.Scanner;

public class Customer {

    Connection conn = null;
    String url = "jdbc:mysql://localhost:3306/lieferservice?user=root";
    Scanner scanner = new Scanner(System.in);


    public void createUser () {

        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = null;

            try {
                stmt = conn.createStatement();
                System.out.println("Willkommen zur Account erstellung");
                System.out.println("Email");
                String email = scanner.nextLine();

                if (!isEmailFree(email)) {
                    createUser();
                } else {

                    System.out.println("Passwort");
                    String password = scanner.nextLine();
                    System.out.println("ORT");
                    String place = scanner.nextLine();

                    String sql = "INSERT INTO `user`" +
                            "(`email`, `password`, `place`) " +
                            "VALUES " +
                            "('" + email + "','" + password + "','" + place + "') ";

                    stmt.executeUpdate(sql);
                    System.out.println("Account wurde erstellt\nIhre Daten: \n");
                    accInfo();


                }





            } catch (SQLException e) {
                throw new Error("problem " , e);
            } finally {
                try {
                    if (stmt != null)
                        stmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }



        } catch (SQLException e) {
           throw new Error("Problem " , e);
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }


    }
    public void accInfo () {

        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = null;
            String query = "SELECT `user_id`, `email`, `password`, `place` FROM `user` ORDER BY user_id DESC LIMIT 1";
            //SELECT `user_id`, `email`, `password`, `place` FROM `user` ORDER BY user_id DESC LIMIT 1

            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    int userID = rs.getInt("user_id");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String place = rs.getString("place");

                    System.out.println("User ID: " + userID + "\nEmail: " + email + "\nPasswort: " + password + "\nOrt: " + place);

                }


            } catch (SQLException e) {
                throw new Error("Problem " , e);
            } finally {
                try {
                    if (stmt != null)
                        stmt.close();
                }catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }


        } catch (SQLException e) {
            throw new Error("Problem " , e);
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    public void custAccInfo (int userId, String userEmail, String userPlace) {

        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = null;
            String query = "SELECT  `email`, `user_id`,  `password`, `place` FROM `user` Where user.user_id =" + userId;


            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String email = rs.getString("email");
                    int userID = rs.getInt("user_id");
                    String password = rs.getString("password");
                    String place = rs.getString("place");

                    if (email.equals(userEmail) && place.equals(userPlace)) {


                    System.out.println("User ID: " + userID + "\nEmail: " + email + "\nPasswort: " + password + "\nOrt: " + place);


                    } else {
                        System.out.println("Falsche Daten");
                    }


                }


            } catch (SQLException e) {
                throw new Error("Problem " , e);
            } finally {
                try {
                    if (stmt != null)
                        stmt.close();
                }catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }


        } catch (SQLException e) {
            throw new Error("Problem " , e);
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    public boolean isRightLogin (String userEmail, String userPassword) {
        boolean isRightLogin = false;
        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = null;
            String query = "SELECT  `email`, `password`  FROM `user`";


            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String email = rs.getString("email");
                    String password = rs.getString("password");

                        if (email.equals(userEmail) && password.equals(userPassword)) {
                            isRightLogin = true;
                        }

                    }





            } catch (SQLException e) {
                throw new Error("Problem " , e);
            } finally {
                try {
                    if (stmt != null)
                        stmt.close();
                }catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }


        } catch (SQLException e) {
            throw new Error("Problem " , e);
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return isRightLogin;
    }


    public boolean isEmailFree (String email) {

        boolean isEmailFree = false;

        try {

            conn = DriverManager.getConnection(url);
            Statement stmt = null;

            String query = "SELECT COUNT(*) as anzahl FROM `user` WHERE email = '" + email + "'"; //Select
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    int count = rs.getInt("anzahl");
                    if (count > 0) {
                        System.out.println("Email vergeben");
                    } else {
                        isEmailFree = true;
                    }
                }
            } catch (SQLException e) {
                throw  new Error("Problem " , e);
            } finally {
                try {
                    if (stmt != null)
                        stmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }


        } catch (SQLException e) {
           throw  new Error("Problem " , e);
        } finally {
            try {
                if (conn != null )
                    conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return isEmailFree;

    }


}
