package com.ahvuit.be_shoeshop.models;

import java.util.Date;

import com.ahvuit.be_shoeshop.entity.OrderEntity;

public class Order extends OrderEntity {

    public Order() {
    }

    public Order(String orderId, String statusId, String userId, String name, String address, String phone,
            String email, String note, Double total, Date bookingDate, Date deliveryDate, boolean payment,
            String momo) {
        super(orderId, statusId, userId, name, address, phone, email, note, total, bookingDate, deliveryDate, payment,
                momo);
    }

}
