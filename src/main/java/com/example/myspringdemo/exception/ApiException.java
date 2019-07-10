package com.example.myspringdemo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 11:25 2019/7/9
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ApiException extends RuntimeException {

    private String code;
    private String message;

    public ApiException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
