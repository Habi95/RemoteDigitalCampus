package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class MotherShop implements IShop{
    String shopeName;
    Person owner;
    double superMarketWealth;
    double superMarketCashStock;
    double superMarketCashInFront;
    double [] price = new double[20];
    ArrayList<Double> prices = new ArrayList<>();
    int storeCounter = 0;
    int counter = 0;
    int choiceOf;
    Scanner scanner = new Scanner(System.in);
    DecimalFormat df = new DecimalFormat("#.##");
    File bill = new File("C:\\Users\\DCV\\Desktop\\HelloWorld\\RemoteDigitalCampus\\CarBsp.text");
    FileWriter mywriter = new FileWriter(bill,true);
    int whichIndex;

    public MotherShop(String shopname, Person owner, double superMarketWealth) throws IOException {
        this.shopeName = shopname;
        this.owner = owner;
        this.superMarketWealth = superMarketWealth;

        this.superMarketCashStock = superMarketWealth / 3.3;
        this.superMarketCashInFront = superMarketCashStock / 10;

    }
    @Override
    public void addStore() {

    }

    @Override
    public void goShopping(Customer customer) throws IOException {

            addStore();
            listFromArray();




    }

    @Override
    public void typeToPay(Person custom) {
        custom.conversationToPay(owner);
        String choiceOfPayment = scanner.nextLine();
        choiceOfPayment = checkLiquidity(choiceOfPayment, custom);
        if (choiceOfPayment.equalsIgnoreCase("Kreditcard")){
            System.out.println(custom.kreditCard.typOfPayment);
            custom.kreditCard.soundOfPayment();
            custom.kreditCard.paymentOutput(prices.get(this.whichIndex));
            this.superMarketCashInFront =+ prices.get(this.whichIndex);
            custom.kreditbalance =  custom.kreditbalance + custom.kreditCard.getBalace();


        } else if (choiceOfPayment.equalsIgnoreCase("Bill")) {
            System.out.println(custom.saleOnAccount.typOfPayment);
            custom.saleOnAccount.soundOfPayment();
            custom.saleOnAccount.paymentOutput(prices.get(this.whichIndex));
            this.superMarketCashInFront =+ prices.get(this.whichIndex);
            custom.saleOnAccountBalance =+ custom.saleOnAccount.getBalace();

        } else if (choiceOfPayment.equalsIgnoreCase("Bankomatcard")) {
            System.out.println(custom.bankomatCard.typOfPayment);
            custom.bankomatCard.soundOfPayment();
            custom.bankomatCard.paymentOutput(prices.get(this.whichIndex));
            this.superMarketCashInFront =+ prices.get(this.whichIndex);
            custom.balance =  custom.balance + custom.bankomatCard.getBalace();

        } else if (choiceOfPayment.equalsIgnoreCase("Bar")) {
            custom.bar.balace += custom.moneyPouch;
            System.out.println(custom.bar.typOfPayment);
            custom.bar.soundOfPayment();
            custom.bar.paymentOutput(prices.get(this.whichIndex));
            this.superMarketCashInFront =+ prices.get(this.whichIndex);
            custom.moneyPouch = custom.bar.getBalace();

        }


    }
    @Override
    public String checkLiquidity (String choiceOfPayment , Person custom) {
        String choiceFor;
        if (choiceOfPayment.equalsIgnoreCase("Kreditcard")) {
            if (price[choiceOf] > custom.maxKredit) {
                System.out.println("would you like to change your payment to bill? max lvl over balance ?\n Ya / No");
                choiceFor = scanner.nextLine();
                if (choiceFor.equalsIgnoreCase("ya")){
                    choiceOfPayment = "Bill";
                }


            }
        } else if (choiceOfPayment.equalsIgnoreCase("Bankomarcard")) {
            if (price[choiceOf] > custom.balance) {
                System.out.println("would you like to change your payment to kreditcard? max lvl over balance ?\n Ya / No");
                choiceFor = scanner.nextLine();
                if (choiceFor.equalsIgnoreCase("ya")){
                    choiceOfPayment = "Kreditcard";
                }
            }

        } else if (choiceOfPayment.equalsIgnoreCase("Bill")) {
            choiceOfPayment = "Bill";

        } else if (choiceOfPayment.equalsIgnoreCase("Bar")) {
            if (price[choiceOf] > custom.moneyPouch) {
                System.out.println("would you like to change your payment to bankomatcard or kreditcard or bill? max lvl over balance ?");
                choiceFor = scanner.nextLine();
                if (choiceFor.equalsIgnoreCase("kreditcard")){
                    choiceOfPayment = "Kreditcard";
                } else  if (choiceFor.equalsIgnoreCase("bankomatcard")){
                    choiceOfPayment = "Bankomatcard";
                } else  if (choiceFor.equalsIgnoreCase("bill")){
                    choiceOfPayment = "Bill";
                }
            }

        }
        return choiceOfPayment;
    }
    @Override
    public void listFromArray() {

    }
    @Override
    public void searchInArray(String choiceToBuy) {

    }

}
