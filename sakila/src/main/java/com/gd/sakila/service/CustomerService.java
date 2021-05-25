package com.gd.sakila.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.CustomerMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅
@Service // 붙어있어야 객체가 만들어짐
@Transactional // spring에 트랜잭션기능이 있다. 어떤 메서드를 실행하다가 에러가뜨면 그 메서드가 있는 서비스 롤백
public class CustomerService {
	
	// spring에는 Mapper에 객체 주입하는 기능이 있음(의존성 주입 = Dependency Injection)
	@Autowired private CustomerMapper customerMapper;
	
	public void modifyCustomerActiveByScheduler() {
		// 디버깅
		log.debug("modifyCustomerActiveByScheduler() 실행");
		
		int row = customerMapper.updateCustomerActiveByScheduler();
		// 디버깅
		log.debug("modifyCustomerActiveByScheduler 실행 여부 : " + row);
	}
}
