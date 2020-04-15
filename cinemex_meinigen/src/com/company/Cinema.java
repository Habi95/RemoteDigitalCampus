package com.company;

import java.sql.*;
import java.util.Scanner;

public class Cinema {

    Connection conn = null;
    String url = "jdbc:mysql://localhost:3306/cinema_meinigen?user=root";
    Scanner scanner = new Scanner(System.in);
    int reserveirungsID;


    public void cineProgramm () {
        try {

            conn = DriverManager.getConnection(url);
            Statement stmt = null;
            String query = " SELECT kino_programm.id , movies.title , fsk.fsk as age_clearance, movies.length_in_min, kino_programm.saal, kino_programm.time_begin   FROM `kino_programm` \n" +
                    "inner join movies ON kino_programm.movie = movies.id\n" +
                    "inner join fsk ON movies.fsk = fsk.id\n";
            try {

                stmt = conn.createStatement();


                ResultSet rs = stmt.executeQuery(query);
                headOfProgram();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String titel = rs.getString("title");
                    int fsk = rs.getInt("age_clearance");
                    int movie_lenght = rs.getInt("length_in_min");
                    int saal = rs.getInt("saal");
                    Time time = rs.getTime("time_begin");


                    System.out.println( id + "\t--\t" + titel + "\t--\t" + fsk + "\t--\t" + movie_lenght + "\t--\t" + saal + "\t--\t" + time);
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

    public void saalAnsicht () {
        System.out.println("Bitte Saal eingeben für Ansicht");
        int temp = scanner.nextByte();
        try {

            conn = DriverManager.getConnection(url);
            Statement stmt = null;
            String query = " SELECT  cinemex_saal_plan.saal_id , saal_sitzplatz_plan.sitz_platz_id, sitzplatz.sitz_reihe, sitzplatz.sitz_platz  FROM `cinemex_saal_plan` \n" +
                    "Inner JOIN saal_plan ON cinemex_saal_plan.saal_id = saal_plan.id\n" +
                    "Inner Join saal_sitzplatz_plan ON saal_sitzplatz_plan.saal_id = saal_plan.id\n" +
                    "Inner JOIN sitzplatz  ON saal_sitzplatz_plan.sitz_platz_id = sitzplatz.id\n" +
                    "WHERE cinemex_saal_plan.saal_id = " + temp + "";


            try {

                stmt = conn.createStatement();


                ResultSet rs = stmt.executeQuery(query);
                headOfSaal();

                while (rs.next()) {
                    int id = rs.getInt("saal_id");
                    int sitz_platz_id = rs.getInt("sitz_platz_id");
                    int sitz_reihe = rs.getInt("sitz_reihe");
                    int sitz_platz = rs.getInt("sitz_platz");



                    System.out.println( id + "\t\t--\t\t" + sitz_platz_id + "\t\t--\t\t" + sitz_reihe + "\t\t--\t\t" + sitz_platz);


                }

                platzauswahl(temp);

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

    public void resavierung () {


        try {

            conn = DriverManager.getConnection(url);
            Statement stmt = null;
            try {

                stmt = conn.createStatement();

                System.out.println(" Film ID eingeben");
                int temp = scanner.nextByte();
                System.out.println(" Stzplatz ID eingeben");
                int temp2 = scanner.nextByte();

                if(!isFreeSeat(temp2)){
                    resavierung();
                } else {


                    String sql = " INSERT INTO `resrvierung`" +
                            "(`movie`, `platz_id`) " +
                            "VALUES (" + temp + " , " + temp2 + ")";

                    stmt.executeUpdate(sql);
                    System.out.println("\n");
                    platzauswahl(temp);
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

    public void reservierungsOutput () {

        try {

            System.out.println("Ihre Reservierung: ");
            conn = DriverManager.getConnection(url);
            Statement stmt = null;
            String query1 = "SELECT max(resrvierung.id) FROM `resrvierung`";
            try {

                stmt = conn.createStatement();


                ResultSet rs = stmt.executeQuery(query1);
                while (rs.next()) {
                    this.reserveirungsID = rs.getInt("max(resrvierung.id)");



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


            String query = " SELECT resrvierung.id ,  movies.title, sitzplatz.sitz_reihe as 'reihe' , sitzplatz.sitz_platz as 'platz' FROM `resrvierung` \n" +
                    "INNER JOIN kino_programm ON  resrvierung.movie = kino_programm.id\n" +
                    "INNER join movies On  movies.id = kino_programm.movie\n" +
                    "INNER JOIN saal_sitzplatz_plan ON resrvierung.platz_id = saal_sitzplatz_plan.id\n" +
                    "INNER JOIN sitzplatz ON saal_sitzplatz_plan.sitz_platz_id = sitzplatz.id\n" +
                    "WHERE resrvierung.id =" + this.reserveirungsID + "" ;
            try {

                stmt = conn.createStatement();


                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    int sitzReihe = rs.getInt("reihe");
                    int sitzPlatz = rs.getInt("platz");

                    System.out.println("ID: " + id + "\nTitle: " + title + "\nSitzreihe: " + sitzReihe +"\nSitzplatz: " + sitzPlatz  );
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

    public void customerOutput (int Customerid) {

        try {

            System.out.println("Ihre Reservierung: ");
            conn = DriverManager.getConnection(url);
            Statement stmt = null;



            String query = " SELECT resrvierung.id ,  movies.title, sitzplatz.sitz_reihe as 'reihe' , sitzplatz.sitz_platz as 'platz' FROM `resrvierung` \n" +
                    "INNER JOIN kino_programm ON  resrvierung.movie = kino_programm.id\n" +
                    "INNER join movies On  movies.id = kino_programm.movie\n" +
                    "INNER JOIN saal_sitzplatz_plan ON resrvierung.platz_id = saal_sitzplatz_plan.id\n" +
                    "INNER JOIN sitzplatz ON saal_sitzplatz_plan.sitz_platz_id = sitzplatz.id\n" +
                    "WHERE resrvierung.id =" + Customerid + "" ;
            try {

                stmt = conn.createStatement();


                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    int sitzReihe = rs.getInt("reihe");
                    int sitzPlatz = rs.getInt("platz");

                    System.out.println("ID: " + id + "\nTitle: " + title + "\nSitzreihe: " + sitzReihe +"\nSitzplatz: " + sitzPlatz  );
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


    public void ticketCanceling () {

        try {

            conn = DriverManager.getConnection(url);
            Statement stmt = null;
            String query = " ";
            try {

                stmt = conn.createStatement();
                System.out.println("Welche ID wollen Sie stonieren?");

                int temp = scanner.nextByte();
                customerOutput(temp);
                String sql = " DELETE FROM `resrvierung` WHERE CONCAT(resrvierung.id) = '"+ temp + "'";
                stmt.executeUpdate(sql);

                System.out.println("\nIhre Reservierung wurde gelöscht\nSchönen Tag");






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

    public boolean isFreeSeat(int sitzplatz) {

        boolean isFreeSeat = false;
        try {

            conn = DriverManager.getConnection(url);
            Statement stmt = null;

            String query = "SELECT count(*) as anzahl FROM `resrvierung` WHERE platz_id = " + sitzplatz;
            try {

                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                   int count = rs.getInt("anzahl");
                   if(count > 0){
                       System.out.println("vergeben");

                   } else {
                       isFreeSeat = true;
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
        return isFreeSeat;
    }






public void platzauswahl(int saal) {



    try {

        conn = DriverManager.getConnection(url);
        Statement stmt = null;

        String query = "SELECT saal_sitzplatz_plan.id as saalsitzplatz_id, resrvierung.platz_id as res_id from saal_sitzplatz_plan\n" +
                "LEFT JOIN resrvierung ON saal_sitzplatz_plan.id = resrvierung.platz_id " +
                "Where saal_sitzplatz_plan.saal_id = " + saal + "";
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);


            int counter = 0;
            while (rs.next()) {
                int sitzID1 = rs.getInt("saalsitzplatz_id");
                int sitzID2 = rs.getInt("res_id");

                if (sitzID1 == sitzID2) {
                    System.out.print("|x|");

                } else {
                    System.out.print("|_|");
                }

                counter++;
                if(counter % 8 == 0) {
                    System.out.println();

                }

                //if (counter == 40) {
//                        rs.;
//                }


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

public void headOfProgram () {
    System.out.print("ID \t--\t\tTitle\t--\t\tFSK\t--\t\tlengt\t--\tSaal\t--\tTime\n\n");
}

public void headOfSaal () {
    System.out.print("Saal ID -- Sitzplatz ID -- Sitzreihe -- Sitzplatz\n\n");
}

}
