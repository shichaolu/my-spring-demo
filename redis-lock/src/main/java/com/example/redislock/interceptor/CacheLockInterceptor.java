package com.example.redislock.interceptor;


import com.example.redislock.annotation.CacheLock;
import com.example.redislock.annotation.LockedComplexObject;
import com.example.redislock.annotation.LockedObject;
import com.example.redislock.exception.CacheLockException;
import com.example.redislock.lock.RedisLock;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 17:11 2019/7/16
 */
@Slf4j
public class CacheLockInterceptor implements InvocationHandler {

    public static int ERROR_COUNT = 0;
    private Object proxied;

    public CacheLockInterceptor(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        CacheLock cacheLock = method.getAnnotation(CacheLock.class);
        if (cacheLock == null) {
            log.info("no cacheLock annotation");
            return method.invoke(proxied, args);
        }

        //获得方法中参数的注解
        Annotation[][] annotations = method.getParameterAnnotations();

        //根据参数注解获得加锁的参数
        Object lockedObject = getLockedObject(annotations, args);
        String objectValue = lockedObject.toString();

        //新建一个锁
        RedisLock lock = new RedisLock(cacheLock.lockedPrefix(), objectValue);

        //加锁
        boolean result = lock.lock(cacheLock.timeOut(), cacheLock.expireTime());

        //取锁失败
        if (!result) {
            ERROR_COUNT += 1;
            throw new CacheLockException("get lock fail");
        }

        try {
            //加锁成功，执行方法
            return method.invoke(proxied, args);
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    private Object getLockedObject(Annotation[][] annotations, Object[] args) {
        if (annotations == null || annotations.length == 0) {
            throw new CacheLockException("没有被注解的参数");
        }

        if (args == null || args.length == 0) {
            throw new CacheLockException("方法参数为空，没有被锁定的对象");
        }

        //不支持多个参数加锁，只支持注解为lockedObject或者lockedComplexObject的参数

        //标记参数的位置指针
        int index = -1;
        for (int i = 0; i < annotations.length; i++) {
            for (int j = 0; j < annotations[i].length; j++) {
                if (annotations[i][j] instanceof LockedObject) {
                    index = i;
                    break;
                }

                if (annotations[i][j] instanceof LockedComplexObject) {
                    index = i;
                    String field = ((LockedComplexObject) annotations[i][j]).field();
                    try {
                        return args[i].getClass().getField(field);
                    } catch (NoSuchFieldException e) {
                        throw new CacheLockException("注解对象中没有该属性" + field);
                    }
                }
            }

            //找到第一个后直接break，不支持多参数加锁
            if (index != -1) {
                break;
            }
        }

        if (index == -1) {
            throw new CacheLockException("请指定被锁定参数");
        }

        return args[index];
    }
}
