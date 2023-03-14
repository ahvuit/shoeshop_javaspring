package com.ahvuit.be_shoeshop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document("rating")
public class Rating {

    @Id
    private String id;
    private String userId;
    private String productId;
    private Double rate;

    public Rating(String userId, String productId, Double rate) {
        this.userId = userId;
        this.productId = productId;
        this.rate = rate;
    }

}
