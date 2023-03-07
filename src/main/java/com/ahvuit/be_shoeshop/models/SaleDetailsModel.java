package com.ahvuit.be_shoeshop.models;

import com.ahvuit.be_shoeshop.entity.SaleDetails;

public class SaleDetailsModel extends SaleDetails {

    public SaleDetailsModel() {
    }

    public SaleDetailsModel(String salesId, String productId, Double salesPrice, String updateBy) {
        super(salesId, productId, salesPrice, updateBy);
    }

}
