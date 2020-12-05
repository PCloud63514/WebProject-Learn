package com.pcloud.user.controller;

import com.pcloud.user.vo.UserVO;
import com.pcloud.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
public class UserController {

    @Inject
    UserService userService;

    @RequestMapping(value="/list", method= RequestMethod.GET)
    public List<UserVO> getUserList() {
        List<UserVO> list = userService.getUserList();
        return list;
    }

    @RequestMapping(value="/{id}/view", method=RequestMethod.GET)
    public UserVO getUser(@PathVariable("id") String id) {
        UserVO user = userService.getUser(id);
        return user;
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public UserVO getUser2(@RequestParam("id") String id) {
        UserVO user = userService.getUser(id);
        return user;
    }
}
