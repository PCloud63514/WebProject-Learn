package com.pcloud.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.sql.DataSource;

@Controller
public class HomeController {

    @Inject
    private DataSource dataSource;

    @RequestMapping(value="/call", method= RequestMethod.GET)
    public String call() {
        return "home";
    }

}
