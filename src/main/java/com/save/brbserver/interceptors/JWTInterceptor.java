package com.save.brbserver.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.save.brbserver.customexception.MySecurityException;
import com.save.brbserver.dao.UserDao;
import com.save.brbserver.entity.ResponseEntity;
import com.save.brbserver.entity.TokenEntity;
import com.save.brbserver.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.SocketException;
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
    @Autowired
    private UserDao userDao;
    
    @Value ("${jwt.secret}")
    private void setSecret (String secret) {
        JWTInterceptor.secret = secret;
    }
    
    @Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String token = request.getHeader("Authorization");
        log.info(request.getRequestURI());
        try {
	        if (request.getRequestURI().equals("/error"))
		        throw new Exception("bad method");
	        String usernameOrEmail = request.getParameter("username");
//            log.info(userDao.getUserLoginStatus(usernameOrEmail).toString());
	        Boolean is = userDao.getUserLoginStatus(usernameOrEmail);
	        if (is == null || !is)
		        throw new MySecurityException();
	        JWTUtils.verify(token);
	        Claims claims = Jwts.parser()
			        .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
			        .parseClaimsJws(token).getBody();
	        //debug
//	        log.info(claims.get("e-mail").toString());
//	        log.info(claims.get("username").toString());
//	        log.info(usernameOrEmail);
	        if (!(claims.get("username").equals(usernameOrEmail) || claims.get("e-mail").equals(usernameOrEmail)))
		        throw new MySecurityException("Dangerous Request!");
	
	        if (claims.get("type").equals("refresh")) {
		        Map<String, String> tokenMap = fetchAndReturnNewMap(claims);
		        assert tokenMap != null;
		        TokenEntity tokenEntity = JWTUtils.getToken(tokenMap);
		        response.setHeader("Authorization-to-request", tokenEntity.getToAuthentication());
		        response.setHeader("Authorization-to-refresh", tokenEntity.getToRefresh());
	        }
	        else
                response.setHeader("Authorization", request.getHeader("Authorization"));
            return true;
        } catch (MySecurityException e) {
            e.printStackTrace();
            map.put("code", ResponseEntity.DANGEROUS);
            map.put("message", "不安全的请求");
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("code", ResponseEntity.FAILED);
            map.put("message", "无效签名");
        } catch (TokenExpiredException e) {
            // 过期异常,需要重新生成并返回给用户，在这里通知客户端需要发送用于刷新的token
            e.printStackTrace();
	        map.put("code", ResponseEntity.TOKEN_OUT_OF_TIME);
	        map.put("message", "token过期");
            response.setHeader("Lapsed", "true");
        } catch (AlgorithmMismatchException e) {//算法不匹配
	        e.printStackTrace();
	        map.put("code", ResponseEntity.FAILED);
	        map.put("message", "token算法不一致");
        } catch (NullPointerException e) {// 403 forbidden
	        e.printStackTrace();
	        map.put("code", ResponseEntity.NO_TOKEN);
	        map.put("message", "未携带token，不予处理");
        } catch (SocketException e) {
	        e.printStackTrace();
	        map.put("code", ResponseEntity.NETWORK_UNREACHABLE);
	        map.put("message", "目标网络不可达（本地测试）");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", ResponseEntity.METHOD_NOT_ALLOW);
	        map.put("message", "方法不允许");
        }
    
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }

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

