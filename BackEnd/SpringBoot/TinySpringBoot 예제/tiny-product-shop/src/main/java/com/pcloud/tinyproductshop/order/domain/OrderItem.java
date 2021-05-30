package com.pcloud.tinyproductshop.order.domain;

import com.pcloud.tinyproductshop.product.domain.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;
    private int orderPrice;
    private int count;
}
