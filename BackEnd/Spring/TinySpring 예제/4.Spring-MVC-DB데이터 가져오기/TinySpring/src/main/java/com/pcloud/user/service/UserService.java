package com.pcloud.user.service;

import com.pcloud.user.dao.UserDao;
import com.pcloud.user.vo.UserVO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserService {

    @Inject
    UserDao userDao;

    public List<UserVO> getUserList() {
        List<UserVO> list = userDao.userSelectAll();
        return list;
    }

    public UserVO getUser(String id) {
        UserVO user = userDao.userSelect(id);
        return user;
    }
}
