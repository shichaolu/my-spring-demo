package com.example.myspringdemo.util;

import com.example.myspringdemo.enums.DBTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 10:47 2019/7/10
 */
@Slf4j
public class DataSourceHolder {

    private static final ThreadLocal<DBTypeEnum> DB_THREAD_LOCAL = new ThreadLocal<>();
    private static final AtomicInteger COUNTER = new AtomicInteger(-1);

    private static void set(DBTypeEnum dbType) {
        DB_THREAD_LOCAL.set(dbType);
    }

    public static DBTypeEnum get() {
        return DB_THREAD_LOCAL.get();
    }

    public static void remove() {
        DB_THREAD_LOCAL.remove();
    }

    public static void master() {
        set(DBTypeEnum.MASTER);
        log.info("切换到master");
    }

    public static void slave() {
        //  轮询
        int index = COUNTER.getAndIncrement() % 2;
        if (COUNTER.get() > 9999) {
            COUNTER.set(-1);
        }

        if (index == 0) {
            set(DBTypeEnum.SLAVE1);
            log.info("切换到slave1");
        } else {
            set(DBTypeEnum.SLAVE2);
            log.info("切换到slave2");
        }
    }

}
