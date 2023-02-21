package com.ahvuit.be_shoeshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.models.Profile;
import com.ahvuit.be_shoeshop.repositories.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public ResponseEntity<ApiResult> findById(String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResult(true, 200, "Query product successfully", profileRepository.findAll()));
    }

    public ResponseEntity<ApiResult> updateProfile(Profile newProfile, String id) {
        Profile updatedProfile = profileRepository.findById(id)
                .map(profile -> {
                    profile.setFirstName(newProfile.getFirstName());
                    profile.setLastName(newProfile.getLastName());
                    profile.setImageUrl(newProfile.getImageUrl());
                    profile.setPhone(newProfile.getPhone());
                    profile.setAddress(newProfile.getAddress());
                    return profileRepository.save(profile);
                }).orElseGet(() -> {
                    newProfile.setUserId(id);
                    return profileRepository.save(newProfile);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResult(true, 200, "Update Product successfully",
                        updatedProfile));
    }

}
