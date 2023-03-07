package com.ahvuit.be_shoeshop.entity;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("sales")
public class Sales {
    @Id
    private String salesId;
    private String salesName;
    private String content;
    private Double percent;
    private Date startDay;
    private Date endDay;
    private Date createdDate;

    public Sales() {

    }

    public Sales(String salesId, String salesName, String content, Double percent, Date startDay, Date endDay,
            Date createdDate) {
        this.salesId = salesId;
        this.salesName = salesName;
        this.content = content;
        this.percent = percent;
        this.startDay = startDay;
        this.endDay = endDay;
        this.createdDate = createdDate;
    }

    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

}
