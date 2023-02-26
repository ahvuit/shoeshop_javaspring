package com.ahvuit.be_shoeshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.models.AuthRequest;
import com.ahvuit.be_shoeshop.models.AuthResponse;
import com.ahvuit.be_shoeshop.models.User;
import com.ahvuit.be_shoeshop.repositories.UserRepository;

import io.jsonwebtoken.io.Decoders;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<AuthResponse> authenticate(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                        authRequest.getPassword()));
        return authentication.isAuthenticated() ? ResponseEntity.status(HttpStatus.OK).body(
                new AuthResponse(jwtService.generateToken(authRequest.getUsername())))
                : ResponseEntity.status(HttpStatus.OK).body(
                        null);
    }

    public ResponseEntity<ApiResult> login(AuthRequest authRequest) {

        Optional<User> foundProducts = userRepository.findByEmail(authRequest.getUsername());
        if (foundProducts.isPresent()) {
            return passwordEncoder.matches(authRequest.getPassword(), foundProducts.get().getPassword())
                    ? ResponseEntity.status(HttpStatus.OK).body(
                            new ApiResult(true, 200, "Login Successfully", foundProducts))
                    : ResponseEntity.status(HttpStatus.OK).body(
                            new ApiResult(false, 400, "Password is not Invalid", null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResult(false, 400, "User does not exist", null));

    }
}
