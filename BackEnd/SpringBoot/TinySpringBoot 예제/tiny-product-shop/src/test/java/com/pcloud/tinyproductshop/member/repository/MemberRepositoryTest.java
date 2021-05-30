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
        member.setName("PCloud");
//        member.setAddress();
    }

    @Test
    void testSave() {
    }

    @Test
    void testFind() {
    }
}