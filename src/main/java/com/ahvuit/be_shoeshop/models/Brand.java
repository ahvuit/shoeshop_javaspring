package com.ahvuit.be_shoeshop.models;

import com.ahvuit.be_shoeshop.entity.BrandEntity;

public class Brand extends BrandEntity {

    public Brand() {
    }

    public Brand(String brandId, String brandName, String information, String logo) {
        super(brandId, brandName, information, logo);
    }

}
