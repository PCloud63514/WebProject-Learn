package com.pcloud.tinyproductshop.order.repository;

import com.pcloud.tinyproductshop.order.domain.Order;
import com.pcloud.tinyproductshop.order.domain.OrderSearch;

import java.util.List;

public interface IOrderRepository {
    public void save(Order order);
    public Order findOne(Long id);
    public List<Order> findAll(OrderSearch orderSearch);
}
