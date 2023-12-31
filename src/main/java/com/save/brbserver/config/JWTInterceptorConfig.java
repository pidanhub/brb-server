package com.save.brbserver.config;

import com.save.brbserver.interceptors.JWTInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author:Zzs
 * @Description: jwt
 * @DateTime: 2023/5/8
 **/

@Configuration
public class JWTInterceptorConfig implements WebMvcConfigurer {
	@Bean
	public JWTInterceptor jwtInterceptor () {
		return new JWTInterceptor();
	}
	
	@Override
	public void addInterceptors (InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor())
				.addPathPatterns("/**") // 拦截的请求
				.excludePathPatterns("/user/login")
				.excludePathPatterns("/images/**")
				.excludePathPatterns("/mail/register-code")
				.addPathPatterns("/error")
				.excludePathPatterns("/user/register"); // 不拦截的请求  如登录接口
	}
}
