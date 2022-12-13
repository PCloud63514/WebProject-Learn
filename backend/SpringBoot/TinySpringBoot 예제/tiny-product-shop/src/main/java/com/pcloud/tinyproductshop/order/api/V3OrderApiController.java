package com.pcloud.tinyproductshop.order.api;

import com.pcloud.tinyproductshop.order.domain.Order;
import com.pcloud.tinyproductshop.order.dto.OrderQueryDto;
import com.pcloud.tinyproductshop.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/v3/order")
@RestController
@RequiredArgsConstructor
public class V3OrderApiController {
    private final OrderRepository orderRepository;

    @GetMapping("list")
    public List<OrderQueryDto> orders() {
        List<Order> allWithMemberDelivery = orderRepository.findAllWithMemberDelivery();

        return allWithMemberDelivery.stream().map(
                o -> new OrderQueryDto(o)
        ).collect(Collectors.toList());
    }

    @GetMapping("list_v2")
    public List<OrderQueryDto> ordersV2(@RequestParam(value="offset", defaultValue = "0") int offset,
                                        @RequestParam(value="limit", defaultValue = "100") int limit) {
        List<Order> allWithMemberDelivery = orderRepository.findAllWithMemberDeliveryV2(offset, limit);

        return allWithMemberDelivery.stream().map(
                o -> new OrderQueryDto(o)
        ).collect(Collectors.toList());
    }
}
