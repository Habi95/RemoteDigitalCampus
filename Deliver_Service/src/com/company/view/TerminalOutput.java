package com.company.view;

import com.company.Database.models.DeliveryPlaces;
import com.company.Database.models.Dishes;
import com.company.Database.models.Restaurant;
import com.company.Database.models.User;

import java.util.ArrayList;

public class TerminalOutput {

    public TerminalOutput() {
    }

    public void outPutStringLanding(String outPutString) {
        System.out.println("");
        System.out.println(outPutString);
    }

    public void outPutString (String outPut) {
        System.out.println(outPut);
    }

    /*
    for(){
    <checkBox>dish[i].name</checkBox>
    }

     */
    public void printMenu(Restaurant restaurant, String choice) {

        switch (choice.toUpperCase()) {
            case "VS":
                for (int j = 0; j < restaurant.dischesList.size(); j++) {

                    if (restaurant.dischesList.get(j).getDishTyp().equalsIgnoreCase(restaurant.getDishTypeStarter())) {

                        String dish = String.valueOf(restaurant.dischesList.get(j));
                        outPutStringLanding(dish);
                        outPutString("Zutaten:");
                        for (int i = 0; i < restaurant.dischesList.get(j).dishIngridients.size(); i++) {

                            outPutString(restaurant.dischesList.get(j).dishIngridients.get(i).getName());

                        }

                    }

                }
                break;
            case "HS":
                for (int j = 0; j < restaurant.dischesList.size(); j++) {

                    if (restaurant.dischesList.get(j).getDishTyp().equalsIgnoreCase(restaurant.getDishTypeMain())) {

                        String dish = String.valueOf(restaurant.dischesList.get(j));
                        outPutStringLanding(dish);
                        outPutString("Zutaten:");
                        for (int i = 0; i < restaurant.dischesList.get(j).dishIngridients.size(); i++) {

                            outPutString(restaurant.dischesList.get(j).dishIngridients.get(i).getName());

                        }

                    }
                }
                break;
            case "ALL":
                for (int j = 0; j < restaurant.dischesList.size(); j++) {
                    outPutStringLanding(String.valueOf(restaurant.dischesList.get(j)));
                    outPutString("Zutaten:");
                    for (int i = 0; i < restaurant.dischesList.get(j).dishIngridients.size(); i++) {
                        outPutString(
                                restaurant.dischesList.get(j).dishIngridients.get(i).getName()
                        );
                    }
                }
                break;

                default: outPutStringLanding("try again");


        }
    }

    public void printOrder (ArrayList<Dishes> orderDish, int orderCount) {

        outPutString(String.valueOf(orderDish.get(orderCount)));
        outPutString("Zutaten:");
        orderDish.get(orderCount).dishIngridients.forEach(ingridients -> System.out.println(ingridients.getId() + " - " + ingridients.getName()));
    }


    public void printDeliveryPlaceInfo(ArrayList<DeliveryPlaces> deliveryPlaces, User user) {

        for (int i = 0; i < deliveryPlaces.size(); i++) {
            if (deliveryPlaces.get(i).getPlace().equalsIgnoreCase(user.getPlace())) {
                outPutString(String.valueOf(deliveryPlaces.get(i)));

            }
        }
    }

    public void printDeliveryPlaces (ArrayList<DeliveryPlaces> deliveryPlaces) {
        outPutString("Wir liefern: ");
        outPutString("ID - ORT - Preis in €");
        for (DeliveryPlaces deliveryPlace : deliveryPlaces) {
            outPutString(deliveryPlace.getId() + " - " + deliveryPlace.getPlace() + " - " + deliveryPlace.getPrice());
        }
    }
}
