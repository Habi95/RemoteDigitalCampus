package com.example.tischreservierung.controller;

import com.example.tischreservierung.database.models.Desk;
import com.example.tischreservierung.database.models.Reservierung;
import com.example.tischreservierung.database.models.ReservierungsDetail;
import com.example.tischreservierung.database.repository.DB_Connector;
import com.example.tischreservierung.database.repository.DeskPlanRepo;
import com.example.tischreservierung.database.repository.ReservierungsRepo;
import com.example.tischreservierung.view.terminal_output.TerminalOutput;

import java.util.ArrayList;
import java.util.Date;

public class DeskPlanController {

    ReservierungsRepo reservierungsRepo;
    DeskPlanRepo deskPlanRepo;
    ArrayList<ReservierungsDetail> reservierungsDetails = new ArrayList<>();
    public DeskPlanController(ReservierungsRepo reservierungsRepo, DeskPlanRepo deskPlanRepo) {
        this.reservierungsRepo = reservierungsRepo;
        this.deskPlanRepo = deskPlanRepo;
    }


    public void whatsFree (Reservierung reservierung, ArrayList<Desk> desks) {

        reservierungsDetails = reservierungsRepo.findDate(reservierung.getDate());
        System.out.println("");

        for (int i = 0; i < reservierungsDetails.size() ; i++) {
            int deskId = reservierungsDetails.get(i).getDeskID() - 1;
            int seatID = reservierungsDetails.get(i).getSeatID() - 1;
            desks.get(deskId).setSeatInUse(String.valueOf(reservierungsDetails.get(i).getId()), seatID);


        }

    }
}
