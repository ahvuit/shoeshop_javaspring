package com.ahvuit.be_shoeshop.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("monthlyRevenueDetails")
public class MonthlyRevenueDetails {

    Integer year;
    Integer month;
    String productId;
    String size;
    Double sellNumber;
    Double turnover;
}
