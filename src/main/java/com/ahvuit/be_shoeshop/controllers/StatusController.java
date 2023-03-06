package com.ahvuit.be_shoeshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.models.Status;
import com.ahvuit.be_shoeshop.service.StatusService;

@RestController
public class StatusController {
    @Autowired
    private StatusService statusService;

    @GetMapping("/api/getAllStatus")
    ResponseEntity<ApiResult> getAllStatus() {
        return statusService.getAllStatus();
    }

    @GetMapping("/api/getStatusDetails/{id}")
    ResponseEntity<ApiResult> findById(@PathVariable String id) {
        return statusService.findById(id);
    }

    @PostMapping("/api/insertStatus")
    ResponseEntity<ApiResult> insertStatus(@RequestBody Status status) {
        // 2 products must not have the same name !
        return statusService.insertStatus(status);
    }

    @PutMapping("/api/updateStatus/{id}")
    public ResponseEntity<ApiResult> updateStatus(@RequestBody Status status, @PathVariable String id) {
        return statusService.updateStatus(status, id);
    }

    @DeleteMapping("/api/deleteStatus/{id}")
    public ResponseEntity<ApiResult> deleteStatus(@PathVariable String id) {
        return statusService.deleteStatus(id);
    }
}