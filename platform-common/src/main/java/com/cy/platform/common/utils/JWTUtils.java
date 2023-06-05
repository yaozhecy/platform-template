package com.cy.platform.common.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
public class JWTUtils {
    public static final long EXPIRE = (long) 1000 * 60 * 60 * 24;
    public static final String SECRET = "platform";

    public static String getToken(Consumer<JwtBuilder> consumer) {
        JwtBuilder builder = Jwts.builder()
            //JWT头信息
            .setHeaderParam("typ", "JWT")
            .setHeaderParam("alg", "HS2256")
            //设置分类；设置过期时间 一个当前时间，一个加上设置的过期时间常量
            .setSubject("account-info")
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRE));

        //扩展设置值
        consumer.accept(builder);

        //设置token主体信息，存储用户信息
        return builder.signWith(SignatureAlgorithm.HS256, SECRET)
            .compact();
    }

    /**
     * 解析Token信息
     *
     * @param token 信息
     * @return 解析后信息
     */
    public static Optional<Jws<Claims>> parseToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return Optional.empty();
        }

        try {
            return Optional.of(Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token));
        } catch (Exception e) {
            log.warn("Token 验证失败");
        }
        return Optional.empty();
    }
}
