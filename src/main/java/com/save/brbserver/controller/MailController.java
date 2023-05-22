package com.save.brbserver.controller;

import com.save.brbserver.customexception.FormatException;
import com.save.brbserver.entity.ResponseEntity;
import com.save.brbserver.service.UserService;
import com.save.brbserver.utils.MailMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/5/22 11:47
 **/

@RestController
@RequestMapping ("/mail")
public class MailController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private MailMsg mailMsg;
	
	@PostMapping ("/register-code")
	public ResponseEntity<?> sendCode (@RequestParam ("email") String email) {
		try {
			if (!email.matches("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$"))
				throw new FormatException();
			String code = mailMsg.getRedisTemplate().opsForValue().get(email);
			if (code != null) {
				return new ResponseEntity<>(ResponseEntity.VERIFY_CODE_EXIST, null, "验证码已经存在且未过期");
			}
			boolean mail = mailMsg.mail(email);
			if (mail) {
				return new ResponseEntity<>(ResponseEntity.SUCCESS, null, "验证码发送成功");
			}
			
		} catch (FormatException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FORMAT_NOT_RIGHT, null, "邮箱格式错误");
		} catch (MessagingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.EMAIL_SEND_FAILED, null, "邮件发送失败");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(ResponseEntity.FAILED, null, "未知错误");
	}
	
	//验证码登录，待开发
}
