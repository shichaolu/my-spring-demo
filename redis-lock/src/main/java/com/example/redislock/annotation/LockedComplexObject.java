package com.example.redislock.annotation;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 17:08 2019/7/16
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockedComplexObject {

    /**
     * 复杂对象中需要加锁的成员变量，如一个商品对象的商品ID
     */
    String field() default "";

}
