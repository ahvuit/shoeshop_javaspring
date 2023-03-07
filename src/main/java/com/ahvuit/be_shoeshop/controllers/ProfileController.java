package com.ahvuit.be_shoeshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahvuit.be_shoeshop.entity.Profile;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.service.ProfileService;

@RestController
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/api/getProfileDetails/{id}")
    ResponseEntity<ApiResult> findByUserId(@PathVariable String id) {
        return profileService.findByUserId(id);
    }

    @PutMapping("/api/updateProfile/{id}")
    public ResponseEntity<ApiResult> updateProfile(@RequestBody Profile profile, @PathVariable String id) {
        return profileService.updateProfile(profile, id);
    }

}
