package com.shop.nucknuckshop.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(ApiException e){
        ErrorResponse error = ErrorResponse.builder()
                .code(e.getStatusCode())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(e.getHttpStatus()).body(error);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Void> exceptionHandler(){
        return ResponseEntity.badRequest().body(null);
    }
}
