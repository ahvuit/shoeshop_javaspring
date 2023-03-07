package com.ahvuit.be_shoeshop.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("order")
public class Order {

    @Id
    private String orderId;
    private String statusId;
    private String userId;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String note;
    private Double total;
    private Date bookingDate;
    private Date deliveryDate;
    private boolean payment;
    private String momo;

    public Order() {
    }

    public Order(String orderId, String statusId, String userId, String name, String address, String phone,
            String email, String note, Double total, Date bookingDate, Date deliveryDate, boolean payment,
            String momo) {
        this.orderId = orderId;
        this.statusId = statusId;
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.note = note;
        this.total = total;
        this.bookingDate = bookingDate;
        this.deliveryDate = deliveryDate;
        this.payment = payment;
        this.momo = momo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    public String getMomo() {
        return momo;
    }

    public void setMomo(String momo) {
        this.momo = momo;
    }
}
