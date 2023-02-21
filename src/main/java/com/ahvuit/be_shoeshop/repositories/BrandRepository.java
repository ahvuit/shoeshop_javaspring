package com.ahvuit.be_shoeshop.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.ahvuit.be_shoeshop.models.Brand;

public interface BrandRepository extends MongoRepository<Brand, String> {
    List<Brand> findByBrandName(String brandName);
}
