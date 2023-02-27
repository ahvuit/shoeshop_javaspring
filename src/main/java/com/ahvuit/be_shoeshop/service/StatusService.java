package com.ahvuit.be_shoeshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.models.Status;
import com.ahvuit.be_shoeshop.repositories.StatusRepository;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public ResponseEntity<ApiResult> getAllStatus() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResult(true, 200, "Query status successfully", statusRepository.findAll()));
    }

    public ResponseEntity<ApiResult> findById(String id) {
        Optional<Status> foundStatus = statusRepository.findById(id);
        return foundStatus.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                new ApiResult(true, 200, "Query status successfully", statusRepository.findAll()))
                : ResponseEntity.status(HttpStatus.OK).body(
                        new ApiResult(false, 400, "status is not found", null));
    }

    public ResponseEntity<ApiResult> insertStatus(Status status) {
        Optional<Status> foundStatus = statusRepository.findByStatusName(status.getStatusName());
        return foundStatus.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(
                new ApiResult(true, 200, "insert new status successfully",
                        statusRepository.save(status)))
                : ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ApiResult(false, 404, "Cannot insert new status", null));
    }

    public ResponseEntity<ApiResult> updateStatus(Status newStatus, String id) {
        Optional<Status> foundStatus = statusRepository.findById(id);
        if (foundStatus.isPresent()) {
            if (checkStatusName(newStatus.getStatusName())) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ApiResult(false, 404, "statusName is already",
                                null));
            }
            foundStatus.get().setStatusName(newStatus.getStatusName());
            statusRepository.save(foundStatus.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResult(true, 200, "Update status successfully",
                            foundStatus.get()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResult(false, 400, "status is not found",
                        null));

    }

    public ResponseEntity<ApiResult> deleteStatus(String id) {
        if (checkStatusId(id)) {
            statusRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResult(true, 200, "Delete product successfully ",
                            null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiResult(false, 404, "Cannot find product to delete ",
                        null));
    }

    public boolean checkStatusName(String name) {
        Optional<Status> foundStatus = statusRepository.findByStatusName(name);
        return foundStatus.isPresent();
    }

    public boolean checkStatusId(String id) {
        return statusRepository.existsById(id);
    }

}
