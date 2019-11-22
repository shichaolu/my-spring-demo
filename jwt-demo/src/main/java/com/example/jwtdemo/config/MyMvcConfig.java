package com.example.jwtdemo.config;

import com.example.jwtdemo.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 10:48 2019/7/9
 */
@Configuration
public class MyMvcConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new AuthenticationInterceptor())
                        .addPathPatterns("/**");
            }
        };
    }

}
