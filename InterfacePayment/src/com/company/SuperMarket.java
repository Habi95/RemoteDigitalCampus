package com.company;

import java.util.Scanner;

public class SuperMarket extends MotherShop {

    String[] items = new String[20];

    public SuperMarket(String shopname, Person owner, double superMarketWealth) {
        super(shopname, owner, superMarketWealth);
    }

    @Override
    public void goShopping(Customer custom) {

        this.owner.greeting(custom);
        custom.greeting(this.owner);
        System.out.println(" ");

        super.goShopping(custom);
        this.owner.conversationToBuy(custom);
        custom.conversationToBuy(this.owner);
        String choiceToBuy = scanner.nextLine();
        searchInArray(choiceToBuy);
        this.owner.conversationToPay(custom) ;
        System.out.print(price[choiceOf] + "€ \n");

        typeToPay(custom);
        this.owner.thankSo(custom);
        custom.thankSo(this.owner);
    }

    @Override
    public void listFromArray() {
        for (String item : items) {
            if (items[counter] == null) {
                System.out.print("");
            } else {
                System.out.print(item + " " + price[counter] + "€" + ",\n");

            }
            counter++;


        }
    }

    @Override
    public void addStore() {
        this.items[storeCounter] ="Beef filet 1Kg";
        this.price[storeCounter] = 42.53;
        storeCounter++;
        this.items[storeCounter] = "Laptop lenovo";
        this.price[storeCounter] = 420.60;
        storeCounter++;
        this.items[storeCounter] = "VideoGame";
        this.price[storeCounter] = 65.80;
        storeCounter++;
        this.items[storeCounter] = "RedBull";
        this.price[storeCounter] = 1.49;
        storeCounter++;
        this.items[storeCounter] = "ShipTicket for 1 week";
        this.price[storeCounter] = 1300.99;
        storeCounter++;
        this.items[storeCounter] = "Backbag";
        this.price[storeCounter] = 50.45;
        storeCounter++;
    }

    @Override
    public void searchInArray(String choiceToBuy) {

        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                System.out.print("");
            }else if (items[i].equalsIgnoreCase(choiceToBuy)) {
                choiceOf = i;
                System.out.println(items[i] + " " + price[i]);
            }

        }
    }


}
    //    String shopname;
//    Person owner;
//    double superMarketWealth;
//    double superMarketCashStock;
//    double superMarketCashInFront;
//    String[] items = new String[2];//{ "Laptop lenovo", "VideoGame", "ShipTicket 1 week", "Backbag"};
//    double[] price = new double[2]; //{ 420.60, 65.80, 1300.99, 50.45};
//    int storeCounter = 0;
//    int counter = 0;
//    int choiceOf;
//    Scanner scanner = new Scanner(System.in);
//
//
//    public SuperMarket(Person owner, double superMarketWealth, String shopName) {
//        this.owner = owner;
//        this.superMarketWealth = superMarketWealth;
//        this.shopname = shopName;
//        this.superMarketCashStock = superMarketWealth / 3.3;
//        this.superMarketCashInFront = superMarketCashStock / 10;
//    }
//
//    private void addStore() {
//        this.items[storeCounter] ="Beef filet 1Kg";
//        this.price[storeCounter] = 42.53;
//        storeCounter++;
//        this.items[storeCounter] ="Laptop lenovo";
//        this.price[storeCounter] = 420.60;
//        storeCounter++;
//        this.items[storeCounter] ="VideoGame";
//        this.price[storeCounter] = 65.80;
//        storeCounter++;
//        this.items[storeCounter] ="Laptop lenovo";
//        this.price[storeCounter] = 420.60;
//        storeCounter++;
//        this.items[storeCounter] ="ShipTicket for 1 week";
//        this.price[storeCounter] = 1300.99;
//        storeCounter++;
//        this.items[storeCounter] ="Backbag";
//        this.price[storeCounter] =50.45;
//        storeCounter++;
//
//    }
//
//    public void goShopping(Person custom) {
//        addStore();
//        listFromArray();
//        this.owner.conversationToBuy(custom);
//        custom.conversationToBuy(this.owner);
//        String choiceToBuy = scanner.nextLine();
//        searchInArray(choiceToBuy);
//        this.owner.conversationToPay(custom) ;
//        System.out.print(price[choiceOf] + "€ \n");
//
//        typeToPay(custom);
//        this.owner.thankSo(custom);
//        custom.thankSo(this.owner);
//
//
//    }
//
//    public void typeToPay(Person custom) {
//        custom.conversationToPay(owner);
//        String choiceOfPayment = scanner.nextLine();
//       choiceOfPayment = checkLiquidity(choiceOfPayment, custom);
//        if (choiceOfPayment.equalsIgnoreCase("Kreditcard")){
//            System.out.println(custom.kreditCard.typOfPayment);
//            custom.kreditCard.soundOfPayment();
//            custom.kreditCard.paymentOutput(price[choiceOf]);
//            this.superMarketCashInFront =+ price[choiceOf];
//            custom.kreditbalance =  custom.kreditbalance + custom.kreditCard.getBalace();
//
//
//        } else if (choiceOfPayment.equalsIgnoreCase("Bill")) {
//            System.out.println(custom.saleOnAccount.typOfPayment);
//            custom.saleOnAccount.soundOfPayment();
//            custom.saleOnAccount.paymentOutput(price[choiceOf]);
//            this.superMarketCashInFront =+ price[choiceOf];
//            custom.saleOnAccountBalance =+ custom.saleOnAccount.getBalace();
//
//        } else if (choiceOfPayment.equalsIgnoreCase("Bankomatcard")) {
//            System.out.println(custom.bankomatCard.typOfPayment);
//            custom.bankomatCard.soundOfPayment();
//            custom.bankomatCard.paymentOutput(price[choiceOf]);
//            this.superMarketCashInFront =+ price[choiceOf];
//            custom.balance =  custom.balance + custom.bankomatCard.getBalace();
//
//        } else if (choiceOfPayment.equalsIgnoreCase("Bar")) {
//            custom.bar.balace += custom.moneyPouch;
//            System.out.println(custom.bar.typOfPayment);
//            custom.bar.soundOfPayment();
//            custom.bar.paymentOutput(price[choiceOf]);
//            this.superMarketCashInFront =+ price[choiceOf];
//            custom.moneyPouch = custom.bar.getBalace();
//
//        }
//
//
//    }
//    private String checkLiquidity (String choiceOfPayment , Person custom) {
//        String choiceFor;
//       if (choiceOfPayment.equalsIgnoreCase("Kreditcard")) {
//           if (price[choiceOf] > custom.maxKredit) {
//               System.out.println("would you like to change your payment to bill? max lvl over balance ?\n Ya / No");
//               choiceFor = scanner.nextLine();
//               if (choiceFor.equalsIgnoreCase("ya")){
//                   choiceOfPayment = "Bill";
//               }
//
//
//           }
//       } else if (choiceOfPayment.equalsIgnoreCase("Bankomarcard")) {
//           if (price[choiceOf] > custom.balance) {
//               System.out.println("would you like to change your payment to kreditcard? max lvl over balance ?\n Ya / No");
//               choiceFor = scanner.nextLine();
//               if (choiceFor.equalsIgnoreCase("ya")){
//                   choiceOfPayment = "Kreditcard";
//               }
//           }
//
//       } else if (choiceOfPayment.equalsIgnoreCase("Bill")) {
//           choiceOfPayment = "Bill";
//
//       } else if (choiceOfPayment.equalsIgnoreCase("Bar")) {
//           if (price[choiceOf] > custom.moneyPouch) {
//               System.out.println("would you like to change your payment to bankomatcard or kreditcard or bill? max lvl over balance ?");
//               choiceFor = scanner.nextLine();
//               if (choiceFor.equalsIgnoreCase("kreditcard")){
//                   choiceOfPayment = "Kreditcard";
//               } else  if (choiceFor.equalsIgnoreCase("bankomatcard")){
//                   choiceOfPayment = "Bankomatcard";
//               } else  if (choiceFor.equalsIgnoreCase("bill")){
//                   choiceOfPayment = "Bill";
//               }
//           }
//
//       }
//        return choiceOfPayment;
//    }
//

//
//    private void searchInArray(String choiceToBuy) {
//        for (int i = 0; i < items.length; i++) {
//            if (items[i].equalsIgnoreCase(choiceToBuy)) {
//                choiceOf = i;
//                System.out.println(items[i] + " " + price[i]);
//            }
//
//        }
//    }
//
//
//
//
//    public double getSuperMarketWealth() {
//        return superMarketWealth;
//    }
//
//    public void setSuperMarketWealth(double superMarketWealth) {
//        this.superMarketWealth = superMarketWealth;
//    }

