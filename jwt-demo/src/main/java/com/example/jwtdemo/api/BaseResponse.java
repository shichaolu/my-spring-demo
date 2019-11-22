package com.example.jwtdemo.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 11:19 2019/11/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse {

    @Builder.Default
    protected ResultCode code = ResultCode.SUCCESS;

    private String message;

}
