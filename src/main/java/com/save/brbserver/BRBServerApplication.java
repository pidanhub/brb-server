package com.save.brbserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author:Zzs
 * @Description: BRB计划——流浪动物救助行动，详情见README.md
 * @DateTime: 2023/4/24 13:37
 **/
@SpringBootApplication
@MapperScan ("com.save.brbserver.dao")
public class BRBServerApplication {
	
	public static void main (String[] args) {
		SpringApplication.run(BRBServerApplication.class, args);
	}
	
}
