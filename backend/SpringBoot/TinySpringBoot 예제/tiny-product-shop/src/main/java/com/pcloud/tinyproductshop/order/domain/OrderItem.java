package com.pcloud.tinyproductshop.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pcloud.tinyproductshop.product.domain.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id @GeneratedValue
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Product product;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;
    private int orderPrice;
    private int count;

//    protected OrderItem() {
//    }

    //==생성 메서드==//
    public static OrderItem createOrderItem(Product product, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        product.removeStock(count);
        return orderItem;
    }

    //==비즈니스 로직==//
    public void cancel() {
        getProduct().addStock(count);
    }

    public int getToTalPrice() {
        return getOrderPrice() * count;
    }
}
