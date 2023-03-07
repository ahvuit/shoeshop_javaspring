package com.ahvuit.be_shoeshop.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("product")
public class Product {

    @Id
    private String productId;
    private String name;
    private String description;
    private Integer brandId;
    private Integer categoryId;
    private Float price;
    private Float rate;
    private Boolean productNew;
    private Integer purchase;
    private Integer stock;
    private Boolean active;
    private String image;
    private Date createdDate;
    private Date dateUpdated;
    private Integer updateBy;

    public Product() {
    }

    public Product(String productId, String name, String description, Integer brandId, Integer categoryId,
            Float price, Float rate, Boolean productNew, Integer purchase, Integer stock, Boolean active, String image,
            Date createdDate, Date dateUpdated, Integer updateBy) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.price = price;
        this.rate = rate;
        this.productNew = productNew;
        this.purchase = purchase;
        this.stock = stock;
        this.active = active;
        this.image = image;
        this.createdDate = createdDate;
        this.dateUpdated = dateUpdated;
        this.updateBy = updateBy;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Boolean getProductNew() {
        return productNew;
    }

    public void setProductNew(Boolean productNew) {
        this.productNew = productNew;
    }

    public Integer getPurchase() {
        return purchase;
    }

    public void setPurchase(Integer purchase) {
        this.purchase = purchase;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
}