package com.pcloud.tinykakaologin.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class OAuthController {

    @GetMapping("/{provider}")
    public Test LoginUrl(@PathVariable String provider) {
        return new Test(provider);
    }

    @Data
    static class Test {
        private String provider;

        Test(String provider) {
            this.provider = provider;
        }
    }
}
