package com.example.proxydemo.service.impl;

import com.example.proxydemo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 9:17 2019/10/30
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void work() {
        System.out.println("do sth...");
    }
}
