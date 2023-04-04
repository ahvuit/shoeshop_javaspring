package com.ahvuit.be_shoeshop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ahvuit.be_shoeshop.entity.Product;
import com.ahvuit.be_shoeshop.entity.SaleDetails;
import com.ahvuit.be_shoeshop.entity.Sales;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.models.SalesModel;
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
            List<SaleDetails> saleDetails = saleDetailsRepository.getSaleDetailsBySalesId(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResult(true, 200, "Query Successfully", saleDetails));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }

    public ResponseEntity<ApiResult> insertSalesDetails(SalesModel salesModel) {
        try {

            Optional<Sales> foundSales = salesRepository.findById(salesModel.getSalesId());
            if (foundSales.isPresent()) {
                List<SaleDetails> litsResponse = new ArrayList<SaleDetails>();
                for (Product product : salesModel.getListProduct()) {
                    Optional<Product> foundProduct = productRepository.findById(product.getProductId());
                    if (foundProduct.isPresent()) {
                        Double price = foundProduct.get().getPrice()
                                - foundProduct.get().getPrice() * foundSales.get().getPercent() / 100;
                        SaleDetails saleDetails = new SaleDetails(foundSales.get().getSalesId(),
                                foundProduct.get().getProductId(), price, salesModel.getUpdateBy());
                        saleDetailsRepository.save(saleDetails);
                        litsResponse.add(saleDetails);
                    }
                }

                return ResponseEntity.status(HttpStatus.OK).body(
                        new ApiResult(true, 200, "insert Successfully",
                                litsResponse));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, "cannot insert new sale details",
                            null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }

    void insertItemSalesDetails(Product product, SalesModel salesModel, Sales sales, List<SaleDetails> listResponse) {
        Double price = product.getPrice()
                - product.getPrice() * sales.getPercent() / 100;
        SaleDetails saleDetails = new SaleDetails(sales.getSalesId(),
                product.getProductId(), price, salesModel.getUpdateBy());
        saleDetailsRepository.save(saleDetails);
        listResponse.add(saleDetails);
    }

    public ResponseEntity<ApiResult> getAllSaleDetailsActive() {
        try {
            Date date = new Date();
            List<SaleDetails> listSD = new ArrayList<SaleDetails>();
            List<Sales> listSales = salesRepository.findAll();

            for (Sales sales : listSales) {
                if ((sales.getEndDay().compareTo(date) > 0 || sales.getEndDay().compareTo(date) == 0)
                        && sales.getStartDay().compareTo(date) < 0) {
                    List<SaleDetails> saleDetails = saleDetailsRepository.getSaleDetailsBySalesId(sales.getSalesId());
                    listSD.addAll(saleDetails);
                }
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResult(true, 200, "Query Successfully", listSD));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }

    public ResponseEntity<ApiResult> getAllSaleDetailsComingSoon() {
        try {
            Date date = new Date();
            List<SaleDetails> listSD = new ArrayList<SaleDetails>();
            List<Sales> listSales = salesRepository.findAll();

            for (Sales sales : listSales) {
                if (sales.getStartDay().compareTo(date) > 0) {
                    List<SaleDetails> saleDetails = saleDetailsRepository.getSaleDetailsBySalesId(sales.getSalesId());
                    listSD.addAll(saleDetails);
                }
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResult(true, 200, "Query Successfully", listSD));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }

    public Date findEndDayForProductInSales(Product product) {
        List<Sales> salesList = salesRepository.findAll();
        List<SaleDetails> salesDetailsList = saleDetailsRepository.findAll();

        Optional<Sales> salesOptional = salesList.stream()
                .filter(sales -> salesDetailsList.stream()
                        .filter(salesDetails -> salesDetails.getSalesId().equals(sales.getSalesId()))
                        .anyMatch(salesDetails -> salesDetails.getProductId().equals(product.getProductId())))
                .findFirst();

        if (salesOptional.isPresent()) {
            Sales sales = salesOptional.get();
            return sales.getEndDay();
        }

        return null;
    }

    public boolean isExpired(Date endDay) {
        Date now = new Date();

        return now.before(endDay);
    }

    public ResponseEntity<ApiResult> deleteSaleDetails(String id) {
        try {
            List<SaleDetails> saleDetails = saleDetailsRepository.getSaleDetailsBySalesId(id);
            if (!saleDetails.isEmpty()) {
                for (SaleDetails element : saleDetails) {
                    saleDetailsRepository.delete(element);
                }
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

    public ResponseEntity<ApiResult> deleteSaleDetailsByList(List<SaleDetails> listSalesDetails) {
        try {
            for (SaleDetails element : listSalesDetails) {
                saleDetailsRepository.delete(element);
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResult(true, 200, "Delete Successfully ",
                            null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }
}
