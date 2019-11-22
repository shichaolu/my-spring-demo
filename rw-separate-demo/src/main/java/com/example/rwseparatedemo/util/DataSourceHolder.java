package com.example.rwseparatedemo.util;

import com.example.rwseparatedemo.enums.DBTypeEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 10:47 2019/7/10
 */
@Slf4j
public class DataSourceHolder {

    private static final ThreadLocal<DBTypeEnum> DB_THREAD_LOCAL = new ThreadLocal<>();

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
        set(DBTypeEnum.SLAVE1);
        log.info("切换到slave1");
    }

}
