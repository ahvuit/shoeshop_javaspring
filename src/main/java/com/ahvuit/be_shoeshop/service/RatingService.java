package com.ahvuit.be_shoeshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ahvuit.be_shoeshop.entity.Rating;
import com.ahvuit.be_shoeshop.entity.Status;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.repositories.OrderDetailsRepository;
import com.ahvuit.be_shoeshop.repositories.OrderRepository;
import com.ahvuit.be_shoeshop.repositories.RatingRepository;
import com.ahvuit.be_shoeshop.repositories.StatusRepository;

import com.ahvuit.be_shoeshop.entity.Order;
import com.ahvuit.be_shoeshop.entity.OrderDetails;

@Service
public class RatingService {
        @Autowired
        private RatingRepository ratingRepository;
        @Autowired
        private OrderRepository orderRepository;
        @Autowired
        private OrderDetailsRepository orderDetailsRepository;
        @Autowired
        private StatusRepository statusRepository;

        public ResponseEntity<ApiResult> rating(Rating newRating) {
                try {
                        Optional<Rating> insertOrUpdateRating = ratingRepository
                                        .getRatingByUserIdAndProductId(newRating.getUserId(), newRating.getProductId());
                        if (!insertOrUpdateRating.isPresent()) {
                                List<OrderDetails> listOd = orderDetailsRepository
                                                .getOrderDetailsByProductId(newRating.getProductId());
                                List<Order> listOrder = orderRepository.getOrderByUserId(newRating.getUserId());

                                Optional<String> statusId = listOrder.stream()
                                                .filter(o -> o.getUserId().equals(newRating.getUserId()))
                                                .filter(o -> listOd.stream()
                                                                .filter(od -> od.getOrderId().equals(o.getOrderId()))
                                                                .anyMatch(od -> od.getProductId()
                                                                                .equals(newRating.getProductId())))
                                                .findFirst()
                                                .map(Order::getStatusId);

                                Optional<Status> status = statusRepository
                                                .findById(statusId.isPresent() ? statusId.get() : "");
                                if (status.isPresent() && status.get().getStatusName().equals("Giao thành công")) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                        new ApiResult(true, 200, "Successfully",
                                                                        ratingRepository.save(newRating)));
                                }
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                                new ApiResult(false, 400, "You cannot rate this product",
                                                                null));
                        }
                        insertOrUpdateRating.get().setRate(newRating.getRate());
                        return ResponseEntity.status(HttpStatus.OK).body(
                                        new ApiResult(true, 200, "Successfully",
                                                        ratingRepository.save(insertOrUpdateRating.get())));

                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }
}
