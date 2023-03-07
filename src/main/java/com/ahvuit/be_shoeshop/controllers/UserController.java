package com.ahvuit.be_shoeshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahvuit.be_shoeshop.entity.User;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/api/getAllUsers")
    ResponseEntity<ApiResult> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/api/getUserDetails/{id}")
    ResponseEntity<ApiResult> findById(@PathVariable String id) {
        return userService.findById(id);
    }

    @PostMapping("/api/register")
    ResponseEntity<ApiResult> insertUser(@RequestBody User user) {
        // 2 products must not have the same name !
        return userService.insertUser(user);
    }

    @PutMapping("/api/updateUser/{id}")
    public ResponseEntity<ApiResult> updateUser(@RequestBody User user, @PathVariable String id) {
        return userService.updateUser(user, id);
    }
}