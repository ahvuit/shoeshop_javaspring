package com.ahvuit.be_shoeshop.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ahvuit.be_shoeshop.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByEmail(String email);
}
