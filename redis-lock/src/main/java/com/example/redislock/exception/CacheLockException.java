package com.example.redislock.exception;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 17:20 2019/7/16
 */
public class CacheLockException extends RuntimeException{

    public CacheLockException(String message) {
        super(message);
    }
}
