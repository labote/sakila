package com.gd.sakila;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan // @WebFilter를 스캔하기 위해 사용
@SpringBootApplication
@EnableScheduling // Schedule를 사용하기 위해 사용, 실행되면 Schedule를 찾아 실행시켜준다
//@ComponentScan("com.gd.sakila") // Default 값으로 들어가 있음. com.gd.sakila가 기본 범위. 범위를 벗어나면 스캔 불가. 범위를 지정해줘야한다.
public class SakilaApplication {

	public static void main(String[] args) { // spring 시작
		SpringApplication.run(SakilaApplication.class, args);
		// 시작하면 @Controller, @Mapper 등등 찾음(componentScan)
	}
}
