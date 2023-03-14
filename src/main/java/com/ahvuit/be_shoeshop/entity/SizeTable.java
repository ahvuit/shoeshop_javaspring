package com.ahvuit.be_shoeshop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("sizeTable")
public class SizeTable {
    @Id
    private String sizeTableId;
    private Integer s38;
    private Integer s39;
    private Integer s40;
    private Integer s41;
    private Integer s42;
    private Integer s43;
    private Integer s44;
    private Integer s45;
    private Integer s46;
    private Integer s47;
    private Integer s48;
    private String productId;
}
