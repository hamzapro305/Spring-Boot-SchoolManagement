package com.hamzacode.demo.Exception;

import com.hamzacode.demo.payloads.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<ApiResponse> handler(HttpMessageNotReadableException ex){
        return new ResponseEntity<ApiResponse>(
                new ApiResponse(ex.getMessage(), false),
                HttpStatus.NOT_ACCEPTABLE
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handler(MethodArgumentNotValidException ex){
        return new ResponseEntity<ApiResponse>(
                new ApiResponse(ex.getFieldError().getDefaultMessage(), false),
                HttpStatus.NOT_ACCEPTABLE
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ApiResponse> handler(ConstraintViolationException ex){
        return new ResponseEntity<ApiResponse>(
                new ApiResponse(ex.getLocalizedMessage(), false),
                HttpStatus.NOT_ACCEPTABLE
        );
    }

    @ExceptionHandler(DuplicateKeyException.class)
    ResponseEntity<ApiResponse> handler(DuplicateKeyException ex){
        return new ResponseEntity<>(
                new ApiResponse(ex.getMostSpecificCause().getMessage(), false),
                HttpStatus.NOT_ACCEPTABLE
        );
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ResponseEntity<?> handler(MethodArgumentTypeMismatchException ex){
        return new ResponseEntity<>(
                new ApiResponse(ex.getMessage(), false),
                HttpStatus.NOT_ACCEPTABLE
        );
    }

}
