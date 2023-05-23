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
		helper.setText("<body>\n" +
				"\t\t<style>\n" +
				"\n" +
				"\t\t\tbody {\n" +
				"\t\t\t\theight: 100%;\n" +
				"\t\t\t\tbackground-color: #ffffff;\n" +
				"\t\t\t\tbackground: linear-gradient(60deg, rgba(255, 165, 150, 0.5) 5%, rgba(0, 228, 255, 0.35));\n" +
				"\t\t\t\tbackground-repeat: repeat,\t\t\tno-repeat,\t\t\tno-repeat;\n" +
				"\t\t\t\tbackground-position: top left,\t\tcenter center,\t\tbottom center;\n" +
				"\t\t\t\tbackground-attachment: fixed,\t\t\tfixed,\t\t\t\tfixed;\n" +
				"\t\t\t}\n" +
				"\t\t\n" +
				"\t\t\tbody{\n" +
				"\t\t\t\tcolor: #414f57;\n" +
				"\t\t\t\tfont-family: \"Source Sans Pro\", Helvetica, sans-serif;\n" +
				"\t\t\t\tfont-size: 14pt;\n" +
				"\t\t\t\tfont-weight: 300;\n" +
				"\t\t\t\tline-height: 2;\n" +
				"\t\t\t\tletter-spacing: 0.2em;\n" +
				"\t\t\t\ttext-transform: uppercase;\n" +
				"\t\t\t}\t\n" +
				"\t\t\n" +
				"\t\t\tp {\n" +
				"\t\t\t\tmargin: 0 0 1.5em 0;\n" +
				"\t\t\t\tLetter-spacing:0.2em;\n" +
				"\t\t\t}\n" +
				"\t\t\n" +
				"\t\t\th1{\n" +
				"\t\t\t\tcolor: #313f47;\n" +
				"\t\t\t\tline-height: 1.5;\n" +
				"\t\t\t\tmargin: 0 0 0.75em 0;\n" +
				"\t\t\t\ttext-decoration: none;\n" +
				"\t\t\t\tfont-size: 1.85em;\n" +
				"\t\t\t\tletter-spacing: 0.22em;\n" +
				"\t\t\t\tmargin: 0 0 0.525em 0;\n" +
				"\t\t\t}\n" +
				"\t\t\n" +
				"\t\t\t#main {\n" +
				"\t\t\t\t-moz-transform-origin: 50% 50%;\n" +
				"\t\t\t\t-webkit-transform-origin: 50% 50%;\n" +
				"\t\t\t\t-ms-transform-origin: 50% 50%;\n" +
				"\t\t\t\ttransform-origin: 50% 50%;\n" +
				"\t\t\t\t-moz-transition: opacity 1s ease, -moz-transform 1s ease;\n" +
				"\t\t\t\t-webkit-transition: opacity 1s ease, -webkit-transform 1s ease;\n" +
				"\t\t\t\t-ms-transition: opacity 1s ease, -ms-transform 1s ease;\n" +
				"\t\t\t\ttransition: opacity 1s ease, transform 1s ease;\n" +
				"\t\t\t\tpadding: 4.5em 3em 3em 3em ;\n" +
				"\t\t\t\tbackground: #ffffff50;\n" +
				"\t\t\t\tborder-radius: 10px;\n" +
				"\t\t\t\tmax-width: 100%;\n" +
				"\t\t\t\topacity: 0.95;\n" +
				"\t\t\t\tposition: relative;\n" +
				"\t\t\t\ttext-align: center;\n" +
				"\t\t\t\twidth: 27em;\n" +
				"\t\t\t}\n" +
				"\t\t\n" +
				"\t\t\t#main .avatar {\n" +
				"\t\t\t\tposition: relative;\n" +
				"\t\t\t\tdisplay: block;\n" +
				"\t\t\t\tmargin-bottom: 1.5em;\n" +
				"\t\t\t}\n" +
				"\t\n" +
				"\t\t\t#main .avatar img {\n" +
				"\t\t\t\tdisplay: block;\n" +
				"\t\t\t\twidth: 122px;\n" +
				"\t\t\t\tmargin: 0 auto;\n" +
				"\t\t\t\tborder-radius: 100%;\n" +
				"\t\t\t\tbox-shadow: 0 0 0 0.5em #ffffff47;\n" +
				"\t\t\t}\n" +
				"\t\t\n" +
				"\t\t\t#wrapper {\n" +
				"\t\t\t\tdisplay: -moz-flex;\n" +
				"\t\t\t\tdisplay: -webkit-flex;\n" +
				"\t\t\t\tdisplay: -ms-flex;\n" +
				"\t\t\t\tdisplay: flex;\n" +
				"\t\t\t\t-moz-align-items: center;\n" +
				"\t\t\t\t-webkit-align-items: center;\n" +
				"\t\t\t\t-ms-align-items: center;\n" +
				"\t\t\t\talign-items: center;\n" +
				"\t\t\t\t-moz-justify-content: space-between;\n" +
				"\t\t\t\t-webkit-justify-content: space-between;\n" +
				"\t\t\t\t-ms-justify-content: space-between;\n" +
				"\t\t\t\tjustify-content: space-between;\n" +
				"\t\t\t\t-moz-flex-direction: column;\n" +
				"\t\t\t\t-webkit-flex-direction: column;\n" +
				"\t\t\t\t-ms-flex-direction: column;\n" +
				"\t\t\t\tflex-direction: column;\n" +
				"\t\t\t\t-moz-perspective: 1000px;\n" +
				"\t\t\t\t-webkit-perspective: 1000px;\n" +
				"\t\t\t\t-ms-perspective: 1000px;\n" +
				"\t\t\t\tperspective: 1000px;\n" +
				"\t\t\t\tposition: relative;\n" +
				"\t\t\t\tmin-height: 100%;\n" +
				"\t\t\t\tpadding: 1.5em;\n" +
				"\t\t\t\tz-index: 2;\n" +
				"\t\t\t}\n" +
				"\t\t\n" +
				"\t\t\t#wrapper > * {\n" +
				"\t\t\t\tz-index: 1;\n" +
				"\t\t\t}\n" +
				"\t\n" +
				"\t\t\t#wrapper:before {\n" +
				"\t\t\t\tcontent: '';\n" +
				"\t\t\t\tdisplay: block;\n" +
				"\t\t\t}\n" +
				"\n" +
				"\t\t</style>\n" +
				"\t\t<div id=\"wrapper\">\n" +
				"\t\t\t<section id=\"main\">\n" +
				"\t\t\t\t<span class=\"avatar\"><img width= \"50px\" src=\"https://pic1.imgdb.cn/item/646c859ee03e90d874362888.png\" alt=\"\" /></span>\n" +
				"\t\t\t\t<p>尊敬的brber，您的验证码是：</p>\n" +
				"\t\t\t\t<h1>"
				+ code +
				"</h1>\n" +
				"\t\t\t\t<p>祝您BRB之旅愉快~</p>\n" +
				"\t\t\t</section>\n" +
				"\t\t</div>\n" +
				"\t</body>", true);
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