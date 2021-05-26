package com.pcloud.tinyspringboot.member.repository;

import com.pcloud.tinyspringboot.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface IMemberRepository {
    Member save(Member member);
    //Java8에 등장한 기능. null 처리 용도이다. null을 Optional로 한번 감싸는 방법이다.
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
