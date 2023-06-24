package com.shop.nucknuckshop.user.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class Password {

    @Column(name = "password")
    private String value;

    public Password(String value) {
        this.value = value;
    }

    public boolean match(String password) {
        return StringUtils.equals(password, this.value);
    }
}
