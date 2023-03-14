package com.ahvuit.be_shoeshop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("profile")
public class Profile {

    @Id
    private String profileId;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String imageUrl;
    private String userId;

}
