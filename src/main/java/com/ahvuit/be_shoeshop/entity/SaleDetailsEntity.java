package com.ahvuit.be_shoeshop.entity;

public class SaleDetailsEntity {
    private String salesId;
    private String productId;
    private Double salesPrice;
    private String updateBy;

    public SaleDetailsEntity() {

    }

    public SaleDetailsEntity(String salesId, String productId, Double salesPrice, String updateBy) {
        this.salesId = salesId;
        this.productId = productId;
        this.salesPrice = salesPrice;
        this.updateBy = updateBy;
    }

    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}
