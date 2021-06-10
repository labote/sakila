package com.gd.sakila.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅
@Controller // 컴포넌트로 객체가 자동으로 만들어진다. 서블릿처럼 행동하는 클래스를 상속받음
@RequestMapping("/admin")
public class CustomerController {
	
	// nullpointException이 발생 -> Autowired 에노테이션을 통해 객체를 주입 시켜준다
	@Autowired private CustomerService customerService;
	
	// getCustomerOne 맵핑
	@GetMapping("/getCustomerOne")
	public String getCustomerOne(Model model, @RequestParam(value="customerId", required = true) int customerId) {
		// 디버깅
		log.debug("getCustomerOne method Param(customerId) : " + customerId);
		
		// Service 호출
		Map<String, Object> returnMap = customerService.getCustomerOne(customerId);
		
		// model
		model.addAttribute("getCustomerOne", returnMap);
		
		return "getCustomerOne";
	}
	
	// getCustomerList 맵핑
	@GetMapping("/getCustomerList")
	public String getCustomerList(Model model, @RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "storeId", required = false) Integer storeId, // null 때문에 Double 사용(double x)
			@RequestParam(name = "active", required = false) Integer active,
			@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) {

		// 이름 선택시 공백 -> null로 수정
		if (name != null && name.equals("")) {
			name = null;
		}

		// 디버깅
		log.debug("getCustomerList method Param(name) : " + name);
		log.debug("getCustomerList method Param(storeId) : " + storeId);
		log.debug("getCustomerList method Param(active) : " + active);
		log.debug("getCustomerList method Param(currentPage) : " + currentPage);
		log.debug("getCustomerList method Param(rowPerPage) : " + rowPerPage);

		Map<String, Object> map = customerService.getCustomerList(name, storeId, active, currentPage, rowPerPage);
		log.debug("map : " + map.toString());

		model.addAttribute("name", name);
		model.addAttribute("storeId", storeId);
		model.addAttribute("active", active);
		model.addAttribute("customerList", map.get("customerList"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		return "getCustomerList";
	}
}
