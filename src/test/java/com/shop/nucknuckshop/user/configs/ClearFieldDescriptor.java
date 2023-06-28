package com.shop.nucknuckshop.user.configs;

import org.springframework.restdocs.payload.FieldDescriptor;

public final class ClearFieldDescriptor extends FieldDescriptor {

    private ClearFieldDescriptor(String path) {
        super(path);
    }

    public static FieldDescriptor parameter(String parameter){
        return new ClearFieldDescriptor(parameter);
    }
}
