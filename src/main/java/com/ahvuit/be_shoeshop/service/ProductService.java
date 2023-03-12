package com.ahvuit.be_shoeshop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ahvuit.be_shoeshop.entity.Brand;
import com.ahvuit.be_shoeshop.entity.Category;
import com.ahvuit.be_shoeshop.entity.Product;
import com.ahvuit.be_shoeshop.entity.SaleDetails;
import com.ahvuit.be_shoeshop.entity.Sales;
import com.ahvuit.be_shoeshop.entity.SizeTable;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.models.ProductModel;
import com.ahvuit.be_shoeshop.repositories.BrandRepository;
import com.ahvuit.be_shoeshop.repositories.ProductRepository;
import com.ahvuit.be_shoeshop.repositories.SaleDetailsRepository;
import com.ahvuit.be_shoeshop.repositories.SalesRepository;
import com.ahvuit.be_shoeshop.repositories.SizeTableRepository;
import com.ahvuit.be_shoeshop.repositories.CategoryRepository;

@Service
public class ProductService {

        // CRUD product service

        @Autowired
        private ProductRepository repository;
        @Autowired
        private SizeTableRepository sizeTableRepository;
        @Autowired
        private BrandRepository brandRepository;
        @Autowired
        private CategoryRepository categoryRepository;
        @Autowired
        private SalesRepository salesRepository;
        @Autowired
        private SaleDetailsRepository saleDetailsRepository;

        public ResponseEntity<ApiResult> getAllProducts() {
                try {
                        List<Product> products = repository.findAll();
                        List<ProductModel> lModels = new ArrayList<ProductModel>();

                        for (Product product : products) {
                                Sales sales = null;
                                Optional<Brand> brand = brandRepository.findById(product.getBrandId());
                                Optional<Category> category = categoryRepository.findById(product.getCategoryId());
                                SizeTable sizeTable = sizeTableRepository
                                                .getSizeTableByProductId(product.getProductId());
                                Optional<SaleDetails> salesDetails = saleDetailsRepository
                                                .getSaleDetailsByProductId(product.getProductId());
                                if (salesDetails.isPresent()) {
                                        Optional<Sales> findSales = salesRepository
                                                        .findById(salesDetails.get().getSalesId());
                                        if (findSales.isPresent()) {
                                                sales = findSales.get();
                                        }
                                }
                                ProductModel productResponse = new ProductModel(product.getProductId(),
                                                product.getName(),
                                                product.getDescription(), product.getBrandId(),
                                                product.getCategoryId(), product.getPrice(),
                                                product.getRate(), product.getProductNew(),
                                                product.getPurchase(), product.getStock(), product.getActive(),
                                                product.getImage(),
                                                product.getCreatedDate(), product.getDateUpdated(),
                                                product.getUpdateBy(), sizeTable,
                                                sales,
                                                brand.isPresent() ? brand.get().getBrandName() : "",
                                                category.isPresent() ? category.get().getCategoryName() : "");
                                lModels.add(productResponse);
                        }
                        return ResponseEntity.status(HttpStatus.OK).body(
                                        new ApiResult(true, 200, "Query product successfully", lModels));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

        public ResponseEntity<ApiResult> findById(String id) {
                try {
                        Optional<Product> foundProduct = repository.findById(id);
                        if (foundProduct.isPresent()) {
                                Product product = foundProduct.get();
                                Sales sales = null;
                                Optional<Brand> brand = brandRepository.findById(product.getBrandId());
                                Optional<Category> category = categoryRepository.findById(product.getCategoryId());
                                SizeTable sizeTable = sizeTableRepository
                                                .getSizeTableByProductId(product.getProductId());
                                Optional<SaleDetails> salesDetails = saleDetailsRepository
                                                .getSaleDetailsByProductId(product.getProductId());
                                if (salesDetails.isPresent()) {
                                        Optional<Sales> findSales = salesRepository
                                                        .findById(salesDetails.get().getSalesId());
                                        if (findSales.isPresent()) {
                                                sales = findSales.get();
                                        }
                                }
                                ProductModel productResponse = new ProductModel(product.getProductId(),
                                                product.getName(),
                                                product.getDescription(), product.getBrandId(),
                                                product.getCategoryId(), product.getPrice(),
                                                product.getRate(), product.getProductNew(),
                                                product.getPurchase(), product.getStock(), product.getActive(),
                                                product.getImage(),
                                                product.getCreatedDate(), product.getDateUpdated(),
                                                product.getUpdateBy(), sizeTable,
                                                sales,
                                                brand.isPresent() ? brand.get().getBrandName() : "",
                                                category.isPresent() ? category.get().getCategoryName() : "");
                                return ResponseEntity.status(HttpStatus.OK).body(
                                                new ApiResult(true, 200, "Query product successfully",
                                                                productResponse));
                        }
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                        new ApiResult(false, 404, "Cannot find product", null));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

        public ResponseEntity<ApiResult> insertProduct(Product newProduct) {
                // 2 products must not have the same name !
                try {
                        List<Product> foundProducts = repository.findByName(newProduct.getName());
                        if (foundProducts.isEmpty()) {
                                newProduct.setStock(0);
                                newProduct.setActive(true);
                                newProduct.setPurchase(0);
                                newProduct.setProductNew(true);
                                newProduct.setCreatedDate(new Date());
                                newProduct.setDateUpdated(new Date());
                                repository.save(newProduct);

                                SizeTable sizeTable = new SizeTable();
                                sizeTable.setProductId(newProduct.getProductId());
                                sizeTable.setS38(0);
                                sizeTable.setS39(0);
                                sizeTable.setS40(0);
                                sizeTable.setS41(0);
                                sizeTable.setS42(0);
                                sizeTable.setS43(0);
                                sizeTable.setS44(0);
                                sizeTable.setS45(0);
                                sizeTable.setS46(0);
                                sizeTable.setS47(0);
                                sizeTable.setS48(0);
                                sizeTableRepository.save(sizeTable);

                                Optional<Brand> brand = brandRepository.findById(newProduct.getBrandId());
                                Optional<Category> category = categoryRepository.findById(newProduct.getCategoryId());

                                ProductModel productResponse = new ProductModel(newProduct.getProductId(),
                                                newProduct.getName(),
                                                newProduct.getDescription(), newProduct.getBrandId(),
                                                newProduct.getCategoryId(), newProduct.getPrice(),
                                                newProduct.getRate(), newProduct.getProductNew(),
                                                newProduct.getPurchase(), newProduct.getStock(), newProduct.getActive(),
                                                newProduct.getImage(),
                                                newProduct.getCreatedDate(), newProduct.getDateUpdated(),
                                                newProduct.getUpdateBy(), sizeTable,
                                                brand.isPresent() ? brand.get().getBrandName() : "",
                                                category.isPresent() ? category.get().getCategoryName() : "");

                                return ResponseEntity.status(HttpStatus.OK).body(
                                                new ApiResult(true, 200, "insert new product successfully",
                                                                productResponse));
                        }
                        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                                        new ApiResult(false, 404, "product name is already", null));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }

        }

        public ResponseEntity<ApiResult> updateProduct(Product newProduct, String id) {
                try {
                        Product updatedProduct = repository.findById(id)
                                        .map(product -> {
                                                product.setName(newProduct.getName());
                                                product.setDescription(newProduct.getDescription());
                                                product.setBrandId(newProduct.getBrandId());
                                                product.setCategoryId(newProduct.getCategoryId());
                                                product.setName(newProduct.getName());
                                                product.setPrice(newProduct.getPrice());
                                                product.setRate(newProduct.getPrice());
                                                product.setProductNew(newProduct.getProductNew());
                                                product.setName(newProduct.getName());
                                                product.setActive(newProduct.getActive());
                                                product.setImage(newProduct.getImage());
                                                product.setDateUpdated(newProduct.getDateUpdated());
                                                product.setUpdateBy(newProduct.getUpdateBy());
                                                return repository.save(product);
                                        }).orElseGet(() -> {
                                                newProduct.setProductId(id);
                                                return repository.save(newProduct);
                                        });
                        return ResponseEntity.status(HttpStatus.OK).body(
                                        new ApiResult(true, 200, "Update Product successfully",
                                                        updatedProduct));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

        public ResponseEntity<ApiResult> deleteProduct(String id) {
                try {
                        boolean exists = repository.existsById(id);
                        if (exists) {
                                repository.deleteById(id);
                                return ResponseEntity.status(HttpStatus.OK).body(
                                                new ApiResult(true, 200, "Delete product successfully ",
                                                                null));
                        }
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                        new ApiResult(false, 404, "Cannot find product to delete ",
                                                        null));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

}
