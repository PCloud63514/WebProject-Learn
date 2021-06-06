package com.pcloud.tinyproductshop.order.repository;

import com.pcloud.tinyproductshop.order.domain.Order;
import com.pcloud.tinyproductshop.order.domain.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

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


    @Override
    public List<Order> findAll(OrderSearch orderSearch) {
        return findAllByCriteria(orderSearch);

//        return em.createQuery("select o from Order o join o.member m where o.status=:status and m.name like :name", Order.class)
//                .setParameter("status", orderSearch.getOrderStatus())
//                .setParameter("name", orderSearch.getMemberName())
//                .setFirstResult(0)
//                .setMaxResults(100)
//                .getResultList();
    }

    /**
     * JPA Criteria FindAll
     * @param orderSearch
     * @return
     */
    public List<Order> findAllByCriteria(OrderSearch orderSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> o = cq.from(Order.class);
        Join<Object, Object> m = o.join("member", JoinType.INNER);
        List<Predicate> criteria = new ArrayList<>();

        //주문 상태 검색
        if(orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
            criteria.add(status);
        }
        if(StringUtils.hasText(orderSearch.getMemberName())) {
            Predicate name = cb.like(m.<String>get("name"), "%" + orderSearch.getMemberName() + "%");
            criteria.add(name);
        }
        cq.where(criteria.toArray(new Predicate[criteria.size()]));
        TypedQuery<Order> query = em.createQuery(cq).setMaxResults(100);
        return query.getResultList();
    }

    public List<Order> findAllWithMemberDelivery() {
        /*
            원래 sql에서 distinct 는 조회한 데이터 row의 결과가 전부 동일해야 중복으로 취급되고 제거되었지만
            jpa는 sql에 distinct 를 추가하고, 검색한 결과의 데이터에서 같은 Entity가 조회되면(id 기준)
            애플리케이션에서 중복을 제외한다.
            페이징이 불가능해진다.
            .setFirstResult(0)  - offset
            .setMaxResults(100) - limit
            을 사용하면 applying in memory가 발생
            이로 써 알게된 사실은 1대다 fetch 조회는 페이징을 해서는 안된다.
            또한 1대다 fetch 조회는 1개만 사용할 수 있다.
         */

        return em.createQuery(
                "select distinct o from Order o" +
                        " join fetch o.member m" +
                        " join fetch o.delivery d" +
                        " join fetch o.orderItems oi" +
                        " join fetch oi.product p", Order.class
        ).getResultList();
    }
}
