package com.pcloud.tinyspringboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/home")
@Controller
public class  HomeController {

    @GetMapping("/static")
    public String home(Model model, @RequestParam(required = false) String name) {
        System.out.println(name);
        model.addAttribute("data", name);
        return "home";
    }

    @GetMapping("/api")
    @ResponseBody
    public Home home(@RequestParam(required = false) String name) {
        Home home = new Home();
        home.setName(name);

        return home;
    }

    static class Home {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
