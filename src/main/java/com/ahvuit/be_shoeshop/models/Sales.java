package com.ahvuit.be_shoeshop.models;

import java.util.Date;

import com.ahvuit.be_shoeshop.entity.SalesEntity;

public class Sales extends SalesEntity {

    public Sales(String salesId, String salesName, String content, Double percent, Date startDay, Date endDay,
            Date createdDate) {
        super(salesId, salesName, content, percent, startDay, endDay, createdDate);
    }

    public Sales() {
    }

}
