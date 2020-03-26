package com.company;

import java.io.IOException;

public interface IShop {
    public void addStore();
    public void goShopping(Customer customer) throws IOException;
    public void typeToPay(Person custom);
    public String checkLiquidity(String choiceOfPayment, Person custom);
    public void listFromArray();
    public void searchInArray(String choiceToBuy);
}
