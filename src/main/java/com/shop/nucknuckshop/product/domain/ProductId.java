package com.shop.nucknuckshop.product.domain;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class ProductId implements Serializable {

    @Column(name = "product_id")
    private int id;
}
