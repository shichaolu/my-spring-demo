package com.example.myspringdemo.controller.rest;

import com.example.myspringdemo.annotation.LoginToken;
import com.example.myspringdemo.annotation.PassToken;
import com.example.myspringdemo.entity.Base;
import com.example.myspringdemo.entity.Result;
import com.example.myspringdemo.entity.User;
import com.example.myspringdemo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 14:10 2019/7/9
 */
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PassToken
    @PostMapping("/user")
    public Result userLogin(String username, String password) {
        User user = userService.getUser(username, password);
        if (user == null) {
            return new Result("9999", "用户名或密码错误", null);
        }
        String token = userService.getUserToken(user);
        return new Result("0000", "登录成功", token);
    }

    @LoginToken
    @PostMapping("/msg")
    public Base getMsg() {
        return new Base("0000", "验证通过");
    }

}
