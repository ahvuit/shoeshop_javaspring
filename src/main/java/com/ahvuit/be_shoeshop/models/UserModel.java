package com.ahvuit.be_shoeshop.models;

import com.ahvuit.be_shoeshop.entity.Profile;
import com.ahvuit.be_shoeshop.entity.User;

public class UserModel extends User {

    private Profile profile;

    private String token;

    public UserModel() {
    }

    public UserModel(String userId, String email, String password, String uType, boolean active) {
        super(userId, email, password, uType, active);
    }

    public UserModel(String userId, String email, String password, String uType, boolean active, Profile profile) {
        super(userId, email, password, uType, active);
        this.profile = profile;
    }

    public UserModel(String userId, String email, String password, String uType, boolean active, Profile profile,
            String token) {
        super(userId, email, password, uType, active);
        this.profile = profile;
        this.token = token;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
