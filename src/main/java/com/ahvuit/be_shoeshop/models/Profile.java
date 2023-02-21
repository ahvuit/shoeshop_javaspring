package com.ahvuit.be_shoeshop.models;

import com.ahvuit.be_shoeshop.entity.ProfileEntity;

public class Profile extends ProfileEntity {

    public Profile() {
    }

    public Profile(String profileId, String firstName, String lastName, String address, String phone, String imageUrl,
            String userId) {
        super(profileId, firstName, lastName, address, phone, imageUrl, userId);
    }

}
