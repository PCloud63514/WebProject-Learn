package com.pcloud.tinyproductshop.member.repository;

import com.pcloud.tinyproductshop.member.domain.Member;

import java.util.Optional;

public interface IMemberRepository {
    public Long save(Member member);
    public Optional<Member> find(Long id);
}
