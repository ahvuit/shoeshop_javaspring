package com.ahvuit.be_shoeshop.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ahvuit.be_shoeshop.entity.OrderDetails;

public interface OrderDetailsRepository extends MongoRepository<OrderDetails, String> {
    List<OrderDetails> getOrderDetailsByOrderId(String orderId);

    List<OrderDetails> getOrderDetailsByProductId(String productId);
}
