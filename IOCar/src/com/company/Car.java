package com.company;

import java.io.IOException;
import java.text.DecimalFormat;

public class Car extends Vehicle implements IVehicleSound {
        DecimalFormat df = new DecimalFormat("#.##");

    public Car(String brand, FuelTyp fuelTyp, Customer owner, String color, double horsePower, double maxTankSize, double mileage) {
        super(brand, fuelTyp, owner, color, horsePower, maxTankSize, mileage);
    }

    public double tankToDrive() {
        double one00km;
        if (this.fuelTyp.equals(FuelTyp.SUPER95)) {
            one00km = this.horsePower / 15;
        } else {
            one00km = this.horsePower / 20;
        }
        return one00km;
    }

    @Override
    public void drive(int kmAmount) {
        driverLicenz();
        this.kmAmount = kmAmount;
        if (this.isAllowedToDrive == true){
            this.mileage += this.kmAmount;
            double howMuchFuelYouNeed = this.kmAmount / 100 * tankToDrive();
            this.tankSize -= howMuchFuelYouNeed;
            if (this.tankSize <= this.tankSize / 100 * 10) {
                if (this.tankSize <= 0) {
                    System.out.println("Fuel is empty ... your must call someone to help ya \n" +
                            "Call GasStation ...\n");
                    this.tankSize = 0;
                } else {
                    System.out.println("Your must go to fuel up ! \n" + df.format(this.tankSize) + " liter left");
                }
            } else {
                System.out.println("Fuel is " + df.format(this.tankSize) + " liter left\n");
            }




    } else {
            System.out.println("dont take this car ...");
            //choice to drive.. whenn drive call police
        }


    }

    @Override
    public void goFillUpTheFuel(GasStation gasStation) throws IOException {

         gasStation.goShopping(this.owner);



    }

    @Override
    public void startEnigne() {

    }

    @Override
    public void driveSound() {

    }

    @Override
    public void engineCoolDown() {

    }
}
