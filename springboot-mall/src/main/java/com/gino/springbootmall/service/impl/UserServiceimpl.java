package com.gino.springbootmall.service.impl;

import com.gino.springbootmall.dao.UserDao;
import com.gino.springbootmall.dto.UseRegisterRequest;
import com.gino.springbootmall.model.User;
import com.gino.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceimpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger((UserServiceimpl.class)) ;

    @Autowired
   private UserDao userDao;

    @Override
    public Integer register(UseRegisterRequest useRegisterRequest) {
        //檢查註冊的email
        User user = userDao.getUserByEmail(useRegisterRequest.getEmail());

        if(user != null){
            log.warn("該email {} 已被註冊", useRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return userDao.createUser(useRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {

        return userDao.getUserById(userId);
    }
}
