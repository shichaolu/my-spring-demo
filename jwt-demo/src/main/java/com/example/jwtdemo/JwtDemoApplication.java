package com.example.jwtdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class JwtDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtDemoApplication.class, args);
    }

}
