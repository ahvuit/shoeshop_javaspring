package com.ahvuit.be_shoeshop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document("saleDetails")

public class SaleDetails {

    @Id
    private String id;
    private String salesId;
    private String productId;
    private Double salesPrice;
    private String updateBy;

    public SaleDetails(String salesId, String productId, Double salesPrice, String updateBy) {
        this.salesId = salesId;
        this.productId = productId;
        this.salesPrice = salesPrice;
        this.updateBy = updateBy;
    }

}
