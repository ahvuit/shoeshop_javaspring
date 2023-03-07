package com.ahvuit.be_shoeshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ahvuit.be_shoeshop.config.UserInfoUserDetails;
import com.ahvuit.be_shoeshop.entity.Profile;
import com.ahvuit.be_shoeshop.entity.User;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.models.UserModel;
import com.ahvuit.be_shoeshop.repositories.ProfileRepository;
import com.ahvuit.be_shoeshop.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private ProfileRepository profileRepository;

        public ResponseEntity<ApiResult> getAllUsers() {
                try {
                        return ResponseEntity.status(HttpStatus.OK).body(
                                        new ApiResult(true, 200, "Query user successfully", userRepository.findAll()));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

        public ResponseEntity<ApiResult> findById(String id) {
                try {
                        Optional<User> foundUser = userRepository.findById(id);
                        return foundUser.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                                        new ApiResult(true, 200, "Query user successfully", foundUser))
                                        : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                                        new ApiResult(false, 404, "Cannot find user", null));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

        public ResponseEntity<ApiResult> insertUser(User user) {
                try {
                        Optional<User> foundProducts = userRepository.findByEmail(user.getEmail());
                        if (foundProducts.isEmpty()) {

                                user.setPassword(passwordEncoder.encode(user.getPassword()));
                                user.setUType("USR"); // USR for normal user and ADM for Admin
                                user.setActive(true);
                                userRepository.save(user);

                                Profile profile = new Profile();
                                profile.setUserId(user.getUserId());
                                profile.setImageUrl("https://cdn-icons-png.flaticon.com/512/6596/6596121.png");
                                profileRepository.save(profile);

                                UserModel userResponse = new UserModel(user.getUserId(), user.getEmail(),
                                                user.getPassword(),
                                                user.getUType(),
                                                user.isActive(),
                                                profile);

                                return ResponseEntity.status(HttpStatus.OK).body(
                                                new ApiResult(true, 200, "insert new user successfully",
                                                                userResponse));
                        }
                        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                                        new ApiResult(false, 404, "Cannot insert new user", null));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

        public ResponseEntity<ApiResult> updateUser(User newUser, String id) {
                try {
                        User updatedUser = userRepository.findById(id)
                                        .map(user -> {
                                                user.setPassword(passwordEncoder.encode(newUser.getPassword()));
                                                user.setUType(newUser.getUType() == null ? "USR" : newUser.getUType());
                                                user.setActive(newUser.isActive());
                                                return userRepository.save(user);
                                        }).orElseGet(() -> {
                                                newUser.setUserId(id);
                                                return userRepository.save(newUser);
                                        });
                        return ResponseEntity.status(HttpStatus.OK).body(
                                        new ApiResult(true, 200, "Update Product successfully",
                                                        updatedUser));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<User> user = userRepository.findByEmail(username);
                return user.map(UserInfoUserDetails::new)
                                .orElseThrow(() -> new UsernameNotFoundException("user not found" + username));
        }

}
