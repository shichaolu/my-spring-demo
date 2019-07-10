package com.example.myspringdemo.service;

import com.example.myspringdemo.entity.User;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 11:18 2019/7/9
 */
public interface UserService {

    User getUser(String username,String password);

    String getUserToken(User user);

}
