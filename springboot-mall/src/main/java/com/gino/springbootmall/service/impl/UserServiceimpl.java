package com.gino.springbootmall.service.impl;

import com.gino.springbootmall.dao.UserDao;
import com.gino.springbootmall.dto.UseRegisterRequest;
import com.gino.springbootmall.dto.UserLoginRequest;
import com.gino.springbootmall.model.User;
import com.gino.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceimpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger((UserServiceimpl.class)) ;

    @Autowired
   private UserDao userDao;

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        //檢查user是否存在
        if(user == null){
            log.warn("該email {} 尚未註冊", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //使用MD5生成雜湊值
        String hashPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());

        //== 是同一個物件才行 像1 = 1 但new s1 != new s2 而是s1.equals(s2)
        if(user.getPassword().equals(hashPassword)){
            return user;
        }
        else {
            log.warn("email {} 的密碼不正確", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public Integer register(UseRegisterRequest useRegisterRequest) {
        //檢查註冊的email
        User user = userDao.getUserByEmail(useRegisterRequest.getEmail());

        if(user != null){
            log.warn("該email {} 已被註冊", useRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //使用 MD5 生成密碼的雜湊值
        String hashedPassword = DigestUtils.md5DigestAsHex(useRegisterRequest.getPassword().getBytes());
        useRegisterRequest.setPassword(hashedPassword);

        return userDao.createUser(useRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {

        return userDao.getUserById(userId);
    }
}
