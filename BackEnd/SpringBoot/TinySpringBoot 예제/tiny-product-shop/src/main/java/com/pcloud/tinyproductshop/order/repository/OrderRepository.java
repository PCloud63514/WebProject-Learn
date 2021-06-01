package com.pcloud.tinyproductshop.order.repository;

import com.pcloud.tinyproductshop.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class OrderRepository implements IOrderRepository {
    private final EntityManager em;

    @Override
    public void save(Order order) {
        em.persist(order);
    }

    @Override
    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }
}
