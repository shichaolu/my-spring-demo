package com.example.jwtdemo.error;

import com.example.jwtdemo.api.ResultCode;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 11:25 2019/7/9
 */
public class ApiException extends RuntimeException {

    private final ResultCode resultCode;

    public ApiException(String message) {
        super(message);
        this.resultCode = ResultCode.FAILURE;
    }
}
