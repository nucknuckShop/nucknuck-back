package com.shop.nucknuckshop.order.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderNo implements Serializable {

    @Column(name = "order_id")
    private Integer id;
}
