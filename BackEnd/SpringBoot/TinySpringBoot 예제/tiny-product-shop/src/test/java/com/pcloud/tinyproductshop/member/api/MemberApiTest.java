package com.pcloud.tinyproductshop.member.api;

import com.pcloud.tinyproductshop.member.domain.Member;
import com.pcloud.tinyproductshop.member.dto.MemberDto;
import com.pcloud.tinyproductshop.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemberApiTest {
    private SpyMemberService memberService;
    private MemberApi memberApi;

    @BeforeEach
    void setUp() {
        memberService = new SpyMemberService();
        memberApi = new MemberApi(memberService);
    }

    @Test
    void memberOne_callsFindOneInMemberService() throws Exception {
        memberService.findOne_returnValue = Optional.of(new Member());

        memberApi.memberOne(1L);

        assertThat(memberService.findOne_wasCalled).isTrue();
        assertThat(memberService.findOne_argumentLong).isEqualTo(1L);
    }

    @Test
    void memberOne_returnsMemberDto() {
        Member givenMember = new Member();
        memberService.findOne_returnValue = Optional.of(givenMember);

        MemberDto actualMemberDto = memberApi.memberOne(1L);

        assertThat(actualMemberDto.getName()).isEqualTo(givenMember.getName());
    }

    private static class SpyMemberService implements MemberService {

        public boolean findOne_wasCalled;
        public Optional<Member> findOne_returnValue;
        public Long findOne_argumentLong;

        @Override
        public Long join(Member member) {
            return null;
        }

        @Override
        public List<Member> findMembers() {
            return null;
        }

        @Override
        public Optional<Member> findOne(Long memberId) {
            findOne_argumentLong = memberId;
            findOne_wasCalled = true;
            return findOne_returnValue;
        }

        @Override
        public void update(Long id, String name) {

        }
    }
}