package com.company;

public class Bar implements IPayment {

    double balace;
    String typOfPayment = "Bar";

    public Bar(double balace) {
        this.balace = balace;
    }

    @Override
    public void soundOfPayment() {
        System.out.println("kram kram  here ..");
    }

    @Override
    public void paymentInput(double input) {
        this.balace += input;
    }

    @Override
    public void paymentOutput(double output) {
        this.balace -= output;
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
