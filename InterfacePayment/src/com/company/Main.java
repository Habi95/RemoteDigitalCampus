package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        String input;
        Rectangle rectangle = new Rectangle(5,8);
        Square square = new Square(5,5);

        Customer hans = new Customer("Hans", "Petersoon", 500.36,24);

        Car audi = new Car("Audi", FuelTyp.SUPER95, hans,"blue" , 200,65,52_000); // remove hans
        audi.owner = hans;
        hans.myCar = audi;

        ShopOwner klaus = new ShopOwner( "Klaus", "Müller", 10_000,50);
        SuperMarket trashShop = new SuperMarket("trashShop", klaus, klaus.balance);

        ShopOwner fred = new ShopOwner("Fred", "Kranz", 12_000,41);
        GasStation ggogh = new GasStation("GOGasOrGoHome",fred,fred.balance);

//
//        klaus.greeting(hans);
//        hans.greeting(klaus);
//        System.out.println(" ");
//        trashShop.goShopping(hans);
//        System.out.println(audi.getMileage());
//        audi.drive(200);
//
//        audi.goFillUpTheFuel(ggogh);

       while (running == true) {

           rectangle.draw();
           System.out.println("\n");
           square.draw();
           System.out.println("\nDo you want to DRIVE your car or go SHOPPING @trashShop?");
           input = scanner.next();
           if (input.equalsIgnoreCase("shopping")) {
               PlayGoShopping goShopping = new PlayGoShopping(hans,trashShop);
           } else if (input.equalsIgnoreCase("Drive")){

                    PlayDriveCar goDrive = new PlayDriveCar(audi,ggogh);

           }






       }


    }





}
