package com.pcloud.tinyproductshop.order.api;

import com.pcloud.tinyproductshop.order.dto.OrderFlatDto;
import com.pcloud.tinyproductshop.order.dto.OrderItemDto;
import com.pcloud.tinyproductshop.order.dto.OrderQueryDto;
import com.pcloud.tinyproductshop.order.repository.OrderQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/v6/order")
@RestController
@RequiredArgsConstructor
public class V6OrderApiController {

    private final OrderQueryRepository orderQueryRepository;

    @GetMapping("list")
    public List<OrderQueryDto> orders() {
        List<OrderFlatDto> flats = orderQueryRepository.findAllByDto_flat();
        return flats.stream()
                .collect(Collectors.groupingBy(o -> new OrderQueryDto(o.getOrderId(), o.getName(), o.getOrderDate(), o.getOrderStatus(), o.getAddress()),
                        Collectors.mapping(o-> new OrderItemDto(o.getOrderId(), o.getItemName(), o.getOrderPrice(), o.getCount()), Collectors.toList())
                )).entrySet().stream()
                .map(e-> new OrderQueryDto(e.getKey().getOrderId(),
                        e.getKey().getName(), e.getKey().getOrderDate(), e.getKey().getOrderStatus(), e.getKey().getAddress(), e.getValue()))
                .collect(Collectors.toList());
    }
}
