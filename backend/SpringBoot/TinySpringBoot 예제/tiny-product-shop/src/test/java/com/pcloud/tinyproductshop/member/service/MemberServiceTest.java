package com.pcloud.tinyproductshop.member.service;

import com.pcloud.tinyproductshop.member.domain.IMemberRepository;
import com.pcloud.tinyproductshop.member.domain.Member;
import com.pcloud.tinyproductshop.member.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.metamodel.EntityType;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    private SpyMemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository = new SpyMemberRepository();
        memberService = new MemberServiceImpl(memberRepository);
    }

    // 1. test method
    // 1. test implementation
    // 2. source implementation
    // 2. test method
    // 2. test implementation
    // 1. source implementation

    @Test
    void join_throwException_whenExistsName() {
        Member givenMember = new Member();
        List<Member> givenMembers = List.of(givenMember);
        memberRepository.findByName_returnValue = givenMembers;

        assertThrows(IllegalStateException.class, () -> {
            memberService.join(givenMember);
        });
    }

    @Test
    void join_callsSaveInMemberRepository() {
        Member givenMember = new Member();
        memberService.join(givenMember);

        assertThat(memberRepository.save_wasCalled).isTrue();
        assertThat(memberRepository.save_argumentMember).isEqualTo(givenMember);
    }

    @Test
    void join_returnsMemberId() {
        Member givenMember = new Member();
        Long memberId = memberService.join(givenMember);

        assertThat(memberId).isEqualTo(givenMember.getId());
    }

    @Test
    void findMembers_callsFindAllInMemberRepository() {
        List<Member> members = memberService.findMembers();

        assertThat(memberRepository.findAll_wasCalled).isTrue();
        assertThat(memberRepository.defaultMembers).isEqualTo(members);
    }

    @Test
    void findOne_callsFindOneInMemberRepository() {
        Member givenMember = Member.builder()
                            .id(1L)
                            .build();
        memberRepository.findOne(givenMember.getId());

        assertThat(memberRepository.findOne_argumentMemberId).isEqualTo(givenMember.getId());
    }

    @Test
    void findOne_returnMember() {
        Member givenMember = Member.builder()
                            .id(1L)
                            .build();
        memberRepository.findOne_returnValue = Optional.of(givenMember);
        Optional<Member> member = memberRepository.findOne(givenMember.getId());
        assertThat(member.get()).isEqualTo(givenMember);
    }

    @Test
    void update_setNameByMember() {
        Member givenMember = Member.builder()
                            .id(1L)
                            .name("TEST")
                            .build();

        memberRepository.findOne_returnValue = Optional.of(givenMember);
        memberRepository.update_name = "TestUpdateName";

        memberService.update(givenMember.getId(), memberRepository.update_name);
        assertThat(memberRepository.update_name).isEqualTo(givenMember.getName());
    }

    private static class SpyMemberRepository implements IMemberRepository {
        private List<Member> defaultMembers = Collections.emptyList();

        public boolean findAll_wasCalled;

        public Long findOne_argumentMemberId;
        public Optional<Member> findOne_returnValue;

        public String update_name;
        public Optional<Member> update_returnValue;

        public List<Member> findByName_returnValue = defaultMembers;

        public boolean save_wasCalled;
        public Member save_argumentMember;

        @Override
        public Long save(Member member) {
            save_argumentMember = member;
            save_wasCalled = true;
            return null;
        }

        @Override
        public Optional<Member> findOne(Long id) {
            findOne_argumentMemberId = id;
            return findOne_returnValue;
        }

        @Override
        public List<Member> findAll() {
            findAll_wasCalled = true;
            return defaultMembers;
        }

        @Override
        public List<Member> findByName(String name) {
            return findByName_returnValue;
        }
    }
}