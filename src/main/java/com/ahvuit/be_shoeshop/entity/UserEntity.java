package com.ahvuit.be_shoeshop.entity;

import org.springframework.data.annotation.Id;

public class UserEntity {
    @Id
    private String userId;
    private String email;
    private String password;
    private String uType;
    private boolean active;

    public UserEntity() {
    }

    public UserEntity(String userId, String email, String password, String uType, boolean active) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.uType = uType;
        this.active = active;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUType() {
        return uType;
    }

    public void setUType(String uType) {
        this.uType = uType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
