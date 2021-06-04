package com.pcloud.tinyproductshop.order.api;

import com.pcloud.tinyproductshop.order.domain.Order;
import com.pcloud.tinyproductshop.order.domain.OrderSearch;
import com.pcloud.tinyproductshop.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * xToOne(ManyToOne, OenToOne) 성능 최적화 목표
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RequestMapping("api/v1/order")
@RestController
@RequiredArgsConstructor
public class V1OrderApiController {
    private final OrderRepository orderRepository;

    @GetMapping("list")
    public List<Order> orders() {
        List<Order> all = orderRepository.findAll(new OrderSearch());
        for(Order order : all) {
            order.getMember().getName(); //Lazy 강제 초기화
            order.getDelivery().getAddress(); //Lazy 강제 초기화
        }
        return all;
    }
}
