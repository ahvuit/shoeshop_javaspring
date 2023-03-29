package com.ahvuit.be_shoeshop.models;

import com.ahvuit.be_shoeshop.entity.MonthlyRevenueDetails;

public class MonthlyRevenueDetailsModel extends MonthlyRevenueDetails {

    public MonthlyRevenueDetailsModel() {
    }

    public MonthlyRevenueDetailsModel(Integer year, Integer month, String productId, String size, Double sellNumber,
            Double turnover) {
        super(year, month, productId, size, sellNumber, turnover);
    }
}
