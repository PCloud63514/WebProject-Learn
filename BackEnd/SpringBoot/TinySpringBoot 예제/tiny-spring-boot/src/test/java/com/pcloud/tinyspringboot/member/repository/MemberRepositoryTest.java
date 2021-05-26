package com.pcloud.tinyspringboot.member.repository;

import com.pcloud.tinyspringboot.member.domain.Member;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {
    MemberRepository memberRepository = new MemberRepository();

    @Test
    public void save() {
        Member member = new Member();
        member.setName("PCloud");
        memberRepository.save(member);

        Member resultMember = memberRepository.findById(member.getId()).get();
        assertThat(member).isEqualTo(resultMember);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        Optional<Member> result = memberRepository.findByName("spring2");
        assertThat(result).isEqualTo(member2);
    }
}
