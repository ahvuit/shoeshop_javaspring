package com.ahvuit.be_shoeshop.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ahvuit.be_shoeshop.entity.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {
    Optional<Rating> getRatingByUserIdAndProductId(String userId, String productId);

}
