package com.example.proxydemo;

import com.example.proxydemo.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class ProxyDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ProxyDemoApplication.class, args);
        UserService userService = context.getBean(UserService.class);
        userService.work();
    }

}
