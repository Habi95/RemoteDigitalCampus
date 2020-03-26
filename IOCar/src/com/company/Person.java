package com.company;

public  class Person implements IGreet{
    String name;
    String lastName;
    int age;
    double moneyPouch;
    double balance;
    double kreditbalance;
    double saleOnAccountBalance;
    double balanceWithoutKredit;
    double balanceWithKredit;
    double maxKredit;
    double maxmoneyStock ;

    KreditCard kreditCard = new KreditCard(kreditbalance);
    Bar bar = new Bar(moneyPouch);
    BankomatCard bankomatCard = new BankomatCard(balance);
    SaleOnAccount saleOnAccount = new SaleOnAccount();


    public Person(String name, String lastName, double money, int age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.moneyPouch = money;
        this.balance = this.moneyPouch * 3.3;
        this.kreditbalance = this.balance *2 ;
        this.balanceWithoutKredit = this.moneyPouch + this.balance;
        this.balanceWithKredit = this.balanceWithoutKredit + this.kreditbalance;
       this.maxKredit = balanceWithKredit;
        this.maxmoneyStock = balanceWithoutKredit;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getMoney() {
        return moneyPouch;
    }

    public void setMoney(double moneyPouch) {
        this.moneyPouch = moneyPouch;
    }

    public double getMoneyPouch() {
        return moneyPouch;
    }

    public double getBalance() {
        return balance;
    }

    public double getKreditbalance() {
        return kreditbalance;
    }

    @Override
    public void greeting(Person person) {

    }

    @Override
    public void leaveTalking(Person person) {

    }

    @Override
    public void thankSo(Person person) {

    }

    @Override
    public void conversationToBuy(Person person) {

    }

    @Override
    public void conversationToPay(Person person) {

    }
}
