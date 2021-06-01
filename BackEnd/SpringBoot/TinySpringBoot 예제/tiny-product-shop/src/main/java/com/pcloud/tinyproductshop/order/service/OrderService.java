package com.pcloud.tinyproductshop.order.service;

import com.pcloud.tinyproductshop.member.domain.Member;
import com.pcloud.tinyproductshop.member.repository.IMemberRepository;
import com.pcloud.tinyproductshop.order.domain.Delivery;
import com.pcloud.tinyproductshop.order.domain.Order;
import com.pcloud.tinyproductshop.order.domain.OrderItem;
import com.pcloud.tinyproductshop.order.domain.OrderSearch;
import com.pcloud.tinyproductshop.order.repository.IOrderRepository;
import com.pcloud.tinyproductshop.product.domain.Product;
import com.pcloud.tinyproductshop.product.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final IOrderRepository orderRepository;
    private final IMemberRepository memberRepository;
    private final IProductRepository productRepository;

    /**
     * 주문
     * @param memberId
     * @param productId
     * @param count
     * @return
     */
    @Transactional
    public Long order(Long memberId, Long productId, int count) {
        Member member = memberRepository.findOne(memberId).get();
        Product product = productRepository.findOne(productId).get();

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(product, product.getPrice(), count);
        Order order = Order.CreateOrder(member, delivery, orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    /**
     * 주문 취소
     * @param orderId
     */
    @Transactional
    public void cancel(Long orderId) {
        orderRepository.findOne(orderId).cancel();
    }

    /**
     * 검색
     * @param orderSearch
     * @return
     */
    public List<Order> searchOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
}
