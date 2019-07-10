package com.example.myspringdemo.config;

import com.example.myspringdemo.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 10:48 2019/7/9
 */
@Configuration
public class MyConfiguration {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(authenticationInterceptor())
                        .addPathPatterns("/**");
            }
        };
    }

    private HandlerInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

}
