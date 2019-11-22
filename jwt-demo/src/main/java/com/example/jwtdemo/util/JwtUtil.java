package com.example.jwtdemo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.jwtdemo.entity.User;
import com.example.jwtdemo.error.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 11:31 2019/7/9
 */
@Component
@Slf4j
public class JwtUtil {

    private static String secret;
    private static long expire;
    public static String authorization;

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        JwtUtil.secret = secret;
    }

    @Value("${jwt.expire}")
    public void setExpire(long expire) {
        JwtUtil.expire = expire;
    }

    @Value("${jwt.token-header}")
    public void setAuthorization(String authorization) {
        JwtUtil.authorization = authorization;
    }

    /**
     * 加密生成token
     *
     * @param user 载体信息
     * @return
     */
    public static String createToken(User user) {
        try {
            //生成签名
            final Algorithm algorithm = Algorithm.HMAC256(JwtUtil.secret);
            String token = JWT.create()
                    //签发人
                    .withIssuer("签发者")
                    //主题
                    .withSubject("用户")
                    .withClaim("id", user.getId())
                    .withClaim("username", user.getUsername())
                    .withClaim("password", user.getPassword())
                    .withExpiresAt(new Date(System.currentTimeMillis() + JwtUtil.expire))
                    .sign(algorithm);
            log.debug(token);
            return Base64.getEncoder().encodeToString(token.getBytes(StandardCharsets.UTF_8));

        } catch (Exception e) {
            log.error("生成token异常：", e);
        }

        return null;
    }

    /**
     * 解析验证token
     *
     * @param token 加密后的token字符串
     * @return
     */
    public static boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JwtUtil.secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8));
            return true;
        } catch (JWTVerificationException e) {
            throw new ApiException(e.getMessage());
        }
    }
}
