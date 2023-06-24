package com.shop.nucknuckshop.exception;

public class BadPasswordException extends RuntimeException{

    public BadPasswordException(String message) {
        super(message);
    }
}
