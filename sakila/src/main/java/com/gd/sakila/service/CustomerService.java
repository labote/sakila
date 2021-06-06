package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.CustomerMapper;
import com.gd.sakila.vo.Customer;
import com.gd.sakila.vo.PageParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅
@Service // 붙어있어야 객체가 만들어짐
@Transactional // spring에 트랜잭션기능이 있다. 어떤 메서드를 실행하다가 에러가뜨면 그 메서드가 있는 서비스 롤백
public class CustomerService {
	
	// spring에는 Mapper에 객체 주입하는 기능이 있음(의존성 주입 = Dependency Injection)
	@Autowired private CustomerMapper customerMapper;
	
	// Scheduler
	public void modifyCustomerActiveByScheduler() {
		// 디버깅
		log.debug("modifyCustomerActiveByScheduler() 실행");
		
		int row = customerMapper.updateCustomerActiveByScheduler();
		// 디버깅
		log.debug("modifyCustomerActiveByScheduler 실행 여부 : " + row);
	}
	
	// 고객 명단 리스트
	public Map<String, Object> getCustomerList(String name, Integer storeId, Integer active, int currentPage, int rowPerPage){
		
		// pageParam 선언 및 초기화
		int beginRow = (currentPage -1) * rowPerPage;
		PageParam pageParam = new PageParam();
		pageParam.setBeginRow(beginRow);
		pageParam.setRowPerPage(rowPerPage);
		
		// 여러 param을 받기 위해 Map 사용
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", name);
		paramMap.put("storeId", storeId);
		paramMap.put("active", active);
		paramMap.put("beginRow", pageParam.getBeginRow());
		paramMap.put("rowPerPage", pageParam.getRowPerPage());
		
		// dao 호출
		List<Customer> customerList = customerMapper.selectCustomerList(paramMap);
		int customerTotal = customerMapper.selectCustomerTotal(paramMap);
		
		log.debug("getCustomerList의 customerList : " + customerList.toString());
		log.debug("getCustomerList의 customerTotal : " + customerTotal);

		// lastPage
		int lastPage = (int)Math.ceil((double)customerTotal/rowPerPage);
		
		// Map을 이용해 두 리스트를 Map 안에 넣어준다.
		Map<String,Object> returnMap = new HashMap<String, Object>();
		
		returnMap.put("lastPage", lastPage);
		returnMap.put("customerList", customerList);
		
		return returnMap;
	}
}
