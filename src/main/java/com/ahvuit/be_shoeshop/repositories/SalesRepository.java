package com.ahvuit.be_shoeshop.repositories;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.ahvuit.be_shoeshop.models.Sales;

public interface SalesRepository extends MongoRepository<Sales, String> {
    Optional<Sales> findSalesBySalesName(String salesName);
}
