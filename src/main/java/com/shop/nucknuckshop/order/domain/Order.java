package com.shop.nucknuckshop.order.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "nns_order")
public class Order {

    @EmbeddedId
    private OrderNo orderNo;
}
