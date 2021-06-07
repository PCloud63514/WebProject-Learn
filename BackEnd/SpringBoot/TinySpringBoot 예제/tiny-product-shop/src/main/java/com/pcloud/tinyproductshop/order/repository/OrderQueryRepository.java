package com.pcloud.tinyproductshop.order.repository;

import com.pcloud.tinyproductshop.order.dto.OrderItemDto;
import com.pcloud.tinyproductshop.order.dto.OrderQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {
    private final EntityManager em;

    public List<OrderQueryDto> findOrderQueryDtos() {
        List<OrderQueryDto> result = findOrders();
        result.forEach(o -> {
            List<OrderItemDto> orderItems = findOrderItems(o.getOrderId());
            o.setOrderItems(orderItems);
        });
        return result;
    }

    private List<OrderItemDto> findOrderItems(Long orderId) {
        return em.createQuery(
                "select new com.pcloud.tinyproductshop.order.dto.OrderItemDto(oi.order.id, p.name, oi.orderPrice, oi.count)" +
                        " from OrderItem oi" +
                        " join oi.product p" +
                        " where oi.order.id = :orderId", OrderItemDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    public List<OrderQueryDto> findOrders() {
        return em.createQuery(
                "select new com.pcloud.tinyproductshop.order.dto.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                        " from Order o" +
                        " join o.member m" +
                        " join o.delivery d").getResultList();
    }

    public List<OrderQueryDto> findAllByDto_optimization() {
        List<OrderQueryDto> orders = findOrders();
        Map<Long, List<OrderItemDto>> orderItemMap = findOrderItemMap(toOrderIds(orders));
        orders.forEach(o->o.setOrderItems(orderItemMap.get(o.getOrderId())));

        return orders;
    }

    private Map<Long, List<OrderItemDto>> findOrderItemMap(List<Long> orderIds) {
        List<OrderItemDto> orderItems = em.createQuery(
                "select new com.pcloud.tinyproductshop.order.dto.OrderItemDto(oi.order.id, p.name, oi.orderPrice, oi.count)" +
                        " from OrderItem oi" +
                        " join oi.product p" +
                        " where oi.order.id in :orderIds", OrderItemDto.class)
                .setParameter("orderIds", orderIds)
                .getResultList();

        return orderItems.stream().collect(Collectors.groupingBy(OrderItemDto::getOrderId));
    }

    private List<Long> toOrderIds(List<OrderQueryDto> orders) {
        return orders.stream().map(o -> o.getOrderId()).collect(Collectors.toList());
    }
}
