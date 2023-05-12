package com.hamzacode.demo.payloads;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class ApiResponse {
    private Object data;
    private boolean success = true;

    public ApiResponse(Object data){
        this.data = data;
    }

    public ResponseEntity<ApiResponse> getResponse(HttpStatus httpStatus){
            return new ResponseEntity<>(this, httpStatus);
    }

}
