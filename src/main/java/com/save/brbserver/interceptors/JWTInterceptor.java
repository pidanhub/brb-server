package com.save.brbserver.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.save.brbserver.entity.ResponseEntity;
import com.save.brbserver.entity.TokenEntity;
import com.save.brbserver.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Zzs
 * @Description: jwt
 * @DateTime: 2023/5/8
 **/

@Data
@Slf4j
@Component
@ConfigurationProperties (prefix = "jwt")
public class JWTInterceptor implements HandlerInterceptor {
    
    private static String secret;
    
    @Value ("${jwt.secret}")
    private void setSecret (String secret) {
        JWTInterceptor.secret = secret;
    }
    
    @Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> map = new HashMap<>();
        //获取请求头里的token
        String token = request.getHeader("Authorization");
        try {
            //验证令牌
            JWTUtils.verify(token);
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                        .parseClaimsJws(token).getBody();
                
                if (claims.get("type").equals("refresh")) {
                    Map<String, String> tokenMap = fetchAndReturnNewMap(claims);
//                log.info(map.toString());
                    assert tokenMap != null;
                    TokenEntity tokenEntity = JWTUtils.getToken(tokenMap);
//                    log.info(tokenEntity.getToAuthentication());
//                    log.info(tokenEntity.getToRefresh());
                    response.setHeader("Authorization-to-request", tokenEntity.getToAuthentication());
                    response.setHeader("Authorization-to-refresh", tokenEntity.getToRefresh());
//                    log.info("success to set header");
                }
                else
                    response.setHeader("Authorization", request.getHeader("Authorization"));
            } catch (Exception e) {
                e.printStackTrace();
                ResponseEntity<?> responseEntity = new ResponseEntity<>(ResponseEntity.NEED_TOKEN, null, "token不合法！");
                PrintWriter writer = response.getWriter();
                writer.write(String.valueOf(responseEntity));
                writer.flush();
                return false;
            }
            return true;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("state", ResponseEntity.FAILED);
            map.put("msg", "无效签名");
        } catch (TokenExpiredException e) {
            // 过期异常,需要重新生成并返回给用户，在这里通知客户端需要发送用于刷新的token
            e.printStackTrace();
            map.put("state", ResponseEntity.TOKEN_OUT_OF_TIME);
            map.put("msg", "token过期");
        } catch (AlgorithmMismatchException e) {//算法不匹配
            e.printStackTrace();
            map.put("state", ResponseEntity.FAILED);
            map.put("msg", "token算法不一致");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", ResponseEntity.FAILED);
            map.put("msg", "token无效");
        }
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws IOException {
//
//    }
    
    private Map<String, String> fetchAndReturnNewMap (Claims claims) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("id", claims.get("id").toString());
            map.put("username", claims.get("username").toString());
            map.put("e-mail", claims.get("e-mail").toString());
            map.put("introduction", claims.get("introduction").toString());
            map.put("head-sculpture-path", claims.get("head-sculpture-path").toString());
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

