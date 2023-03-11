package com.ahvuit.be_shoeshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahvuit.be_shoeshop.entity.Rating;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.service.RatingService;

@RestController
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PutMapping("/api/rating")
    public ResponseEntity<ApiResult> rating(@RequestBody Rating rating) {
        return ratingService.rating(rating);
    }
}
