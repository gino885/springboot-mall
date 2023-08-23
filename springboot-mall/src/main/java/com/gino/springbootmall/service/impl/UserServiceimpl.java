package com.gino.springbootmall.service.impl;

import com.gino.springbootmall.dao.UserDao;
import com.gino.springbootmall.dto.UseRegisterRequest;
import com.gino.springbootmall.model.User;
import com.gino.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceimpl implements UserService {

    @Autowired
   private UserDao userDao;

    @Override
    public Integer register(UseRegisterRequest useRegisterRequest) {
        return userDao.createUser(useRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {

        return userDao.getUserById(userId);
    }
}
