package com.example.redislock.service.impl;

import com.example.redislock.service.SecKill;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 17:01 2019/7/19
 */
public class SecKillImpl implements SecKill {

    public static Map<Long, Long> inventory;

    static {
        inventory = new HashMap<>();
        inventory.put(10000001L, 10000L);
        inventory.put(10000002L, 10000L);
    }

    @Override
    public void seckill(String userId, Long productId) {
        reduceInventory(productId);
    }

    /**
     * 模拟秒杀操作，姑且认为一个秒杀就是将库存减一，实际情景要复杂的多
     */
    private void reduceInventory(Long productId) {
        inventory.put(productId, inventory.get(productId) - 1);
    }
}
