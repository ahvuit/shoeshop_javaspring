package com.ahvuit.be_shoeshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ahvuit.be_shoeshop.entity.Product;
import com.ahvuit.be_shoeshop.entity.SaleDetails;
import com.ahvuit.be_shoeshop.entity.Sales;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.repositories.ProductRepository;
import com.ahvuit.be_shoeshop.repositories.SaleDetailsRepository;
import com.ahvuit.be_shoeshop.repositories.SalesRepository;

@Service
public class SaleDetailsService {
    @Autowired
    private SaleDetailsRepository saleDetailsRepository;
    @Autowired
    private SalesRepository salesRepository;
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<ApiResult> findById(String id) {
        try {
            Optional<SaleDetails> saleDetails = saleDetailsRepository.getSaleDetailsBySalesId(id);
            return saleDetails.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResult(true, 200, "Query Successfully", saleDetails))
                    : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                            new ApiResult(false, 400, "not found", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }

    public ResponseEntity<ApiResult> insertSalesDetails(SaleDetails saleDetails) {
        try {
            Optional<Sales> foundSales = salesRepository.findById(saleDetails.getSalesId());
            Optional<Product> foundProduct = productRepository.findById(saleDetails.getProductId());
            if (foundProduct.isPresent() && foundSales.isPresent()) {
                Double price = foundProduct.get().getPrice()
                        - foundProduct.get().getPrice() * foundSales.get().getPercent() / 100;
                saleDetails.setSalesPrice(price);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ApiResult(true, 200, "insert Successfully",
                                saleDetailsRepository.save(saleDetails)));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, "cannot insert new sale details",
                            saleDetailsRepository.save(saleDetails)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }

    public ResponseEntity<ApiResult> deleteSaleDetails(String id) {
        try {
            Optional<SaleDetails> saleDetails = saleDetailsRepository.getSaleDetailsByProductId(id);
            if (saleDetails.isPresent()) {
                saleDetailsRepository.deleteById(saleDetails.get().getId());
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ApiResult(true, 200, "Delete Successfully ",
                                null));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResult(false, 404, "Cannot delete ",
                            null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }
}
