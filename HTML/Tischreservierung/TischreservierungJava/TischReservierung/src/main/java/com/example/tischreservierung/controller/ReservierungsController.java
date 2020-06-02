package com.example.tischreservierung.controller;

import com.example.tischreservierung.database.models.Desk;
import com.example.tischreservierung.database.models.Reservierung;
import com.example.tischreservierung.database.repository.DeskPlanRepo;
import com.example.tischreservierung.database.repository.ReservierungsRepo;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class ReservierungsController {
    DeskPlanRepo deskPlanRepo;
    ReservierungsRepo reservierungsRepo;

    public ReservierungsController(DeskPlanRepo deskPlanRepo, ReservierungsRepo reservierungsRepo) {
        this.deskPlanRepo = deskPlanRepo;
        this.reservierungsRepo = reservierungsRepo;
    }

    public boolean isFree(Reservierung reservierung) {
        boolean freeTable = false;
        ArrayList<Desk> desks = new ArrayList<>();
        desks = deskPlanRepo.findAll();
        int counter = 0;
        int seatCounter = 1;
        for (int i = 0; i < desks.size(); i++) {
            for (int j = 0; j < desks.get(i).getSeatInUse().length; j++) {
                if (desks.get(i).getSeatInUse()[j] == null) {
                    counter++;
                    if (counter >= reservierung.getHowMuch()) {
                        freeTable = true;
                        for (int k = 0; k < desks.get(i).getSeatInUse().length; k++) {
                            if (k >= reservierung.getHowMuch()) {
                                desks.get(i).setSeatInUse(null, k);
                            } else {
                                desks.get(i).setSeatInUse(String.valueOf(seatCounter), k);
                                seatCounter++;
                            }
                        }
                        reservierung.setDeskNumber(i + 1);
                        reservierung.setMaxSeatNumber(seatCounter);
                        seatCounter = 0;
                        return freeTable;
                    }
                }
            }
        }
        seatCounter = 0;
        return freeTable;
    }


}
