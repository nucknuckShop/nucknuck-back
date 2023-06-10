package com.shop.nucknuckshop.user.controller.request;

import lombok.Builder;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Builder
public class UserSearch {

    private static final int MAX_SIZE = 2000;

    @Builder.Default
    private Integer page = 1;

    @Builder.Default
    private Integer size = 10;

    public long getOffset(){
        return (long) (max(1, page) -1) * min(size, MAX_SIZE);
    }
}
