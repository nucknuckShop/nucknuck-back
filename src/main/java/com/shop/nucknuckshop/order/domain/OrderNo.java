package com.shop.nucknuckshop.order.domain;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class OrderNo implements Serializable {

    @Column(name = "order_id")
    private Integer id;
}
