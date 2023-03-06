package com.ahvuit.be_shoeshop.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ahvuit.be_shoeshop.models.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> getOrderByUserId(String userId);
}
