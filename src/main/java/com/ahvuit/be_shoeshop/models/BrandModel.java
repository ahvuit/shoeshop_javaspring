package com.ahvuit.be_shoeshop.models;

import com.ahvuit.be_shoeshop.entity.Brand;

public class BrandModel extends Brand {

    public BrandModel() {
    }

    public BrandModel(String brandId, String brandName, String information, String logo) {
        super(brandId, brandName, information, logo);
    }

}
