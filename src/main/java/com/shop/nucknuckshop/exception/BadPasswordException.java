package com.shop.nucknuckshop.exception;

import org.springframework.http.HttpStatus;

public class BadPasswordException extends ApiException{

    private static final long serialVersionUID = 4678176946928639845L;

    public BadPasswordException() {
        super(HttpStatus.CONFLICT);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.CONFLICT.value();
    }
}
