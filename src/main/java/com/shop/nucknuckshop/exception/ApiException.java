package com.shop.nucknuckshop.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public abstract class ApiException extends RuntimeException{

    private final HttpStatus httpStatus;
    private final Map<String, String> validation = new ConcurrentHashMap<>();

    protected ApiException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    protected ApiException(HttpStatus httpStatus, String field, String message) {
        this.httpStatus = httpStatus;
        this.validation.put(field, message);
    }

    public abstract int getStatusCode();
}
