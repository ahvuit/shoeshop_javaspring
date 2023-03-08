package com.ahvuit.be_shoeshop.models;

import com.ahvuit.be_shoeshop.entity.OrderDetails;

public class OrderDetailsModel extends OrderDetails {

    public OrderDetailsModel() {
    }

    public OrderDetailsModel(String orderId, String productId, String quantity, String size, Double price,
            String total) {
        super(orderId, productId, quantity, size, price, total);
    }

}