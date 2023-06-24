package com.shop.nucknuckshop.product.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Money {

    private Integer value;
    private String unit;

    public Money(Integer value) {
        this.value = value;
    }
}
