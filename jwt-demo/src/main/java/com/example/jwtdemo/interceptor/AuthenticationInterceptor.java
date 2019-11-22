package com.example.jwtdemo.interceptor;

import com.example.jwtdemo.annotation.LoginToken;
import com.example.jwtdemo.annotation.PassToken;
import com.example.jwtdemo.error.ApiException;
import com.example.jwtdemo.util.JwtUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 10:59 2019/7/9
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(JwtUtil.authorization);

        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        if (method.isAnnotationPresent(LoginToken.class)) {
            LoginToken loginToken = method.getAnnotation(LoginToken.class);
            if (loginToken.required()) {
                if (token == null) {
                    throw new ApiException("您还未登录");
                }

                return JwtUtil.verifyToken(token);
            }
        }
        return false;
    }
}
