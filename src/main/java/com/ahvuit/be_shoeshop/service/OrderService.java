package com.ahvuit.be_shoeshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.models.Order;
import com.ahvuit.be_shoeshop.models.OrderDetails;
import com.ahvuit.be_shoeshop.models.OrderOrderDetails;
import com.ahvuit.be_shoeshop.models.Status;
import com.ahvuit.be_shoeshop.repositories.OrderDetailsRepository;
import com.ahvuit.be_shoeshop.repositories.OrderRepository;
import com.ahvuit.be_shoeshop.repositories.StatusRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private StatusRepository statusRepository;

    public ResponseEntity<ApiResult> getAllOrders() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResult(true, 200, "Query orders successfully", orderRepository.findAll()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }

    public ResponseEntity<ApiResult> findById(String id) {
        try {
            Optional<Order> foundOrder = orderRepository.findById(id);
            if (foundOrder.isPresent()) {
                List<OrderDetails> foundOrderDetails = orderDetailsRepository.getOrderDetailsByOrderId(id);
                OrderOrderDetails orderOrderDetails = new OrderOrderDetails(foundOrder.get(), foundOrderDetails);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ApiResult(true, 200, "Query order successfully", orderOrderDetails));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResult(false, 404, "Cannot find order", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }

    public ResponseEntity<ApiResult> insertOrder(OrderOrderDetails orders) {
        try {
            orderRepository.save(orders.getOrder());
            for (OrderDetails iDetails : orders.getListOrderDetails()) {
                iDetails.setOrderId(orders.getOrder().getOrderId());
                orderDetailsRepository.save(iDetails);
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResult(true, 200, "Successfully", orders));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }

    }

    public ResponseEntity<ApiResult> paymentOrder(Order order, String id) {
        try {
            Optional<Order> foundOrder = orderRepository.findById(id);
            if (foundOrder.isPresent()) {
                foundOrder.get().setPayment(true);
                foundOrder.get().setMomo(order.getMomo());
                orderRepository.save(foundOrder.get());
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ApiResult(true, 200, "Successful payment order", order));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResult(false, 404, "Cannot find order", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }

    public ResponseEntity<ApiResult> cancelOrder(String id) {
        try {
            Optional<Order> foundOrder = orderRepository.findById(id);
            List<Status> foundStatus = statusRepository.findAll();
            Status item = foundStatus.get(foundStatus.size() - 1);
            if (foundOrder.isPresent()) {
                foundOrder.get().setStatusId(item.getStatusId());
                orderRepository.save(foundOrder.get());
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ApiResult(true, 200, "Order has been canceled", foundOrder));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResult(false, 404, "Cannot find order", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }

    public ResponseEntity<ApiResult> updateOrder(Order order, String id) {
        try {
            Optional<Order> foundOrder = orderRepository.findById(id);
            if (foundOrder.isPresent()) {
                foundOrder.get().setStatusId(order.getStatusId());
                orderRepository.save(foundOrder.get());
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ApiResult(true, 200, "Order has been updated", order));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResult(false, 404, "Cannot find order", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }

}
