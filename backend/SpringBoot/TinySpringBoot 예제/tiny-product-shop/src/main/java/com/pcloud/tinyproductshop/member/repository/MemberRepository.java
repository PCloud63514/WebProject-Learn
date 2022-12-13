package com.pcloud.tinyproductshop.member.repository;

import com.pcloud.tinyproductshop.member.domain.IMemberRepository;
import com.pcloud.tinyproductshop.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository implements IMemberRepository {
    //PersistenceContext 대신 Autowired가 가능하다. 이는 SpringBoot jpa가 지원해주는 기능이다.
//    @PersistenceContext
    private final EntityManager em;

    // Long을 반환하는 이유는 커맨드와 쿼리를 분리하는 원칙을 지키기 위해서이다.
    // 사이드 이펙트를 발생하는 커맨드이므로, 반환을 지양하며 id는 다음 요청 때 사용할 수 있는 정도이므로 반환
    @Override
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    @Override
    public Optional<Member> findOne(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    @Override
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name=:name", Member.class)
                .setParameter("name", name).getResultList();
    }

}
