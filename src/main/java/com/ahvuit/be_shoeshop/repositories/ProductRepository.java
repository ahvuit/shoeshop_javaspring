package com.ahvuit.be_shoeshop.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ahvuit.be_shoeshop.models.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByName(String name);
}