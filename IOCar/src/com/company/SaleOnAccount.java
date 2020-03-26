package com.company;

public class SaleOnAccount implements IPayment {
        double balace;
    String typOfPayment = "Bill";

    @Override
    public void soundOfPayment() {
        System.out.println("signature ..  take in the bag");
    }

    @Override
    public void paymentInput(double input) {
        this.balace += input;
    }

    @Override
    public void paymentOutput(double output) {
        this.balace -= output ;
    }


    public double getBalace() {
        return balace;
    }

    public void setBalace(double balace) {
        this.balace = balace;
    }

    public String getTypOfPayment() {
        return typOfPayment;
    }
}
