package com.example.proxydemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 9:18 2019/10/30
 */
@Aspect
@Component
public class UserServiceAspect {

    @Before("execution(* com.example.proxydemo.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint){
        System.out.println("UserServiceAspect...");
    }

}
