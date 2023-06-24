package com.shop.nucknuckshop.exception;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UnsupportedCustomException extends RuntimeException{

    private final ErrorCases errorCases;
    private final Map<String, String> validation = new ConcurrentHashMap<>();

    public UnsupportedCustomException(ErrorCases errorCases) {
        this.errorCases = errorCases;
    }

    public UnsupportedCustomException(ErrorCases errorCases, String fieldName, String message) {
        this.errorCases = errorCases;
        this.validation.put(fieldName, message);
    }
}
