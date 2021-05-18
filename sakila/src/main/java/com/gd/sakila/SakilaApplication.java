package com.gd.sakila;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan // @WebFilter를 스캔하기 위해 사용
@SpringBootApplication
public class SakilaApplication {

	public static void main(String[] args) { // spring 시작
		SpringApplication.run(SakilaApplication.class, args);
		// 시작하면 @Controller, @Mapper 등등 찾음(componentScan)
	}

}
