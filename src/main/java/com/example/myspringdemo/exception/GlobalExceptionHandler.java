package com.example.myspringdemo.exception;

import com.example.myspringdemo.entity.Base;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 17:52 2019/7/9
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public Base apiExceptionHandler(ApiException e) {
        return new Base(e.getCode(), e.getMessage());
    }

}
