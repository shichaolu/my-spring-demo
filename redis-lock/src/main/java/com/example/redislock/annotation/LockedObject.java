package com.example.redislock.annotation;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 17:07 2019/7/16
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockedObject {
}
