package com.example.jwtdemo.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 11:15 2019/11/20
 */
@Getter
@AllArgsConstructor
public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(HttpServletResponse.SC_OK, "Operation is Successful"),
    FAILURE(HttpServletResponse.SC_BAD_REQUEST, "Biz Exception");

    private final int code;
    private final String msg;

}
