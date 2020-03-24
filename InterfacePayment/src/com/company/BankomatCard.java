package com.company;

public class BankomatCard implements IPayment{
    double balace;
    String typOfPayment = "Bankomatcard";

    public BankomatCard(double balace) {
        this.balace = balace;
    }

    @Override
    public void soundOfPayment() {
        System.out.println("peep peep peep peep * * * *\n payment complete");
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
