package com.company;

public class FuelShop {

    public static double getMyFuelPrice(FuelTyp fuelTyp) {
        switch (fuelTyp) {
            case SUPER95:
                return 1.020;

            case DIESEL:
                return 1.008;
            default:
                return 0;
        }
    }
}
