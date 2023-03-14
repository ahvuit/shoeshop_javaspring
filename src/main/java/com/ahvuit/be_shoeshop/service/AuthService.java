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

import com.ahvuit.be_shoeshop.entity.Profile;
import com.ahvuit.be_shoeshop.entity.User;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.models.AuthRequest;
import com.ahvuit.be_shoeshop.models.AuthResponse;
import com.ahvuit.be_shoeshop.models.UserModel;
import com.ahvuit.be_shoeshop.repositories.ProfileRepository;
import com.ahvuit.be_shoeshop.repositories.UserRepository;

@Service
public class AuthService {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private JwtService jwtService;

        @Autowired
        private ProfileRepository profileRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        public ResponseEntity<AuthResponse> authenticate(AuthRequest authRequest) {
                try {
                        Authentication authentication = authenticationManager.authenticate(
                                        new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
                                                        authRequest.getPassword()));
                        return authentication.isAuthenticated() ? ResponseEntity.status(HttpStatus.OK).body(
                                        new AuthResponse(jwtService.generateToken(authRequest.getEmail())))
                                        : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                                        new AuthResponse(null));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new AuthResponse(null));
                }
        }

        public ResponseEntity<ApiResult> login(AuthRequest authRequest) {
                try {
                        Optional<User> foundProducts = userRepository.findByEmail(authRequest.getEmail());
                        if (foundProducts.isPresent()) {
                                String token = jwtService.generateToken(authRequest.getEmail());
                                Optional<Profile> foundProfile = profileRepository
                                                .findByUserId(foundProducts.get().getUserId());
                                UserModel user = new UserModel(foundProducts.get().getUserId(),
                                                foundProducts.get().getEmail(), foundProducts.get().getPassword(),
                                                foundProducts.get().getUtype(), foundProducts.get().isActive(),
                                                foundProfile.isPresent() ? foundProfile.get() : null, token);
                                return passwordEncoder.matches(authRequest.getPassword(),
                                                foundProducts.get().getPassword())
                                                                ? ResponseEntity.status(HttpStatus.OK).body(
                                                                                new ApiResult(true, 200,
                                                                                                "Login Successfully",
                                                                                                user))
                                                                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                                                                new ApiResult(false, 400,
                                                                                                "Password is not Invalid",
                                                                                                null));
                        }
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                        new ApiResult(false, 404, "User does not exist", null));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }

        }
}
