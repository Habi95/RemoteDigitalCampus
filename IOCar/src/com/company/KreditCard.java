package com.company;

public class KreditCard implements IPayment {
    double balace;
    String typOfPayment = "Kreditcard";

    public KreditCard(double balace) {
        this.balace = balace;
    }

    @Override
    public void soundOfPayment() {
        System.out.println("signature  ...");
    }

    @Override
    public void paymentInput(double input) {
        this.balace += input;
    }

    @Override
    public void paymentOutput(double output) {
        this.balace -= (output - (output/100*3));
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
