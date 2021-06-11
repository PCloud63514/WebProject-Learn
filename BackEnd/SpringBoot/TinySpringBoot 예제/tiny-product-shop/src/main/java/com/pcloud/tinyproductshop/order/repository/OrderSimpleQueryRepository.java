package com.pcloud.tinyproductshop.order.repository;

import com.pcloud.tinyproductshop.order.dto.OrderQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    public List<OrderQueryDto> findOrderDtos() {
        /*
            기본적인 조회 성능은 비슷하지만 select 해야할 요소가 적어지는 장점이 있다.
            네트웍 용량이 최근엔 너무 좋다보니 성능 최적화가 findAllWithMemberDelivery 에 비해 크지 않으며,
            API 스펙에 맞춘 코드를 Repository에 들어가다보니 재사용성이 떨어지게 된다.
            Repository는 Entity의 그래프를 불러오거나 최적화 하는 용도로 쓰이는게 좋고,
            findOrderDtos는 API 스펙인 Dto를 사용하므로 사용에 알맞지 않다.
            물론 필드에서 통계 api나 조회용 등 이런 방식의 api를 만들 가능성은 얼마든지 있으므로 별개의 Repository로 관리하는 편이 좋다.
         */
        return em.createQuery(
                "select new com.pcloud.tinyproductshop.order.dto.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                        " from Order o" +
                        " join o.member m" +
                        " join o.delivery d", OrderQueryDto.class)
                .getResultList();
    }
}
