package com.ahvuit.be_shoeshop.models;

import java.util.Date;

import com.ahvuit.be_shoeshop.entity.Product;
import com.ahvuit.be_shoeshop.entity.SizeTable;

public class ProductModel extends Product {

    private SizeTable sizeTable;

    public ProductModel() {
    }

    public ProductModel(String productId, String name, String description, Integer brandId, Integer categoryId,
            Float price,
            Float rate, Boolean productNew, Integer purchase, Integer stock, Boolean active, String image,
            Date createdDate, Date dateUpdated, Integer updateBy) {
        super(productId, name, description, brandId, categoryId, price, rate, productNew, purchase, stock, active,
                image, createdDate, dateUpdated, updateBy);
    }

    public ProductModel(String productId, String name, String description, Integer brandId, Integer categoryId,
            Float price,
            Float rate, Boolean productNew, Integer purchase, Integer stock, Boolean active, String image,
            Date createdDate, Date dateUpdated, Integer updateBy, SizeTable sizeTable) {
        super(productId, name, description, brandId, categoryId, price, rate, productNew, purchase, stock, active,
                image, createdDate, dateUpdated, updateBy);
        this.sizeTable = sizeTable;
    }

    public SizeTable getSizeTable() {
        return sizeTable;
    }

    public void setSizeTable(SizeTable sizeTable) {
        this.sizeTable = sizeTable;
    }

}
