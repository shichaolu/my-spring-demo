package com.example.redislock.service;

import com.example.redislock.annotation.CacheLock;
import com.example.redislock.annotation.LockedObject;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 18:00 2019/7/16
 */
public interface SecKill {

    @CacheLock(lockedPrefix = "TEST_PREFIX_")
    void seckill(String userId, @LockedObject Long productId);

}
