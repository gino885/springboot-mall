package com.gino.springbootmall.dao;

import com.gino.springbootmall.dto.UseRegisterRequest;
import com.gino.springbootmall.model.User;

public interface UserDao {
    Integer createUser(UseRegisterRequest useRegisterRequest);
    User getUserById(Integer userId);
    User getUserByEmail(String email);
}
