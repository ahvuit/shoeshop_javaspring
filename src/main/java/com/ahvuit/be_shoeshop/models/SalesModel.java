package com.ahvuit.be_shoeshop.models;

import java.util.Date;

import com.ahvuit.be_shoeshop.entity.Sales;

public class SalesModel extends Sales {

    public SalesModel(String salesId, String salesName, String content, Double percent, String banner, Date startDay,
            Date endDay, Date createdDate) {
        super(salesId, salesName, content, percent, banner, startDay, endDay, createdDate);
    }

    public SalesModel() {
    }

}
