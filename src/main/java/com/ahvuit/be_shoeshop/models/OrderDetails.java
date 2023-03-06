package com.ahvuit.be_shoeshop.models;

import com.ahvuit.be_shoeshop.entity.OrderDetailsEntity;

public class OrderDetails extends OrderDetailsEntity {

    public OrderDetails() {
    }

    public OrderDetails(String orderId, String productId, String quantity, String size, Double price, String total) {
        super(orderId, productId, quantity, size, price, total);
    }

}