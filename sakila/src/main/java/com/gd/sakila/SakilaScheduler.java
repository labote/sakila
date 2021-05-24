package com.gd.sakila;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gd.sakila.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅
@Component
public class SakilaScheduler {
	
	@Autowired CustomerService customerService;
	
	// 0 20 11 : 11시 20분, 24 : 24일, * : 매달, * : 요일은 상관없음
	@Scheduled(cron = "0 27 11 24 * *") // 스케줄러 메서드 규칙 : void, 매개변수x
	public void modifyCustomerActive() {
		customerService.modifyCustomerActiveByScheduler();
		log.debug("modifyCustomerActive 스케줄러 실행 완료");
	}
}
