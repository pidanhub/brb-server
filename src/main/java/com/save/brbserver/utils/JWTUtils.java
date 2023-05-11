package com.save.brbserver.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.save.brbserver.entity.TokenEntity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Map;

/**
 * @Author:Zzs
 * @Description: jwt factory
 * @DateTime: 2023/5/8
 **/

@Data
@Component
@ConfigurationProperties (prefix = "jwt")
@Slf4j
public class JWTUtils {
    
    private static String secret;
    
    @Value ("${jwt.secret}")
    private void setSecret (String secret) {
        JWTUtils.secret = secret;
    }
    
    /**
     * 生成token header payload sign
     * 按照传送进来的用户的map构造payload
     * map当中不应该含隐私信息，应该去除
     * 根据生成计划，用于认证的token时间为1天，用于刷新的token时间为7天
     *
     * @return 返回值为封装有两个token的实体类
     * @type 区分的规则是jwt中的type字段，可能用不到但是需要标识
     */
    public static <T> TokenEntity getToken (Map<String, String> map) throws NullPointerException {
        Calendar instance1 = Calendar.getInstance();
        instance1.add(Calendar.DATE, 1);
        Calendar instance2 = Calendar.getInstance();
        instance2.add(Calendar.DATE, 7);
        //创建jwt
        JWTCreator.Builder builderToAuthentication = JWT.create();
        JWTCreator.Builder builderToRefresh = JWT.create();
        map.forEach((k, v) -> {
            builderToAuthentication.withClaim(k, v);
            builderToRefresh.withClaim(k, v);
        });
        builderToAuthentication.withClaim("type", "authentication");
        builderToRefresh.withClaim("type", "refresh");
        String token1 = builderToAuthentication.withExpiresAt(instance1.getTime())
                .sign(Algorithm.HMAC256(secret));
        String token2 = builderToRefresh.withExpiresAt(instance2.getTime())
                .sign(Algorithm.HMAC256(secret));
//        log.info(secret);
//        log.info(token1);
        return new TokenEntity(token1, token2);
    }
    
    /**
     * 验证token 合法性
     *
     * @param token
     */
    public static void verify (String token) throws NullPointerException {
        
        JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
    }
    
}

