package com.ahvuit.be_shoeshop.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("order")
public class Order {

    @Id
    private String orderId;
    private String statusId;
    private String userId;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private String note;
    private Double total;
    private Date bookingDate;
    private Date deliveryDate;
    private boolean payment;
    private String momo;
    private Date createDate;
}
