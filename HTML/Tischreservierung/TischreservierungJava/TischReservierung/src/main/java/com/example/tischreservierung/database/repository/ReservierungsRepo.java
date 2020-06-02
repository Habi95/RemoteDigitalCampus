package com.example.tischreservierung.database.repository;

import com.example.tischreservierung.database.models.Reservierung;
import com.example.tischreservierung.view.terminal_output.TerminalOutput;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReservierungsRepo implements Repository<Reservierung> {

    private DB_Connector db_connector;
    private TerminalOutput output;

    public ReservierungsRepo(DB_Connector db_connector, TerminalOutput output) {

        this.db_connector = db_connector;
        this.output = output;
    }

    @Override
    public ArrayList<Reservierung> findAll() {
        ArrayList<Reservierung> reservierungs = new ArrayList<>();
        String sql = "SELECT * FROM `reservierung`";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return null;
        }
       try {
           while (result.next()) {
               int id = result.getInt("id");
               String lastname = result.getString("lastname");
               Date date = result.getDate("date");
               Time time = result.getTime("time");
               int howmuch = result.getInt("howMuch");

               reservierungs.add(new Reservierung(id,lastname,howmuch,time, date));
           }

       }catch (SQLException e) {
           e.printStackTrace();
           output.outPutStringLanding(e.getMessage());
       } finally {
           db_connector.closeConnection();
           return  reservierungs;
       }
    }

    @Override
    public Reservierung findOne(int id) {
       Reservierung reservierungs = null;
        String sql = "SELECT * FROM `reservierung` WHERE id =" + id;
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            output.outPutStringLanding("something go wrong");
            return null;
        }
        try {
            while (result.next()) {
                String lastname = result.getString("lastname");
                Date date = result.getDate("date");
                Time time = result.getTime("time");
                int howmuch = result.getInt("howMuch");

                reservierungs = new Reservierung(id,lastname,howmuch,time, date);
            }

        }catch (SQLException e) {
            e.printStackTrace();
            output.outPutStringLanding(e.getMessage());
        } finally {
            db_connector.closeConnection();
            return  reservierungs;
        }
    }

    @Override
    public void create(Reservierung entity) {

    }
}
