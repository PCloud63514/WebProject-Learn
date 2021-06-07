package com.pcloud.tinyproductshop.order.api;

import com.pcloud.tinyproductshop.order.dto.OrderQueryDto;
import com.pcloud.tinyproductshop.order.repository.OrderQueryRepository;
import com.pcloud.tinyproductshop.order.repository.OrderRepository;
import com.pcloud.tinyproductshop.order.repository.OrderSimpleQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/v5/order")
@RestController
@RequiredArgsConstructor
public class V5OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;
    private final OrderQueryRepository orderQueryRepository;

    @GetMapping("list")
    public List<OrderQueryDto> orders() {
        return orderQueryRepository.findAllByDto_optimization();
    }
}
