package com.shop.nucknuckshop.order.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderNo is a Querydsl query type for OrderNo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QOrderNo extends BeanPath<OrderNo> {

    private static final long serialVersionUID = -637631116L;

    public static final QOrderNo orderNo = new QOrderNo("orderNo");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QOrderNo(String variable) {
        super(OrderNo.class, forVariable(variable));
    }

    public QOrderNo(Path<? extends OrderNo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderNo(PathMetadata metadata) {
        super(OrderNo.class, metadata);
    }

}

