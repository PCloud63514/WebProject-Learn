package com.pcloud.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value="/call", method= RequestMethod.GET)
    public String call() {
        return "home";
    }

}
