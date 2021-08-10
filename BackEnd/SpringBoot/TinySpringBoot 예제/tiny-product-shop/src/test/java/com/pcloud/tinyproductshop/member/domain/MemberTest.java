package com.pcloud.tinyproductshop.member.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    @Test
    void createMember() {
        Address givenAddress = new Address(null, null, null);
        Member member = new Member(0L, "name", givenAddress);

        assertThat(member.getName()).isEqualTo("name");
        assertThat(member.getAddress()).isEqualTo(givenAddress);
    }
}