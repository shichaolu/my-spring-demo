package com.example.rwseparatedemo.aspect;

import com.example.rwseparatedemo.util.DataSourceHolder;
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

    @Pointcut(value = "!@annotation(com.example.rwseparatedemo.annotation.Master) " +
            "&& (execution(* com.example.rwseparatedemo.service..*.select*(..)) " +
            "|| execution(* com.example.rwseparatedemo.service..*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut(value = "@annotation(com.example.rwseparatedemo.annotation.Master) " +
            "|| execution(* com.example.rwseparatedemo.service..*.insert*(..)) " +
            "|| execution(* com.example.rwseparatedemo.service..*.add*(..)) " +
            "|| execution(* com.example.rwseparatedemo.service..*.update*(..)) " +
            "|| execution(* com.example.rwseparatedemo.service..*.edit*(..)) " +
            "|| execution(* com.example.rwseparatedemo.service..*.delete*(..)) " +
            "|| execution(* com.example.rwseparatedemo.service..*.remove*(..))")
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
