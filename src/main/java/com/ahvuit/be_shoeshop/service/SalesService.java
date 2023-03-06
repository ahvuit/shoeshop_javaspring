package com.ahvuit.be_shoeshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.models.Sales;
import com.ahvuit.be_shoeshop.repositories.SalesRepository;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    public ResponseEntity<ApiResult> getAllSales() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResult(true, 200, "Query sales successfully", salesRepository.findAll()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }

    public ResponseEntity<ApiResult> findById(String id) {
        try {
            return checkStatusId(id) ? ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResult(true, 200, "Query sales successfully", salesRepository.findAll()))
                    : ResponseEntity.status(HttpStatus.OK).body(
                            new ApiResult(false, 400, "sales is not found", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }

    public ResponseEntity<ApiResult> insertSales(Sales sales) {
        try {
            return checkStatusName(sales.getSalesName()) ? ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
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
                if (checkStatusName(newSales.getSalesName())) {
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ApiResult(false, 404, "sales name is already",
                                    null));
                }
                foundSales.get().setSalesName(newSales.getSalesName());
                foundSales.get().setStartDay(newSales.getStartDay());
                foundSales.get().setEndDay(newSales.getEndDay());
                foundSales.get().setContent(newSales.getContent());
                foundSales.get().setPercent(newSales.getPercent());
                foundSales.get().setStartDay(newSales.getStartDay());
                salesRepository.save(foundSales.get());
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ApiResult(true, 200, "Update sales successfully",
                                foundSales.get()));
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResult(false, 400, "sales is not found",
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
