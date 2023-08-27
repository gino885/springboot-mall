package com.gino.springbootmall.dao;

import com.gino.springbootmall.dto.UserRegisterRequest;
import com.gino.springbootmall.model.User;

public interface UserDao {
    Integer createUser(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);
    User getUserByEmail(String email);
}
