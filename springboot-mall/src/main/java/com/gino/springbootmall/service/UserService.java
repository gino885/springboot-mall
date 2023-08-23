package com.gino.springbootmall.service;

import com.gino.springbootmall.dto.ProductRequest;
import com.gino.springbootmall.dto.UseRegisterRequest;
import com.gino.springbootmall.model.User;

public interface UserService {

    Integer register(UseRegisterRequest useRegisterRequest);
    User getUserById(Integer userId);
}
