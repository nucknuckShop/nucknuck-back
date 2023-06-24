package com.shop.nucknuckshop.product.domain;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "nns_product")
public class Product {

    @EmbeddedId
    private ProductId productId;

    @Embedded
    private Money money;
}
