package com.company;

public class Customer extends Person  {

    Car myCar;
    public Customer(String name, String lastName, double money, int age) {
        super(name, lastName, money, age);
    }

    @Override
    public void greeting(Person person) {

        System.out.println("Howdy my Friend " + person.getName());
    }

    @Override
    public void leaveTalking(Person person) {
        System.out.println("Have a nice Day " + person.getName() + " !");
    }

    @Override
    public void thankSo(Person person) {
        System.out.println("Thanks for your Consulting " +  person.getName() + " !");
    }

    @Override
    public void conversationToBuy(Person person) {
        System.out.print("I like to buy: ");
    }

    @Override
    public void conversationToPay(Person person) {
        System.out.print("I pay by: ");
    }
}
