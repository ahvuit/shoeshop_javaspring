package com.ahvuit.be_shoeshop.models;

import com.ahvuit.be_shoeshop.entity.Rating;

public class RatingModel extends Rating {

    public RatingModel() {
    }

    public RatingModel(String userId, String productId, Double rate) {
        super(userId, productId, rate);
    }

}
