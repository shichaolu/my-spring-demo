package com.example.redislock;

import com.example.redislock.interceptor.CacheLockInterceptor;
import com.example.redislock.service.SecKill;
import com.example.redislock.service.impl.SecKillImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Proxy;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisLockApplicationTests {

    @Test
    public void testSecKill() {
        int threadCount = 1000;
        int splitPoint = 500;

        CountDownLatch beginCount = new CountDownLatch(1);
        CountDownLatch endCount = new CountDownLatch(threadCount);
        SecKillImpl secKillImpl = new SecKillImpl();

        Thread[] threads = new Thread[threadCount];
        //起500个线程，秒杀第一个商品
        for (int i = 0; i < splitPoint; i++) {
            threads[i] = new Thread(() -> {
                //等待在一个信号量上，挂起
                try {
                    beginCount.await();

                    //用动态代理的方式调用secKill方法
                    SecKill proxy = (SecKill) Proxy.newProxyInstance(SecKill.class.getClassLoader(), new Class[]{SecKill.class}, new CacheLockInterceptor(secKillImpl));
                    proxy.seckill("test", 10000001L);

                    endCount.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });

            threads[i].start();
        }

        //再起500个线程，秒杀第二件商品
        for (int i = 0; i < splitPoint; i++) {
            threads[i] = new Thread(() -> {
                //等待在一个信号量上，挂起
                try {
                    beginCount.await();

                    //用动态代理的方式调用secKill方法
                    SecKill proxy = (SecKill) Proxy.newProxyInstance(SecKill.class.getClassLoader(), new Class[]{SecKill.class}, new CacheLockInterceptor(secKillImpl));
                    proxy.seckill("test", 10000002L);

                    endCount.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });

            threads[i].start();
        }

        long startTime = System.currentTimeMillis();
        //主线程释放开始信号量，并等待结束信号量，这样做保证1000个线程做到完全同时执行，保证测试的正确性
        beginCount.countDown();
        //主线程等待结束信号量
        try {
            endCount.await();
            //观察秒杀结果是否正确
            log.info("remain1:[{}]", SecKillImpl.inventory.get(10000001L));
            log.info("remain2:[{}]", SecKillImpl.inventory.get(10000002L));
            log.info("error count:[{}]", CacheLockInterceptor.ERROR_COUNT);
            long endTime = System.currentTimeMillis();
            log.info("cost time:[{}]ms", (endTime - startTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
