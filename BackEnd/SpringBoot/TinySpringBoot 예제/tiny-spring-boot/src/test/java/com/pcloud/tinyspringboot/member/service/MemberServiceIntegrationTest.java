package com.pcloud.tinyspringboot.member.service;

import com.pcloud.tinyspringboot.member.domain.Member;
import com.pcloud.tinyspringboot.member.repository.IMemberRepository;
import com.pcloud.tinyspringboot.member.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;

    @Autowired IMemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given (무언가 주어졌을 때) - 데이터 기반
        Member member = new Member();
        member.setName("PCloud");
        //when (이것을 실행했을 때) - 검증
        Long saveId = memberService.join(member);
        //then (결과는 ~~해야한다) - 결과
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        Member member1 = new Member();
        member1.setName("PCloud");

        Member member2 = new Member();
        member2.setName("PCloud");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
