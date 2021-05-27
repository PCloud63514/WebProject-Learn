package com.pcloud.tinyspringboot;

import com.pcloud.tinyspringboot.member.repository.IMemberRepository;
import com.pcloud.tinyspringboot.member.repository.MemoryMemberRepository;
import com.pcloud.tinyspringboot.member.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public IMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
