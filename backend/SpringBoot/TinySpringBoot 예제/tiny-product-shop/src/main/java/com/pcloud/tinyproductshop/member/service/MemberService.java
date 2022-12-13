package com.pcloud.tinyproductshop.member.service;

import com.pcloud.tinyproductshop.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Long join(Member member);

    List<Member> findMembers();

    Optional<Member> findOne(Long memberId);

    void update(Long id, String name);
}
