package com.pcloud.tinyproductshop.member.repository;

import com.pcloud.tinyproductshop.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    IMemberRepository memberRepository;

    @Test
    public void testMember() throws Exception {
        Member member = new Member();
        member.setUsername("memberA");

        Long saveId = memberRepository.save(member);
        Optional<Member> result = memberRepository.find(saveId);
        Assertions.assertThat(result.get().getId()).isEqualTo(member.getId());
        Assertions.assertThat(result.get().getUsername()).isEqualTo(member.getUsername());
        //같은 영속성 컨텍스트 이므로 true
        Assertions.assertThat(result.get()).isEqualTo(member);
    }

    @Test
    void save() {

    }

    @Test
    void find() {
    }
}