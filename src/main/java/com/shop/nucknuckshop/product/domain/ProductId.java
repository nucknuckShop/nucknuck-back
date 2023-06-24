package com.shop.nucknuckshop.product.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProductId implements Serializable {

    @Column(name = "product_id")
    private int id;
}
