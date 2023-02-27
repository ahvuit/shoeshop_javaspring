package com.ahvuit.be_shoeshop.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ahvuit.be_shoeshop.models.Status;

public interface StatusRepository extends MongoRepository<Status, String> {
    Optional<Status> findByStatusName(String statusName);
}