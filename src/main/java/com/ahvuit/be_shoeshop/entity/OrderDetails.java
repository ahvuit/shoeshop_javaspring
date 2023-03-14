package com.ahvuit.be_shoeshop.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("orderDetails")
public class OrderDetails {

    private String orderId;
    private String productId;
    private Integer quantity;
    private String size;
    private Double price;
    private Double total;

}
