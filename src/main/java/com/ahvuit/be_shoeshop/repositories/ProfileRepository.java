package com.ahvuit.be_shoeshop.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ahvuit.be_shoeshop.models.Profile;

public interface ProfileRepository extends MongoRepository<Profile, String> {
    List<Profile> findByPhone(String phone);
}
