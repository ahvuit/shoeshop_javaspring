package com.ahvuit.be_shoeshop.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ahvuit.be_shoeshop.entity.ProductEntity;

@Document("product")
public class Product extends ProductEntity {

    @Id
    private String productId;

    public Product() {
    }

    public Product(String productId, String name, String description, Integer brandId, Integer categoryId,
            String productName,
            Float price, Float rate, Boolean productNew, Integer purchase, Integer stock, Boolean active, String image,
            Date createdDate, Date dateUpdated, Integer updateBy) {
        super(name, description, brandId, categoryId, productName, price, rate, productNew, purchase, stock, active,
                image, createdDate, dateUpdated, updateBy);
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    @Override
    public Boolean getActive() {
        // TODO Auto-generated method stub
        return super.getActive();
    }

    @Override
    public Integer getBrandId() {
        // TODO Auto-generated method stub
        return super.getBrandId();
    }

    @Override
    public Integer getCategoryId() {
        // TODO Auto-generated method stub
        return super.getCategoryId();
    }

    @Override
    public Date getCreatedDate() {
        // TODO Auto-generated method stub
        return super.getCreatedDate();
    }

    @Override
    public Date getDateUpdated() {
        // TODO Auto-generated method stub
        return super.getDateUpdated();
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return super.getDescription();
    }

    @Override
    public String getImage() {
        // TODO Auto-generated method stub
        return super.getImage();
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return super.getName();
    }

    @Override
    public Float getPrice() {
        // TODO Auto-generated method stub
        return super.getPrice();
    }

    @Override
    public String getProductName() {
        // TODO Auto-generated method stub
        return super.getProductName();
    }

    @Override
    public Boolean getProductNew() {
        // TODO Auto-generated method stub
        return super.getProductNew();
    }

    @Override
    public Integer getPurchase() {
        // TODO Auto-generated method stub
        return super.getPurchase();
    }

    @Override
    public Float getRate() {
        // TODO Auto-generated method stub
        return super.getRate();
    }

    @Override
    public Integer getStock() {
        // TODO Auto-generated method stub
        return super.getStock();
    }

    @Override
    public Integer getUpdateBy() {
        // TODO Auto-generated method stub
        return super.getUpdateBy();
    }

    @Override
    public void setActive(Boolean active) {
        // TODO Auto-generated method stub
        super.setActive(active);
    }

    @Override
    public void setBrandId(Integer brandId) {
        // TODO Auto-generated method stub
        super.setBrandId(brandId);
    }

    @Override
    public void setCategoryId(Integer categoryId) {
        // TODO Auto-generated method stub
        super.setCategoryId(categoryId);
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        // TODO Auto-generated method stub
        super.setCreatedDate(createdDate);
    }

    @Override
    public void setDateUpdated(Date dateUpdated) {
        // TODO Auto-generated method stub
        super.setDateUpdated(dateUpdated);
    }

    @Override
    public void setDescription(String description) {
        // TODO Auto-generated method stub
        super.setDescription(description);
    }

    @Override
    public void setImage(String image) {
        // TODO Auto-generated method stub
        super.setImage(image);
    }

    @Override
    public void setName(String name) {
        // TODO Auto-generated method stub
        super.setName(name);
    }

    @Override
    public void setPrice(Float price) {
        // TODO Auto-generated method stub
        super.setPrice(price);
    }

    @Override
    public void setProductName(String productName) {
        // TODO Auto-generated method stub
        super.setProductName(productName);
    }

    @Override
    public void setProductNew(Boolean productNew) {
        // TODO Auto-generated method stub
        super.setProductNew(productNew);
    }

    @Override
    public void setPurchase(Integer purchase) {
        // TODO Auto-generated method stub
        super.setPurchase(purchase);
    }

    @Override
    public void setRate(Float rate) {
        // TODO Auto-generated method stub
        super.setRate(rate);
    }

    @Override
    public void setStock(Integer stock) {
        // TODO Auto-generated method stub
        super.setStock(stock);
    }

    @Override
    public void setUpdateBy(Integer updateBy) {
        // TODO Auto-generated method stub
        super.setUpdateBy(updateBy);
    }

}
