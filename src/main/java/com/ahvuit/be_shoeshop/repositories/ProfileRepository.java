package com.ahvuit.be_shoeshop.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ahvuit.be_shoeshop.entity.Profile;

public interface ProfileRepository extends MongoRepository<Profile, String> {
    List<Profile> findByPhone(String phone);

    Optional<Profile> findByUserId(String userId);
}
