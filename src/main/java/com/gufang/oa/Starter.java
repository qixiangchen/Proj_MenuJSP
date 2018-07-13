package com.gufang.oa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

//声明SpringBoot应用
@SpringBootApplication
//扫描Mybatis接口包
@MapperScan("com.gufang.oa.mapper")
//扫描Service，Controller包
@ComponentScan("com.gufang.oa.ctrl,com.gufang.oa.service.impl")
@EnableDubboConfiguration
public class Starter {

	public static void main(String[] args) {
		
		SpringApplication.run(Starter.class, args);

	}

}
