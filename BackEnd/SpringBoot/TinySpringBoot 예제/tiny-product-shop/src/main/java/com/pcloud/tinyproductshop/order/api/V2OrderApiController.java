package com.pcloud.tinyproductshop.order.api;

import com.pcloud.tinyproductshop.member.domain.Address;
import com.pcloud.tinyproductshop.order.domain.Order;
import com.pcloud.tinyproductshop.order.domain.OrderSearch;
import com.pcloud.tinyproductshop.order.domain.OrderStatus;
import com.pcloud.tinyproductshop.order.repository.OrderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequestMapping("api/v2/order")
@RestController
@RequiredArgsConstructor
public class V2OrderApiController {
    private final OrderRepository orderRepository;

    @GetMapping("list")
    public List<OrderDto> orders() {
        /*
         * Order 2건 조회
         * */
        List<Order> orders = orderRepository.findAll(new OrderSearch());
        /*
         * 이 상태에서는 n + 1 문제가 발생한다.
         * Order 조회에 1
         * Order 내부 LAZY Loading 대상이 2개 이므로 N=2 즉
         * Order 조회 1건 + LAZY 대상 N=2 + 조회된 정보 갯수 2 로 도합 5번의 쿼리가 발생한다.
         * Order가 조회된 건 수는 2개이며, 내부의 Member와 Delivery는 LAZY Loading이므로 각 건마다 한번씩 쿼리를 날린다.
         * */
        return orders.stream()
                .map(OrderDto::new)
                .collect(toList());
    }

    @Data
    static class OrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public OrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
        }
    }
}
