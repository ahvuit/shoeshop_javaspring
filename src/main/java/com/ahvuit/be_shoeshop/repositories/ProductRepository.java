package com.ahvuit.be_shoeshop.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ahvuit.be_shoeshop.entity.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByName(String name);
}