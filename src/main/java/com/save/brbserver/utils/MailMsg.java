package com.save.brbserver.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.Duration;

/**
 * @Author:Zzs
 * @DateTime: 2023/05/22 10:30
 * @Description: 生成验证码
 */

@Component
@Getter
public class MailMsg {
	
	@Resource
	private JavaMailSenderImpl mailSender;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	public boolean mail (String email) throws MessagingException {
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		String code = CodeGeneratorUtil.generateCode(6);
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		//设置一个html邮件信息
		helper.setText("<p style='color: blue'>尊敬的brber，您好！你的验证码为：<u>" + code + "</u>(有效期为五分钟)</p>", true);
		//设置邮件主题名
		helper.setSubject("brb--注册验证码");
		//发给谁-邮箱地址
		helper.setTo(email);
		//谁发的-发送人邮箱
		helper.setFrom("2476594519@qq.com");
		//将邮箱验证码以邮件地址为key存入redis,5分钟过期
		redisTemplate.opsForValue().set(email, code, Duration.ofMinutes(1)); //redis
		mailSender.send(mimeMessage);
		return true;
	}
}