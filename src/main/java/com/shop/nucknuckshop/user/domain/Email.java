package com.shop.nucknuckshop.user.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Email implements Serializable {

    private String value;

    public Email(String value) {
        this.value = value;
    }
}
