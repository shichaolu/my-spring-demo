package com.example.myspringdemo.service.impl;

import com.example.myspringdemo.entity.User;
import com.example.myspringdemo.service.UserService;
import com.example.myspringdemo.util.JwtUtil;
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

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(username);
        user.setPassword(password);
        return user;

    }

    @Override
    public String getUserToken(User user) {
        return JwtUtil.createToken(user);
    }

}
