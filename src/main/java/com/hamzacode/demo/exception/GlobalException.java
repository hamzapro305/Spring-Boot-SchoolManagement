package com.hamzacode.demo.exception;

import com.hamzacode.demo.payloads.ApiErrorResponse;
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
    ResponseEntity<ApiErrorResponse> handler(HttpMessageNotReadableException ex){
        ApiErrorResponse resp = new ApiErrorResponse(ex.getMessage());
        return resp.getResponse(HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiErrorResponse> handler(MethodArgumentNotValidException ex){
        ApiErrorResponse resp = new ApiErrorResponse(ex.getFieldError().getDefaultMessage());
        return resp.getResponse(HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ApiErrorResponse> handler(ConstraintViolationException ex){
        ApiErrorResponse resp = new ApiErrorResponse(ex.getLocalizedMessage());
        return resp.getResponse(HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    ResponseEntity<ApiErrorResponse> handler(DuplicateKeyException ex){
        ApiErrorResponse resp = new ApiErrorResponse(ex.getMostSpecificCause().getMessage());
        return resp.getResponse(HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ResponseEntity<ApiErrorResponse> handler(MethodArgumentTypeMismatchException ex){
        ApiErrorResponse resp = new ApiErrorResponse(ex.getMessage());
        return resp.getResponse(HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(CustomException.class)
    ResponseEntity<ApiErrorResponse> handler(CustomException ex){
        ApiErrorResponse resp = new ApiErrorResponse(ex.getMessage());
        return resp.getResponse(HttpStatus.NOT_ACCEPTABLE);
    }

}
