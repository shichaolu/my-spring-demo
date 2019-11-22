package com.example.jwtdemo.error;

import com.example.jwtdemo.api.BaseResponse;
import com.example.jwtdemo.api.ResultCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 11:13 2019/11/20
 */
@RestControllerAdvice
public class GlobalExceptionTranslator {

    @ExceptionHandler
    public BaseResponse handleError(ApiException e) {
        return BaseResponse.builder().code(ResultCode.FAILURE).message(e.getMessage()).build();
    }

}
