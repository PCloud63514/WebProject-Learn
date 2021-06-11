package com.pcloud.tinyproductshop.member.service;

import com.pcloud.tinyproductshop.member.domain.Member;
import com.pcloud.tinyproductshop.member.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("PCloud");
        //when
        Long savedId = memberService.join(member);
        //then
        Assertions.assertEquals(member, memberRepository.findOne(savedId).get());
    }

    @Test//JUnit4 기능(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member = new Member();
        member.setName("PCloud");
        Member member2 = new Member();
        member2.setName("PCloud");
        //when
        try {
            Long savedId1 = memberService.join(member);
            Long savedId2 = memberService.join(member2);
        }catch (IllegalStateException e) {
            return;
        }
        //then
        fail("예외가 발생되지 않음.");
    }

    @Test
    void findMembers() {
        memberService.findMembers();
    }

    @Test
    void findOne() {
        Member member = new Member();
        member.setName("PCloud");
        //when
        Long savedId = memberService.join(member);

        memberService.findOne(savedId);
    }
}