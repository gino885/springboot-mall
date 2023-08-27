package com.gino.springbootmall.service;

import com.gino.springbootmall.dto.UserRegisterRequest;
import com.gino.springbootmall.dto.UserLoginRequest;
import com.gino.springbootmall.model.User;

public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);
    User login(UserLoginRequest userLoginRequest);
}
