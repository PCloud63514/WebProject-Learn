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

    public List<Order> findAllWithMemberDeliveryV2(int offset, int limit) {
        /*
            ToOne 관계는 row 수를 증가시키지않는다. 즉 fetch join을 하는 것이 옳바르다.
            또한 페이징 처리에도 문제가 발생하지 않는다.
            컬렉션은 지연 로딩으로 조회해야한다. 즉 fetch join을 해서는 않된다.
            지연 로딩 성능 최적화를 위해 hibernate.default_batch_fetch_size, @BatchSize를 적용해야한다.
            hibernate.default_batch_fetch_size - global 설정
            @BatchSize - 단일 설정
            이를 설정하면 n+1 과 같은 1개씩 조회해오는 문제를 설정해둔 갯수만큼 미리 가져온다.
            default_batch_fetch_size를 사용하면 SQL IN절을 이용해서 데이터를 가져오게된다.
            즉 fetch join 의 필요성도 없어지게 된다.
            단 batch 형식이다보니 네트웤 용량이 커지므로 가급적 ToOne 관계는 fetch를 사용하는 것이 좋다.
            batch Size를 줄이면 속도가 느려지고, 늘리면 순간적인 부하가 늘어나게 되므로 OOM이 발생할 수 있다.
            이를 잘 맞추는 것이 중요.
         */

        return em.createQuery(
                "select o from Order o" +
                        " join fetch o.member m" +
                        " join fetch o.delivery d", Order.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
