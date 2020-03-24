package com.company;

public class ShopOwner extends Person {


    public ShopOwner(String name, String lastName, double money, int age) {
        super(name, lastName, money, age);
    }

    @Override
    public void greeting(Person person) {
        System.out.println("Welcome to my Shop "   + person.getName());
        //super.greeting(person);
    }

    @Override
    public void leaveTalking(Person person) {
        System.out.println("See ya sonn, have a nice day " + person.getName());

    }

    @Override
    public void thankSo(Person person) {
        System.out.println("Thanks for your buy " + person.getName());
    }

    @Override
    public void conversationToBuy(Person person) {
        System.out.println("What you prefer from to buy? " + person.getName());
    }

    @Override
    public void conversationToPay(Person person) {
        System.out.print("\nthat will be: ");

    }

    //    @Override
//    public void greeting(Person person) {
//        System.out.println(" " + person);
//
//    }
//
//    @Override
//    public void leaveTalking(Person person) {
//
//    }
//
//    @Override
//    public void thankSo(Person person) {
//
//    }
//
//    @Override
//    public void conversationToBuy(Person person) {
//
//    }
//
//    @Override
//    public void conversationToPay(Person person) {
//
//    }
}
