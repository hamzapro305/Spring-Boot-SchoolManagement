package com.hamzacode.demo.payloads;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class ApiErrorResponse {
    private String message;
    private boolean success = false;

    public ApiErrorResponse(String message) {
        this.message = message;
    }

    public ResponseEntity<ApiErrorResponse> getResponse(HttpStatus httpStatus){
        return new ResponseEntity<>(this, httpStatus);
    }
}
