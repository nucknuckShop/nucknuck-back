package com.shop.nucknuckshop.exception;

import org.springframework.http.HttpStatus;

public class NoSuchUserException extends ApiException {

    private static final long serialVersionUID = -5016998539088851705L;

    public NoSuchUserException() {
        super(HttpStatus.NOT_FOUND);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
