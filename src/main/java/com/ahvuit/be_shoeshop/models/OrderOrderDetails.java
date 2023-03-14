package com.ahvuit.be_shoeshop.models;

import java.util.List;

import com.ahvuit.be_shoeshop.entity.OrderDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderOrderDetails {
    private OrderModel orderModel;
    private List<OrderDetails> listOrderDetails;
}
