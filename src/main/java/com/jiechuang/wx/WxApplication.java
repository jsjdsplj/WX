package com.jiechuang.wx;

import org.apache.http.impl.bootstrap.HttpServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jersey.JerseyProperties;

import javax.servlet.Servlet;

@SpringBootApplication
@MapperScan(basePackages = "com.jiechuang.wx.dataobject.mapper")
public class WxApplication {


	public static void main(String[] args) {
		SpringApplication.run(WxApplication.class, args);
	}
}
