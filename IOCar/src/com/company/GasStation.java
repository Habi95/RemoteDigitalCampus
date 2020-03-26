package com.company;

import java.io.IOException;

public class GasStation extends MotherShop {
    FuelTyp[] fuelTyps = new FuelTyp[5];
    double[] fuelPrice = new double[5];
    FuelTyp stringToEnum;

    public GasStation(String shopname, Person owner, double superMarketWealth) throws IOException {
        super(shopname, owner, superMarketWealth);
    }

    @Override
    public void addStore() {
        fuelTyps[storeCounter] = FuelTyp.SUPER95;
        fuelPrice[storeCounter] = FuelShop.getMyFuelPrice(FuelTyp.SUPER95);
        storeCounter++;
        fuelTyps[storeCounter] = FuelTyp.DIESEL;
        fuelPrice[storeCounter] = FuelShop.getMyFuelPrice(FuelTyp.DIESEL);
        storeCounter++;

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
        double toPay = fuelPrice[choiceOf] * fuelYouNeed;

        System.out.println(df.format(fuelYouNeed) + " l filled up");
        customer.myCar.tankSize += fuelYouNeed;

        System.out.println(df.format(toPay) + " € to pay");
        price[choiceOf] = toPay;


        System.out.println(customer.myCar.getMileage() + " new Mileage");
        System.out.println(customer.myCar.tankSize + "l left");

        typeToPay(customer);
        this.owner.thankSo(customer);
        customer.thankSo(this.owner);
        mywriter.write(customer.name +" "+ customer.lastName + " " + choiceToBuy + " " + price[choiceOf] + "€"  );
        mywriter.close();



    }



    @Override
    public void listFromArray() {
        for (FuelTyp fuelTyp : fuelTyps) {
            if (fuelTyps[counter] == null) {
                System.out.print("");
            } else {
                System.out.println(fuelTyp + " " + fuelPrice[counter] + "€" + ",\n");
            }
            counter++;
        }
    }

    @Override
    public void searchInArray(String choiceToBuy) {
        if (choiceToBuy.equalsIgnoreCase(String.valueOf(FuelTyp.SUPER95))){
            this.stringToEnum = FuelTyp.SUPER95;
        } else  if (choiceToBuy.equalsIgnoreCase(String.valueOf(FuelTyp.DIESEL))){
            this.stringToEnum = FuelTyp.DIESEL;
        }
        for (int i = 0; i < fuelTyps.length; i++) {
            if (fuelTyps[i] == null) {
                System.out.print("");
            }else if (fuelTyps[i].equals(this.stringToEnum)) {
                choiceOf = i;
                System.out.println(fuelTyps[i] + " " + fuelPrice[i]);
            }

        }
    }
}
