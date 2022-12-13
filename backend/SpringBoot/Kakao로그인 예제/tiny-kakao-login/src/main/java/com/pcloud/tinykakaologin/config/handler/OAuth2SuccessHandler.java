package com.pcloud.tinykakaologin.config.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

    }

    private String generateToken(Authentication authentication) {
        OAuth2User o2u = (OAuth2User)authentication.getPrincipal();
        String attributeKey = o2u.getName();
        String roles = o2u.getAuthorities().stream().map(o -> ((GrantedAuthority) o).getAuthority()).collect(Collectors.joining(","));

    }
}
