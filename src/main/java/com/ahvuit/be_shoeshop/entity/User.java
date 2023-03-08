package com.ahvuit.be_shoeshop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user")
public class User {
    @Id
    private String userId;
    private String email;
    private String password;
    private String utype;
    private boolean active;

    public User() {
    }

    public User(String userId, String email, String password, String utype, boolean active) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.utype = utype;
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
        return utype;
    }

    public void setUType(String utype) {
        this.utype = utype;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
