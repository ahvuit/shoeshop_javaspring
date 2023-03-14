package com.ahvuit.be_shoeshop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("brand")
public class Brand {
    @Id
    private String brandId;
    private String brandName;
    private String information;
    private String logo;

}
