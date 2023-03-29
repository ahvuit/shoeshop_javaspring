package com.ahvuit.be_shoeshop.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ahvuit.be_shoeshop.entity.MonthlyRevenueDetails;

public interface MonthlyRevenueDetailsRepository extends MongoRepository<MonthlyRevenueDetails, String> {
    Optional<MonthlyRevenueDetails> getMonthlyRevenueByYearAndMonth(Integer year, Integer month, String productId,
            String size);
}
