package com.pcloud.tinyproductshop.member.repository;

import com.pcloud.tinyproductshop.member.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class MemberRepository implements IMemberRepository {

    @PersistenceContext
    private EntityManager em;

    // Long을 반환하는 이유는 커맨드와 쿼리를 분리하는 원칙을 지키기 위해서이다.
    // 사이드 이펙트를 발생하는 커맨드이므로, 반환을 지양하며 id는 다음 요청 때 사용할 수 있는 정도이므로 반환
    @Override
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    @Override
    public Optional<Member> find(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }
}
