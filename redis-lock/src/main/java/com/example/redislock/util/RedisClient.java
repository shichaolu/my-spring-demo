package com.example.redislock.util;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 17:51 2019/7/18
 */
@Component
public class RedisClient {

    private final JedisPool jedisPool;

    public RedisClient(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public boolean set(String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.get(key) == null) {
                jedis.set(key, value);
                return true;
            }

            return false;
        } finally {
            assert jedis != null;
            jedis.close();
        }
    }

    public void expire(String key, int expire) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.expire(key, expire);
        } finally {
            assert jedis != null;
            jedis.close();
        }
    }

    public void delKey(String key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } finally {
            assert jedis != null;
            jedis.close();
        }
    }

}
