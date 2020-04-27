package com.company.Database.repository;

import com.company.Database.models.DeliveryPlaces;
import com.company.controller.Delivery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryPlaceHandler {
    private DB_Connector db_connector;

    public DeliveryPlaceHandler(DB_Connector db_connector) {
        this.db_connector = db_connector;
    }

    public  void crateDeliveryPlaceList(Delivery delivery)
    {
        String sql = "SELECT delivery_places.id, delivery_places.place, delivery_places.delivery_price FROM `delivery_places`";
        ResultSet result = db_connector.fetchData(sql);
        if (result == null) {
            System.out.println("something go wrong");
            return;
        }

        try {
            DeliveryPlaces deliveryPlaces;
            while (result.next()) {
                int ID = result.getInt("id");
                String place = result.getString("place");
                double delivery_price = result.getDouble("delivery_price");

                deliveryPlaces = new DeliveryPlaces(ID,place,delivery_price);
                delivery.deliveryPlaces.add(deliveryPlaces);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            db_connector.closeConnection();
        }
    }

}
