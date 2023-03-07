package com.ahvuit.be_shoeshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahvuit.be_shoeshop.entity.SizeTable;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.service.SizeTableService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class SizeTableController {
    @Autowired
    private SizeTableService sizeTableService;

    @GetMapping("/api/getAllSizeTables")
    ResponseEntity<ApiResult> getAllSizeTables(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
        return sizeTableService.getAllSizeTables();
    }

    @GetMapping("/api/getSizeTableDetails/{id}")
    ResponseEntity<ApiResult> findById(@PathVariable String id) {
        return sizeTableService.findById(id);
    }

    @PutMapping("/api/updateSizeTable/{id}")
    public ResponseEntity<ApiResult> updateSizeTable(@RequestBody SizeTable sizeTable, @PathVariable String id) {
        return sizeTableService.updateSizeTable(sizeTable, id);
    }
}