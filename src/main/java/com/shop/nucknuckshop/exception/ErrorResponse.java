package com.shop.nucknuckshop.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ErrorResponse {

    private final Integer code;
    private final Map<String, String> validation;

    @Builder
    public ErrorResponse(@Nullable Integer code, Map<String, String> validation) {
        this.code = code;
        this.validation = validation != null ? validation : new HashMap<>();
    }
}
