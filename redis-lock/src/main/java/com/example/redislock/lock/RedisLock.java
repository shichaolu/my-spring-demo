package com.example.redislock.lock;

import com.example.redislock.util.RedisClient;
import com.example.redislock.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 17:37 2019/7/16
 */
@Slf4j
public class RedisLock {

    private String key;

    public RedisLock(String lockedPrefix, String objectValue) {
        this.key = lockedPrefix + objectValue;
    }

    private static final String LOCKED = "locked";
    private boolean lock;
    private RedisClient redisClient = SpringUtils.getApplicationContext().getBean(RedisClient.class);


    /**
     * @param timeout timeout的时间范围内轮询锁
     * @param expire  设置锁超时时间
     * @return 成功 or 失败
     */
    public boolean lock(long timeout, int expire) {
        long millisTime = System.currentTimeMillis();

        try {
            //在timeout的时间范围内不断轮询锁
            while (System.currentTimeMillis() - millisTime < timeout) {
                //锁不存在的话，设置锁并设置锁过期时间，即加锁
                if (redisClient.set(key, LOCKED)) {
                    //设置锁过期时间是为了在没有释放锁的情况下锁过期后消失，不会造成永久阻塞
                    redisClient.expire(key, expire);
                    lock = true;
                    return true;
                }

                log.info("出现锁等待...");
                //短暂休眠，避免可能的活锁
                Thread.sleep(3, (new Random()).nextInt(30));
            }

        } catch (Exception e) {
            throw new RuntimeException("locking error", e);
        }
        return false;
    }

    public void unlock() {
        try {
            if (lock) {
                //直接删除
                redisClient.delKey(key);
                lock = false;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
