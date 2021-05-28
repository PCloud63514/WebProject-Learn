package com.pcloud.tinyspringboot;

import com.pcloud.tinyspringboot.member.repository.IMemberRepository;
import com.pcloud.tinyspringboot.member.repository.JdbcTemplateMemberRepository;
import com.pcloud.tinyspringboot.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public IMemberRepository memberRepository() {
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
