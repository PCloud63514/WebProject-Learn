package com.pcloud.userservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public UserRepository userRepository() {
        return new MemoryUserRepository();
    }
}
