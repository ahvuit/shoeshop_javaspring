package com.ahvuit.be_shoeshop.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ahvuit.be_shoeshop.entity.Sales;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.repositories.SalesRepository;

@Service
public class SalesService {

        @Autowired
        private SalesRepository salesRepository;

        public ResponseEntity<ApiResult> getAllSales() {
                try {
                        return ResponseEntity.status(HttpStatus.OK).body(
                                        new ApiResult(true, 200, "Query sales successfully",
                                                        salesRepository.findAll()));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

        public ResponseEntity<ApiResult> findById(String id) {
                try {
                        return checkStatusId(id) ? ResponseEntity.status(HttpStatus.OK).body(
                                        new ApiResult(true, 200, "Query sales successfully", salesRepository.findAll()))
                                        : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                                        new ApiResult(false, 401, "sales is not found", null));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

        public ResponseEntity<ApiResult> insertSales(Sales sales) {
                try {
                        sales.setStartDay(new Date());
                        sales.setCreatedDate(new Date());
                        return checkStatusName(sales.getSalesName())
                                        ? ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                                                        new ApiResult(false, 404, "Cannot insert new sales", null))
                                        : ResponseEntity.status(HttpStatus.OK).body(
                                                        new ApiResult(true, 200, "insert new sales successfully",
                                                                        salesRepository.save(sales)));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

        public ResponseEntity<ApiResult> updateSales(Sales newSales, String id) {
                try {
                        Optional<Sales> foundSales = salesRepository.findById(id);
                        if (foundSales.isPresent()) {
                                foundSales.get()
                                                .setSalesName((newSales.getSalesName() != null
                                                                || newSales.getSalesName().equals(""))
                                                                                ? newSales.getSalesName()
                                                                                : foundSales.get().getSalesName());
                                foundSales.get().setStartDay(
                                                newSales.getStartDay() != null ? newSales.getStartDay()
                                                                : foundSales.get().getStartDay());
                                foundSales.get()
                                                .setEndDay(newSales.getEndDay() != null ? newSales.getEndDay()
                                                                : foundSales.get().getEndDay());
                                foundSales.get().setContent(
                                                newSales.getContent() != null ? newSales.getContent()
                                                                : foundSales.get().getContent());
                                foundSales.get().setPercent(
                                                newSales.getPercent() != null ? newSales.getPercent()
                                                                : foundSales.get().getPercent());
                                foundSales.get()
                                                .setBanner(newSales.getBanner() != null ? newSales.getBanner()
                                                                : foundSales.get().getBanner());
                                salesRepository.save(foundSales.get());
                                return ResponseEntity.status(HttpStatus.OK).body(
                                                new ApiResult(true, 200, "Update sales successfully",
                                                                foundSales.get()));
                        }
                        return ResponseEntity.status(HttpStatus.OK).body(
                                        new ApiResult(false, 401, "sales is not found",
                                                        null));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }

        }

        public boolean checkStatusName(String name) {
                Optional<Sales> foundSales = salesRepository.findSalesBySalesName(name);
                return foundSales.isPresent();
        }

        public boolean checkStatusId(String id) {
                return salesRepository.existsById(id);
        }
}
