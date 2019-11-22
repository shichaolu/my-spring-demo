package com.example.jwtdemo.service.impl;

import com.example.jwtdemo.entity.User;
import com.example.jwtdemo.service.UserService;
import com.example.jwtdemo.util.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 14:15 2019/7/9
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(String username, String password) {

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return null;
        }

        return User.builder().id(UUID.randomUUID().toString())
                .username(username).password(password).build();
    }

    @Override
    public String getUserToken(User user) {
        return JwtUtil.createToken(user);
    }

}
