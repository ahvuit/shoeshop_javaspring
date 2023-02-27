package com.ahvuit.be_shoeshop.entity;

import org.springframework.data.annotation.Id;

public class StatusEntity {

    @Id
    private String statusId;
    private String statusName;

    public StatusEntity() {
    }

    public StatusEntity(String statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
