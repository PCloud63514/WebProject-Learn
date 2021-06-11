package com.pcloud.tinyproductshop.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pcloud.tinyproductshop.order.domain.OrderItem;
import lombok.Data;

@Data
public class OrderItemDto {
    @JsonIgnore
    private Long orderId;
    private String itemName;
    private int orderPrice;
    private int count;

    public OrderItemDto(Long orderId, String itemName, int orderPrice, int count) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public OrderItemDto(OrderItem orderItem) {
        itemName = orderItem.getProduct().getName();
        orderPrice = orderItem.getOrderPrice();
        count = orderItem.getCount();
    }
}
