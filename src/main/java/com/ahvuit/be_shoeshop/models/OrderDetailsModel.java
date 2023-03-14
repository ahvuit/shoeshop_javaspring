package com.ahvuit.be_shoeshop.models;

import com.ahvuit.be_shoeshop.entity.OrderDetails;

public class OrderDetailsModel extends OrderDetails {

    public OrderDetailsModel() {
    }

    public OrderDetailsModel(String orderId, String productId, Integer quantity, String size, Double price,
            Double total) {
        super(orderId, productId, quantity, size, price, total);
    }

}