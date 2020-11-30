package com.pcloud.home;

import com.pcloud.home.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    @ResponseBody
    @RequestMapping(value="/call", method= RequestMethod.GET)
    public User home() {
        User user = new User();
        user.setId("test01");
        user.setPasswd("test");
        user.setName("Kim");
        user.setGenderId(0);
        return user;
    }
    @ResponseBody
    @RequestMapping(value="/test", method= RequestMethod.GET)
    public Map<String, Object> test() {
        Map<String, Object> testMap = new HashMap<String, Object>();
        testMap.put("test","test");
        return testMap;
    }
}
