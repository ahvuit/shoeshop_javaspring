package com.ahvuit.be_shoeshop.models;

import java.util.Date;

import com.ahvuit.be_shoeshop.entity.Product;
import com.ahvuit.be_shoeshop.entity.Sales;
import com.ahvuit.be_shoeshop.entity.SizeTable;

public class ProductModel extends Product {

    private SizeTable sizeTable;
    private Sales sales;
    private String brandName;
    private String categoryName;

    public SizeTable getSizeTable() {
        return sizeTable;
    }

    public void setSizeTable(SizeTable sizeTable) {
        this.sizeTable = sizeTable;
    }

    public Sales getSales() {
        return sales;
    }

    public void setSales(Sales sales) {
        this.sales = sales;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ProductModel() {
    }

    public ProductModel(String productId, String name, String description, String brandId, String categoryId,
            Float price, Float rate, Boolean productNew, Integer purchase, Integer stock, Boolean active, String image,
            Date createdDate, Date dateUpdated, Integer updateBy, SizeTable sizeTable, Sales sales, String brandName,
            String categoryName) {
        super(productId, name, description, brandId, categoryId, price, rate, productNew, purchase, stock, active,
                image, createdDate, dateUpdated, updateBy);
        this.sizeTable = sizeTable;
        this.sales = sales;
        this.brandName = brandName;
        this.categoryName = categoryName;
    }

    public ProductModel(String productId, String name, String description, String brandId, String categoryId,
            Float price, Float rate, Boolean productNew, Integer purchase, Integer stock, Boolean active, String image,
            Date createdDate, Date dateUpdated, Integer updateBy, SizeTable sizeTable, String brandName,
            String categoryName) {
        super(productId, name, description, brandId, categoryId, price, rate, productNew, purchase, stock, active,
                image, createdDate, dateUpdated, updateBy);
        this.sizeTable = sizeTable;
        this.brandName = brandName;
        this.categoryName = categoryName;
    }

    public ProductModel(String productId, String name, String description, String brandId, String categoryId,
            Float price,
            Float rate, Boolean productNew, Integer purchase, Integer stock, Boolean active, String image,
            Date createdDate, Date dateUpdated, Integer updateBy) {
        super(productId, name, description, brandId, categoryId, price, rate, productNew, purchase, stock, active,
                image, createdDate, dateUpdated, updateBy);
    }

}
