package com.example.redislock.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 17:21 2019/6/22
 */
@Component
public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        if (SpringUtils.applicationContext == null) {
            SpringUtils.applicationContext = arg0;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

}
