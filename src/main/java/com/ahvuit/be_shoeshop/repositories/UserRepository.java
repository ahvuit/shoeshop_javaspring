package com.ahvuit.be_shoeshop.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ahvuit.be_shoeshop.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
