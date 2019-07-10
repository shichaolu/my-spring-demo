package com.example.myspringdemo.aspect;

import com.example.myspringdemo.util.DataSourceHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Description: 定义切面，查询都走从库，插入/修改/删除走主库
 * @Author: Shichao.Lu
 * @Date: Created in 11:01 2019/7/10
 */
@Aspect
@Component
public class DataSourceAspect {

    @Pointcut(value = "!@annotation(com.example.myspringdemo.annotation.Master) " +
            "&& (execution(* com.example.myspringdemo.service..*.select*(..)) " +
            "|| execution(* com.example.myspringdemo.service..*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut(value = "@annotation(com.example.myspringdemo.annotation.Master) " +
            "|| execution(* com.example.myspringdemo.service..*.insert*(..)) " +
            "|| execution(* com.example.myspringdemo.service..*.add*(..)) " +
            "|| execution(* com.example.myspringdemo.service..*.update*(..)) " +
            "|| execution(* com.example.myspringdemo.service..*.edit*(..)) " +
            "|| execution(* com.example.myspringdemo.service..*.delete*(..)) " +
            "|| execution(* com.example.myspringdemo.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DataSourceHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DataSourceHolder.master();
    }

}
