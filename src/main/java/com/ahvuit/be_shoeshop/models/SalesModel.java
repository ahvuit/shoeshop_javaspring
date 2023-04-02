package com.ahvuit.be_shoeshop.models;

import java.util.Date;
import java.util.List;

import com.ahvuit.be_shoeshop.entity.Product;
import com.ahvuit.be_shoeshop.entity.Sales;

import lombok.Data;

@Data
public class SalesModel extends Sales {

    private List<Product> listProduct;
    private String updateBy;

    public SalesModel(String salesId, String updateBy, List<Product> listProduct) {
        super(salesId);
        this.listProduct = listProduct;
        this.updateBy = updateBy;
    }

    public SalesModel(String salesId, String salesName, String content, Double percent, String banner, Date startDay,
            Date endDay, Date createdDate) {
        super(salesId, salesName, content, percent, banner, startDay, endDay, createdDate);
    }

    public SalesModel() {
    }

}
