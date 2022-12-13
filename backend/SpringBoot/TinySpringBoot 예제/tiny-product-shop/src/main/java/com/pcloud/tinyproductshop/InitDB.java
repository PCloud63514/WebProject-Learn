package com.pcloud.tinyproductshop;

import com.pcloud.tinyproductshop.member.domain.Address;
import com.pcloud.tinyproductshop.member.domain.Member;
import com.pcloud.tinyproductshop.order.domain.Delivery;
import com.pcloud.tinyproductshop.order.domain.Order;
import com.pcloud.tinyproductshop.order.domain.OrderItem;
import com.pcloud.tinyproductshop.product.domain.impl.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;
    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Member member = Member.create("userA", new Address("서울", "bb", "aa"));
            em.persist(member);

            Book book1 = createBook("JPA1 BOOK", 10000, 100);
            Book book2 = createBook("JPA2 BOOK", 10000, 100);

            em.persist(book1);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());

            Order order = Order.CreateOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        public void dbInit2() {
            Member member = Member.create("userB", new Address("서울", "bb", "aa"));
            em.persist(member);
            Book book1 = createBook("SPRING1 BOOK", 10000, 100);
            Book book2 = createBook("SPRING2 BOOK", 10000, 100);
            em.persist(book1);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 100000, 10);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 200000, 20);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());

            Order order = Order.CreateOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private Book createBook(String name, int price, int stockQuantity) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);
            return book;
        }
    }
}


