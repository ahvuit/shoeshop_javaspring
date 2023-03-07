package com.ahvuit.be_shoeshop.models;

import com.ahvuit.be_shoeshop.entity.Profile;

public class ProfileModel extends Profile {

    public ProfileModel() {
    }

    public ProfileModel(String profileId, String firstName, String lastName, String address, String phone,
            String imageUrl,
            String userId) {
        super(profileId, firstName, lastName, address, phone, imageUrl, userId);
    }

}
