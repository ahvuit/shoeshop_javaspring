package com.ahvuit.be_shoeshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.models.AuthRequest;
import com.ahvuit.be_shoeshop.models.AuthResponse;
import com.ahvuit.be_shoeshop.models.User;
import com.ahvuit.be_shoeshop.service.AuthService;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/api/authenticate")
    public ResponseEntity<AuthResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }

    @PostMapping("/api/login")
    public ResponseEntity<ApiResult> login(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }
}
