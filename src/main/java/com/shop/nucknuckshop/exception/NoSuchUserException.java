package com.shop.nucknuckshop.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoSuchUserException extends RuntimeException {

    private static final long serialVersionUID = -5016998539088851705L;

    public NoSuchUserException() {
        super();
        log.info("No such user found");
    }
}
