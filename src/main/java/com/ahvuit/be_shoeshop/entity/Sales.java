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
@Document("sales")
public class Sales {
    @Id
    private String salesId;
    private String salesName;
    private String content;
    private Double percent;
    private String banner;
    private Date startDay;
    private Date endDay;
    private Date createdDate;

}
