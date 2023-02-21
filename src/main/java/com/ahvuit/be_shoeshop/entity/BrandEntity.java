package com.ahvuit.be_shoeshop.entity;

import org.springframework.data.annotation.Id;

public class BrandEntity {
    @Id
    private String brandId;
    private String brandName;
    private String information;
    private String logo;

    public BrandEntity() {

    }

    public BrandEntity(String brandId, String brandName, String information, String logo) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.information = information;
        this.logo = logo;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

}
