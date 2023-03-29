package com.ahvuit.be_shoeshop.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ahvuit.be_shoeshop.entity.Order;
import com.ahvuit.be_shoeshop.entity.OrderDetails;
import com.ahvuit.be_shoeshop.entity.Status;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.models.OrderModel;
import com.ahvuit.be_shoeshop.models.OrderOrderDetails;
import com.ahvuit.be_shoeshop.repositories.MonthlyRevenueDetailsRepository;
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
    @Autowired
    private MonthlyRevenueDetailsRepository monthlyRevenueDetailsRepository;

    public ResponseEntity<ApiResult> getAllOrders() {
        try {

            List<Order> listOrder = orderRepository.findAll();
            List<OrderOrderDetails> list = new ArrayList<OrderOrderDetails>();
            for (Order order : listOrder) {
                Optional<Status> foundStatus = statusRepository.findById(order.getStatusId());
                OrderOrderDetails orderOrderDetails = new OrderOrderDetails();
                OrderModel orderModel = new OrderModel(order.getOrderId(), order.getStatusId(),
                        order.getUserId(), order.getFirstName(), order.getLastName(),
                        order.getAddress(),
                        order.getPhone(), order.getEmail(), order.getNote(),
                        order.getTotal(), order.getBookingDate(),
                        order.getDeliveryDate(), order.isPayment(),
                        order.getMomo(), order.getCreateDate(),
                        foundStatus.isPresent() ? foundStatus.get().getStatusName() : "");
                List<OrderDetails> foundOrderDetails = orderDetailsRepository
                        .getOrderDetailsByOrderId(order.getOrderId());
                orderOrderDetails.setListOrderDetails(foundOrderDetails);
                orderOrderDetails.setOrderModel(orderModel);
                list.add(orderOrderDetails);
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResult(true, 200, "Query orders successfully", list));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }

    public ResponseEntity<ApiResult> findById(String id) {
        try {
            Optional<Order> foundOrder = orderRepository.findById(id);

            if (foundOrder.isPresent()) {
                Optional<Status> foundStatus = statusRepository.findById(foundOrder.get().getStatusId());
                OrderModel orderResponse = new OrderModel(foundOrder.get().getOrderId(), foundOrder.get().getStatusId(),
                        foundOrder.get().getUserId(), foundOrder.get().getFirstName(), foundOrder.get().getLastName(),
                        foundOrder.get().getAddress(),
                        foundOrder.get().getPhone(), foundOrder.get().getEmail(), foundOrder.get().getNote(),
                        foundOrder.get().getTotal(), foundOrder.get().getBookingDate(),
                        foundOrder.get().getDeliveryDate(), foundOrder.get().isPayment(),
                        foundOrder.get().getMomo(), foundOrder.get().getCreateDate(),
                        foundStatus.isPresent() ? foundStatus.get().getStatusName() : "");
                List<OrderDetails> foundOrderDetails = orderDetailsRepository.getOrderDetailsByOrderId(id);
                OrderOrderDetails orderOrderDetails = new OrderOrderDetails(orderResponse, foundOrderDetails);
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

            Double total = 0.0;
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            Integer year = cal.get(Calendar.YEAR);
            Integer month = cal.get(Calendar.MONTH);

            cal.add(Calendar.DATE, 3);

            List<Status> status = statusRepository.findAll();
            Status item = status.get(0);

            orders.getOrderModel().setDeliveryDate(cal.getTime());
            orders.getOrderModel().setPayment(false);
            orders.getOrderModel().setMomo(null);
            orders.getOrderModel().setStatusId(item.getStatusId());
            orders.getOrderModel().setStatusName(item.getStatusName());
            orders.getOrderModel().setBookingDate(date);
            orders.getOrderModel().setCreateDate(date);

            Order order = new Order(orders.getOrderModel().getOrderId(), orders.getOrderModel().getStatusId(),
                    orders.getOrderModel().getUserId(), orders.getOrderModel().getFirstName(),
                    orders.getOrderModel().getLastName(), orders.getOrderModel().getAddress(),
                    orders.getOrderModel().getPhone(), orders.getOrderModel().getEmail(),
                    orders.getOrderModel().getNote(), orders.getOrderModel().getTotal(),
                    orders.getOrderModel().getBookingDate(), orders.getOrderModel().getDeliveryDate(), false,
                    null, orders.getOrderModel().getCreateDate());
            orderRepository.save(order);

            for (OrderDetails iDetails : orders.getListOrderDetails()) {
                iDetails.setOrderId(order.getOrderId());
                iDetails.setTotal(iDetails.getPrice() * iDetails.getQuantity());
                total += iDetails.getTotal();
                orderDetailsRepository.save(iDetails);
            }

            orders.getOrderModel().setOrderId(order.getOrderId());
            orders.getOrderModel().setTotal(total);
            order.setTotal(total);
            orderRepository.save(order);

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

    public ResponseEntity<ApiResult> findByUserId(String id) {
        try {
            List<Order> listOrder = orderRepository.getOrderByUserId(id);
            List<OrderOrderDetails> list = new ArrayList<OrderOrderDetails>();
            for (Order order : listOrder) {
                Optional<Status> foundStatus = statusRepository.findById(order.getStatusId());
                OrderOrderDetails orderOrderDetails = new OrderOrderDetails();
                OrderModel orderModel = new OrderModel(order.getOrderId(), order.getStatusId(),
                        order.getUserId(), order.getFirstName(), order.getLastName(),
                        order.getAddress(),
                        order.getPhone(), order.getEmail(), order.getNote(),
                        order.getTotal(), order.getBookingDate(),
                        order.getDeliveryDate(), order.isPayment(),
                        order.getMomo(), order.getCreateDate(),
                        foundStatus.isPresent() ? foundStatus.get().getStatusName() : "");
                List<OrderDetails> foundOrderDetails = orderDetailsRepository
                        .getOrderDetailsByOrderId(order.getOrderId());
                orderOrderDetails.setListOrderDetails(foundOrderDetails);
                orderOrderDetails.setOrderModel(orderModel);
                list.add(orderOrderDetails);
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResult(true, 200, "Query order successfully", list));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }

}
