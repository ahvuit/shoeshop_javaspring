package com.ahvuit.be_shoeshop.models;

import java.util.Date;

import com.ahvuit.be_shoeshop.entity.Order;

public class OrderModel extends Order {

    private String statusName;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public OrderModel() {
    }

    public OrderModel(String orderId, String statusId, String userId, String firstName, String lastName, String address,
            String phone, String email, String note, Double total, Date bookingDate, Date deliveryDate, boolean payment,
            String momo, Date createDate, String statusName) {
        super(orderId, statusId, userId, firstName, lastName, address, phone, email, note, total, bookingDate,
                deliveryDate, payment, momo, createDate);
        this.statusName = statusName;
    }

    @Override
    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        return super.equals(o);
    }

}
