package com.gd.sakila.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.CustomerService;
import com.gd.sakila.service.RentalService;
import com.gd.sakila.vo.Customer;
import com.gd.sakila.vo.CustomerList;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅
@Controller // 컴포넌트로 객체가 자동으로 만들어진다. 서블릿처럼 행동하는 클래스를 상속받음
@RequestMapping("/admin")
public class CustomerController {
	
	// nullpointException이 발생 -> Autowired 에노테이션을 통해 객체를 주입 시켜준다
	@Autowired private CustomerService customerService;
	@Autowired private RentalService rentalService;
	
	// addCustomer 맵핑
	@GetMapping("addCustomer")
	public String addCustomer() {
		return "addCustomer";
	}
	
	@PostMapping("addCustomer")
	public String addCustomer(Customer customer) {
		
		// 디버깅
		log.debug("addCustomer method Param(customer) : " + customer.toString());
		
		customerService.addCustomer(customer);
		
		return "redirect:/admin/getCutsomerList";
	}
	
	// getCustomerOne 맵핑
	@GetMapping("/getCustomerOne")
	public String getCustomerOne(Model model, @RequestParam(value="customerId", required = true) int customerId,
			@RequestParam(value="sum", required = true) int sum,
			@RequestParam(value="name", required = false) String name,
			@RequestParam(value="active", required = false) Integer active,
			@RequestParam(value="storeId", required = false) Integer storeId,
			@RequestParam(value="rentalCurrentPage", defaultValue = "1") int rentalCurrentPage,
			@RequestParam(value="currentPage", defaultValue = "1") int currentPage,
			@RequestParam(value="rentalRowPerPage", defaultValue = "10") int rentalRowPerPage,
			@RequestParam(value="searchWord", required = false) String searchWord) {
		
		// 디버깅
		log.debug("getCustomerOne method Param(customerId) : " + customerId);
		log.debug("getCustomerOne method Param(sum) : " + sum);
		log.debug("getCustomerOne method Param(name) : " + name);
		log.debug("getCustomerOne method Param(active) : " + active);
		log.debug("getCustomerOne method Param(storeId) : " + storeId);
		log.debug("getCustomerOne method Param(currentPage) : " + currentPage);
		log.debug("getCustomerOne method Param(rentalCurrentPage) : " + rentalCurrentPage);
		log.debug("getCustomerOne method Param(rentalRowPerPage) : " + rentalRowPerPage);
		log.debug("getCustomerOne method Param(searchWord) : " + searchWord);
				
		// Service 호출
		CustomerList customerList= customerService.getCustomerOne(customerId);
		Map<String, Object> rentalListMap = rentalService.selectListRentalList(rentalCurrentPage, rentalRowPerPage, searchWord, customerId);
		
		// model
		model.addAttribute("getCustomerOne", customerList);
		model.addAttribute("sum", sum);
		model.addAttribute("name", name);
		model.addAttribute("active", active);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rentalCurrentPage", rentalCurrentPage);
		model.addAttribute("lastPage", rentalListMap.get("lastPage"));
		model.addAttribute("rentalList", rentalListMap.get("rentalList"));
		
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
