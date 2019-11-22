package com.example.jwtdemo.controller;

import com.example.jwtdemo.annotation.LoginToken;
import com.example.jwtdemo.annotation.PassToken;
import com.example.jwtdemo.api.BaseResponse;
import com.example.jwtdemo.api.LoginResponse;
import com.example.jwtdemo.api.ResultCode;
import com.example.jwtdemo.entity.User;
import com.example.jwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PassToken
    @PostMapping("/user")
    public LoginResponse userLogin(String username, String password) {
        User user = userService.getUser(username, password);
        if (user == null) {
            return LoginResponse.builder().code(ResultCode.FAILURE)
                    .message("用户名或密码错误").build();
        }

        String token = userService.getUserToken(user);
        return LoginResponse.builder()
                .message("登录成功").data(token).build();
    }

    @LoginToken
    @PostMapping("/msg")
    public BaseResponse getMsg() {
        return BaseResponse.builder()
                .message("验证通过").build();
    }

}
