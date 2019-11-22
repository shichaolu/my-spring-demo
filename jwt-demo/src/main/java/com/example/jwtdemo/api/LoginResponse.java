package com.example.jwtdemo.api;

import lombok.*;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 11:37 2019/11/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse{

    @Builder.Default
    protected ResultCode code = ResultCode.SUCCESS;

    private String message;

    private String data;

}
