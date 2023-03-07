package com.ahvuit.be_shoeshop.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ahvuit.be_shoeshop.models.SaleDetails;

public interface SaleDetailsRepository extends MongoRepository<SaleDetails, String> {
    Optional<SaleDetails> getSaleDetailsBySalesId(String salesId);

    Optional<SaleDetails> getSaleDetailsByProductId(String productId);
}
