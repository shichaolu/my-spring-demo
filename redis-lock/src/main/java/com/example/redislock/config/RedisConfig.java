package com.example.redislock.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 17:58 2019/7/18
 */
@Configuration
public class RedisConfig {

    @Bean
    @Autowired
    public JedisPool jedisPool(@Qualifier("spring.redis.jedis.pool.config") JedisPoolConfig config,
                               @Value("${spring.redis.host}") String host,
                               @Value("${spring.redis.port}") int port,
                               @Value("${spring.redis.timeout}") int timeout,
                               @Value("${spring.redis.password}") String password) {
        return new JedisPool(config, host, port, timeout, password);
    }

    @Bean(name = "spring.redis.jedis.pool.config")
    public JedisPoolConfig jedisPoolConfig(@Value("${spring.redis.jedis.pool.max-active}") int maxActive,
                                           @Value("${spring.redis.jedis.pool.max-idle}") int maxIdle,
                                           @Value("${spring.redis.jedis.pool.max-wait}") int maxWaitMillis) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxActive);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        return config;
    }

}
