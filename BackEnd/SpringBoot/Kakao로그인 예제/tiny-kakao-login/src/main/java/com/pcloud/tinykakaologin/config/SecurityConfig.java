package com.pcloud.tinykakaologin.config;

import com.pcloud.tinykakaologin.service.oauth.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableReactiveMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    public OAuth2SuccessHandler oAuth2SuccessHandler() {

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/api/**").hasRole("USER")
                    .anyRequest().authenticated()
                .and()
                    .oauth2Login().loginPage("/login")
                .successHandler(successHandler())
    }
}

