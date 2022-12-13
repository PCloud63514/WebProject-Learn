package com.pcloud.tinyproductshop.member.domain;

import com.pcloud.tinyproductshop.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface IMemberRepository {
    public Long save(Member member);
    public Optional<Member> findOne(Long id);
    public List<Member> findAll();
    public List<Member> findByName(String name);
}
