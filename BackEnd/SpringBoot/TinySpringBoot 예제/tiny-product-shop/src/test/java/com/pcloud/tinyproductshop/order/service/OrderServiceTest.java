package com.pcloud.tinyproductshop.order.service;

import com.pcloud.tinyproductshop.member.domain.Address;
import com.pcloud.tinyproductshop.member.domain.Member;
import com.pcloud.tinyproductshop.order.domain.Order;
import com.pcloud.tinyproductshop.order.domain.OrderStatus;
import com.pcloud.tinyproductshop.order.repository.IOrderRepository;
import com.pcloud.tinyproductshop.product.domain.Product;
import com.pcloud.tinyproductshop.product.domain.impl.Book;
import com.pcloud.tinyproductshop.product.exception.NotEnoughStockException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired OrderService orderService;
    @Autowired IOrderRepository orderRepository;

    @Autowired
    EntityManager em;

    @Test
    void 주문()throws Exception {
        Member member = createMember("PCloud");
        Product product = createProduct("떡볶이가 먹고 싶어", 50000, 100);

        Long orderId = orderService.order(member.getId(), product.getId(), 30);
        Order order = orderRepository.findOne(orderId);
        assertEquals(OrderStatus.ORDER, order.getStatus(), "상품 주문시 상태는 ORDER");
        assertEquals(1, order.getOrderItems().size(), "상품 종류 수 검증");
        assertEquals(70, product.getStockQuantity(), "재고 수량 검증");
        assertEquals(50000 * 30, order.getTotalPrice(), "주문 가격 검증");
    }

    private Member createMember(String name) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(new Address("서울", "한강", "123-123"));
        em.persist(member);
        return member;
    }

    @Test
    void 주문_재고수량_초과() throws Exception {
        Member member = createMember("PCloud");
        Product product = createProduct("쓰울 관광", 10000, 100);
        int orderCount = 110;
        try {
            orderService.order(member.getId(), product.getId(), orderCount);
            fail("재고 수량 예외 발생 실패");
        } catch (NotEnoughStockException e) {
            
        }
    }

    @Test
    void 주문_취소()throws Exception {
        Member member = createMember("PCloud");

        Product product = createProduct("떡볶이가 먹고 싶어", 50000, 100);

        Long orderId = orderService.order(member.getId(), product.getId(), 30);
        Order order = orderRepository.findOne(orderId);
        orderService.cancel(orderId);

        assertEquals(OrderStatus.CANCEL, order.getStatus(), "주문 취소 상태는 CANCEL");
        assertEquals(100, product.getStockQuantity());
    }

    private Product createProduct(String name, int orderPrice, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setAuthor("PPP");
        book.setIsbn("M123");
        book.setPrice(orderPrice);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }
}