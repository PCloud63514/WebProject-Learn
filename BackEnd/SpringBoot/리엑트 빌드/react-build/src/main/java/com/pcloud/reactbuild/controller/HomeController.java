package com.pcloud.reactbuild.controller;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/home")
@RestController
public class HomeController {

    @GetMapping
    public HomeDto Home(@RequestParam("name") String name) {
        return new HomeDto(name, "하이");
    }

    @Data
    static class HomeDto {
        private String name;
        private String Message;

        public HomeDto(String name, String message) {
            this.name = name;
            Message = message;
        }
    }
}
