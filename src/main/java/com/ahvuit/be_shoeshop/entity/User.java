package com.ahvuit.be_shoeshop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("user")
public class User {
    @Id
    private String userId;
    private String email;
    private String password;
    private String utype;
    private boolean active;
}
