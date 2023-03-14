package com.ahvuit.be_shoeshop.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("product")
public class Product {

    @Id
    private String productId;
    private String name;
    private String description;
    private String brandId;
    private String categoryId;
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
}