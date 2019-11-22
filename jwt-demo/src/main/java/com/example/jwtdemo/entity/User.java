package com.example.jwtdemo.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 11:38 2019/7/9
 */
@Data
@Builder
public class User {

    private String id;
    private String username;
    private String password;

}
