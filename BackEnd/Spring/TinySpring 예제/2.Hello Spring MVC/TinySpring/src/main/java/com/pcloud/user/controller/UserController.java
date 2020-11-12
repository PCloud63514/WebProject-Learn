package com.pcloud.user.controller;

import com.pcloud.user.dto.UserDto;
import com.pcloud.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    //접근 url: /findid?name=HEON&phoneNum=010-000-0000
    @RequestMapping(value="/findId", method= RequestMethod.GET)
    public String findId(HttpServletRequest request) {
        String name = request.getParameter("name");
        String phoneNum = request.getParameter("phoneNum");

        String id = service.FindID(name, phoneNum);
        return id;
    }
    //접근 url: /getinfo?id=qwe123
    @RequestMapping(value="/getInfo", method=RequestMethod.GET)
    public String getInfo(@RequestParam String id) {
        UserDto userDto = service.GetInfo(id);

        return "";
    }
    //접근 url:/setinfo/qwe123/25
    @RequestMapping(value="setInfo/{name}/{age}", method=RequestMethod.POST)
    public void setInfo(@PathVariable("name") String name, @PathVariable("age") int age) {
        service.SetInfo(name, age);
    }
}