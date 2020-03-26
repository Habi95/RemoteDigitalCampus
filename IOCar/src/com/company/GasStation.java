package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class GasStation extends MotherShop {
    FuelTyp[] fuelTyps = new FuelTyp[5];
    double[] fuelPrice = new double[5];
    FuelTyp stringToEnum;
    ArrayList<FuelTyp> fuelTyp = new ArrayList<>();
    ArrayList<Double> fuelPriceOfl = new ArrayList<>();

    public GasStation(String shopname, Person owner, double superMarketWealth) throws IOException {
        super(shopname, owner, superMarketWealth);
    }

    @Override
    public void addStore() {

        fuelTyp.add(FuelTyp.SUPER95);
        fuelPriceOfl.add(FuelShop.getMyFuelPrice(FuelTyp.SUPER95));
        fuelTyp.add(FuelTyp.DIESEL);
        fuelPriceOfl.add(FuelShop.getMyFuelPrice(FuelTyp.DIESEL));
        prices.add(0.00);
        prices.add(0.00);

//        fuelTyps[storeCounter] = FuelTyp.SUPER95;
//        fuelPrice[storeCounter] = FuelShop.getMyFuelPrice(FuelTyp.SUPER95);
//        storeCounter++;
//        fuelTyps[storeCounter] = FuelTyp.DIESEL;
//        fuelPrice[storeCounter] = FuelShop.getMyFuelPrice(FuelTyp.DIESEL);
//        storeCounter++;

    }

    @Override
    public void goShopping(Customer customer) throws IOException {
        this.owner.greeting(customer);
        customer.greeting(this.owner);
        System.out.println(" ");
        super.goShopping(customer);

        this.owner.conversationToBuy(customer);
        customer.conversationToBuy(this.owner);
        String choiceToBuy = scanner.nextLine().toUpperCase();
        searchInArray(choiceToBuy);
        System.out.println(df.format(customer.myCar.tankSize) + " l left");
        double fuelYouNeed = customer.myCar.maxTankSize - customer.myCar.tankSize;
        double toPay = fuelPriceOfl.get(this.whichIndex) * fuelYouNeed;

        System.out.println(df.format(fuelYouNeed) + " l filled up");
        customer.myCar.tankSize += fuelYouNeed;

        System.out.println(df.format(toPay) + " € to pay");


        prices.add(this.whichIndex,toPay);


        System.out.println(customer.myCar.getMileage() + " new Mileage");
        System.out.println(customer.myCar.tankSize + "l left");

        typeToPay(customer);
        this.owner.thankSo(customer);
        customer.thankSo(this.owner);
        mywriter.write("\n" + customer.name +" "+ customer.lastName + " " + choiceToBuy + " " + prices.get(this.whichIndex) + "€"  );
        mywriter.close();



    }



    @Override
    public void listFromArray() {

        for (int i = 0; i < fuelTyp.size() ; i++) {
            System.out.println(fuelTyp.get(i) + " " + fuelPriceOfl.get(i) + "€"+ ",\n");
        }
//        for (FuelTyp fuelTyp : fuelTyps) {
//            if (fuelTyps[counter] == null) {
//                System.out.print("");
//            } else {
//                System.out.println(fuelTyp + " " + fuelPrice[counter] + "€" + ",\n");
//            }
//            counter++;
//        }
    }

    @Override
    public void searchInArray(String choiceToBuy) {

//        if (choiceToBuy.equalsIgnoreCase(String.valueOf(FuelTyp.SUPER95))){
//            this.stringToEnum = FuelTyp.SUPER95;
//        } else  if (choiceToBuy.equalsIgnoreCase(String.valueOf(FuelTyp.DIESEL))){
//            this.stringToEnum = FuelTyp.DIESEL;
//        }
        if (fuelTyp.stream().anyMatch((n) -> n.getName().equalsIgnoreCase(choiceToBuy))) {
            Optional<FuelTyp> myFuel = fuelTyp.stream().filter((n) -> n.getName().equalsIgnoreCase(choiceToBuy)).findFirst();
            this.whichIndex = fuelTyp.indexOf(myFuel.get());
            System.out.println(fuelTyp.get(this.whichIndex) + " " + fuelPriceOfl.get(this.whichIndex));
        }

//        for (int i = 0; i < fuelTyps.length; i++) {
//            if (fuelTyps[i] == null) {
//                System.out.print("");
//            }else if (fuelTyps[i].equals(this.stringToEnum)) {
//                choiceOf = i;
//                System.out.println(fuelTyps[i] + " " + fuelPrice[i]);
//            }
//
//        }
    }
}
