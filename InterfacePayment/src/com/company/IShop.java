package com.company;

public interface IShop {
    public void addStore();
    public void goShopping(Customer customer);
    public void typeToPay(Person custom);
    public String checkLiquidity(String choiceOfPayment, Person custom);
    public void listFromArray();
    public void searchInArray(String choiceToBuy);
}
