package com.ahvuit.be_shoeshop.models;

import com.ahvuit.be_shoeshop.entity.SaleDetailsEntity;

public class SaleDetails extends SaleDetailsEntity {

    public SaleDetails() {
    }

    public SaleDetails(String salesId, String productId, Double salesPrice, String updateBy) {
        super(salesId, productId, salesPrice, updateBy);
    }

}
