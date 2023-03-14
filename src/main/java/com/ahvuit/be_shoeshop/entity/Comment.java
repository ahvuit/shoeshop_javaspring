package com.ahvuit.be_shoeshop.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("comment")
public class Comment {
    @Id
    private String cmtId;
    private String productId;
    private String userId;
    private String content;
    private String image;
    private Date createDate;
}
