package com.pcloud.tinyspringboot.member.repository;

import com.pcloud.tinyspringboot.member.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements IMemberRepository {
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // id값 자동 삽입, sql query 자동 생성 등 모든 것을 해준다.
        em.persist(member);//영구 저장하다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        //@id로 지정된 id 값 검색과 다르게 name은 jpql이라는 객체지향 쿼리를 사용해야한다. 문법은 sql과 동일하나 Member 자체를 select 하는 것.
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
