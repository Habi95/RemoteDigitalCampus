package com.company;

public abstract class Vehicle {

    public String brand;
    public FuelTyp fuelTyp;
    public Customer owner;
    public String color;
    public double horsePower;
    public double maxTankSize;
    public double mileage;

    public double tankSize;
    public double kmAmount;
    public boolean isAllowedToDrive;

    public Vehicle(String brand, FuelTyp fuelTyp, Customer owner, String color, double horsePower, double maxTankSize, double mileage) {
        this.brand = brand;
        this.fuelTyp = fuelTyp;
        this.owner = owner;
        this.color = color;
        this.horsePower = horsePower;
        this.maxTankSize = maxTankSize;
        this.mileage = mileage;
        this.tankSize = maxTankSize;
    }

    public abstract void drive(int kmAmount);
    public abstract void goFillUpTheFuel(GasStation gasStation);

    public void driverLicenz() {
        if (owner.age >= 18 || owner.age <= 75 ) {
            isAllowedToDrive = true;
        } else if (owner.age < 18) {
            System.out.println("you´re to young");
            isAllowedToDrive = false;
        } else if (owner.age > 75) {
            System.out.println("you´re to old.. ask someone to drive");
            isAllowedToDrive = false;
        }
    }

    public double getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(double horsePower) {
        this.horsePower = horsePower;
    }

    public double getMaxTankSize() {
        return maxTankSize;
    }

    public void setMaxTankSize(double maxTankSize) {
        this.maxTankSize = maxTankSize;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public double getTankSize() {
        return tankSize;
    }

    public void setTankSize(double tankSize) {
        this.tankSize = tankSize;
    }

    public double getKmAmount() {
        return kmAmount;
    }

    public void setKmAmount(double kmAmount) {
        this.kmAmount = kmAmount;
    }
}
