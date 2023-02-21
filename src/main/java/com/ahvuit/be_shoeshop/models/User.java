package com.ahvuit.be_shoeshop.models;

import com.ahvuit.be_shoeshop.entity.UserEntity;

public class User extends UserEntity {

    private Profile profile;

    public User() {
    }

    public User(Profile profile) {
        this.profile = profile;
    }

    public User(String userId, String email, String password, String uType, boolean active) {
        super(userId, email, password, uType, active);
    }

    public User(String userId, String email, String password, String uType, boolean active, Profile profile) {
        super(userId, email, password, uType, active);
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

}
