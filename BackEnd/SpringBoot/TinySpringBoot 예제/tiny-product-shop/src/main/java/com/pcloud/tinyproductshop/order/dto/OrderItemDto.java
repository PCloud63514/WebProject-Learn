package com.pcloud.tinyproductshop.order.dto;

import com.pcloud.tinyproductshop.order.domain.OrderItem;
import lombok.Data;

@Data
public class OrderItemDto {
    private String itemName;
    private int orderPrice;
    private int count;


    public OrderItemDto(OrderItem orderItem) {
        itemName = orderItem.getProduct().getName();
        orderPrice = orderItem.getOrderPrice();
        count = orderItem.getCount();
    }
}
