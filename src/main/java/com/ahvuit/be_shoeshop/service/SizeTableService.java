package com.ahvuit.be_shoeshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ahvuit.be_shoeshop.entity.Product;
import com.ahvuit.be_shoeshop.entity.SizeTable;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.repositories.ProductRepository;
import com.ahvuit.be_shoeshop.repositories.SizeTableRepository;

@Service
public class SizeTableService {
        @Autowired
        private SizeTableRepository sizeTableRepository;
        @Autowired
        private ProductRepository productRepository;

        public ResponseEntity<ApiResult> getAllSizeTables() {
                try {
                        return ResponseEntity.status(HttpStatus.OK).body(
                                        new ApiResult(true, 200, "Successfully", sizeTableRepository.findAll()));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

        public ResponseEntity<ApiResult> updateSizeTable(SizeTable sizeTable, String id) {
                try {
                        Optional<SizeTable> foundSizeTable = sizeTableRepository.findById(id);
                        if (foundSizeTable.isPresent()) {
                                foundSizeTable.get()
                                                .setS38(sizeTable.getS38() != null ? sizeTable.getS38()
                                                                : foundSizeTable.get().getS38());
                                foundSizeTable.get()
                                                .setS39(sizeTable.getS39() != null ? sizeTable.getS39()
                                                                : foundSizeTable.get().getS39());
                                foundSizeTable.get()
                                                .setS40(sizeTable.getS40() != null ? sizeTable.getS40()
                                                                : foundSizeTable.get().getS40());
                                foundSizeTable.get()
                                                .setS41(sizeTable.getS41() != null ? sizeTable.getS41()
                                                                : foundSizeTable.get().getS41());
                                foundSizeTable.get()
                                                .setS42(sizeTable.getS42() != null ? sizeTable.getS42()
                                                                : foundSizeTable.get().getS42());
                                foundSizeTable.get()
                                                .setS43(sizeTable.getS43() != null ? sizeTable.getS43()
                                                                : foundSizeTable.get().getS43());
                                foundSizeTable.get()
                                                .setS44(sizeTable.getS44() != null ? sizeTable.getS44()
                                                                : foundSizeTable.get().getS44());
                                foundSizeTable.get()
                                                .setS45(sizeTable.getS45() != null ? sizeTable.getS45()
                                                                : foundSizeTable.get().getS45());
                                foundSizeTable.get()
                                                .setS46(sizeTable.getS46() != null ? sizeTable.getS46()
                                                                : foundSizeTable.get().getS46());
                                foundSizeTable.get()
                                                .setS47(sizeTable.getS47() != null ? sizeTable.getS47()
                                                                : foundSizeTable.get().getS47());
                                foundSizeTable.get()
                                                .setS48(sizeTable.getS48() != null ? sizeTable.getS48()
                                                                : foundSizeTable.get().getS48());
                                sizeTableRepository.save(foundSizeTable.get());
                                Optional<Product> foundProduct = productRepository.findById(sizeTable.getProductId());
                                if (foundProduct.isPresent()) {
                                        Integer stock = foundSizeTable.get().getS38() + foundSizeTable.get().getS39()
                                                        + foundSizeTable.get().getS40() + foundSizeTable.get().getS41()
                                                        + foundSizeTable.get().getS42() + foundSizeTable.get().getS43()
                                                        + foundSizeTable.get().getS44() + foundSizeTable.get().getS45()
                                                        + foundSizeTable.get().getS46() + foundSizeTable.get().getS47()
                                                        + foundSizeTable.get().getS48();
                                        foundProduct.get().setStock(stock);
                                        productRepository.save(foundProduct.get());
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                        new ApiResult(true, 200, "Size table has been updated",
                                                                        foundSizeTable));
                                }
                        }
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                        new ApiResult(false, 404, "Cannot find size table", null));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

        public ResponseEntity<ApiResult> findById(String id) {
                try {
                        Optional<SizeTable> foundSizeTable = sizeTableRepository.findById(id);
                        if (foundSizeTable.isPresent()) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                                new ApiResult(true, 200, "Successfully", foundSizeTable));
                        }
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                        new ApiResult(false, 404, "Cannot find size table", null));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

}