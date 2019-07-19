package com.example.redislock.annotation;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 17:04 2019/7/16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {

    /**
     * redis锁key的前缀
     */
    String lockedPrefix() default "";

    /**
     * 轮询锁的时间，单位毫秒
     */
    long timeOut() default 2000;

    /**
     * key在redis里存在的时间，单位秒
     */
    int expireTime() default 1;
}
