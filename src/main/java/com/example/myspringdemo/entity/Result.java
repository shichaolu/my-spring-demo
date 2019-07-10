package com.example.myspringdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 14:33 2019/7/9
 */
@Data
@AllArgsConstructor
public class Result{

    private String code;
    private String desc;
    private String data;

}

