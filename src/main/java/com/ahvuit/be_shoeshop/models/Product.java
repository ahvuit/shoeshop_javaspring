package com.ahvuit.be_shoeshop.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ahvuit.be_shoeshop.entity.ProductEntity;

@Document("product")
public class Product extends ProductEntity {

    public Product() {
    }

    public Product(String productId, String name, String description, Integer brandId, Integer categoryId, Float price,
            Float rate, Boolean productNew, Integer purchase, Integer stock, Boolean active, String image,
            Date createdDate, Date dateUpdated, Integer updateBy) {
        super(productId, name, description, brandId, categoryId, price, rate, productNew, purchase, stock, active,
                image, createdDate, dateUpdated, updateBy);
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return super.getName();
    }

    @Override
    public String getProductId() {
        // TODO Auto-generated method stub
        return super.getProductId();
    }

}
