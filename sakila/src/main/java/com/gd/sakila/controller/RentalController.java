package com.gd.sakila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.RentalService;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅
@Controller // 컴포넌트로 객체가 자동으로 만들어진다. 서블릿처럼 행동하는 클래스를 상속받음
@RequestMapping("/admin")
public class RentalController {
	
	@Autowired private RentalService rentalService;
	
	@PostMapping("/addRental")
	public String addRental(@RequestParam(value="currentPage", required = false) int currentPage,
			 @RequestParam(value="name", required = false) String name,
			 @RequestParam(value="storeId", required = false) String storeId,
			 @RequestParam(value="active", required = false) String active,
			 @RequestParam(value="categoryName", required = true) String categoryName,
			 @RequestParam(value="filmId", required = true) int filmId,
			 @RequestParam(value="inventoryId", required = true) Integer inventoryId,
			 @RequestParam(value="customerId", required = true) int customerId,
			 @RequestParam(value="sum", required = true) int sum) {
		
		// debug
		log.debug("addRental param(sum) : " + sum);
		log.debug("addRental param(currentPage) : " + currentPage);
		log.debug("addRental param(name) : " + name);
		log.debug("addRental param(storeId) : " + storeId);
		log.debug("addRental param(active) : " + active);
		log.debug("addRental param(categoryName) : " + categoryName);
		log.debug("addRental param(filmId) : " + filmId);
		log.debug("addRental param(inventoryId) : " + inventoryId);
		log.debug("addRental param(customerId) : " + customerId);
		
		// Service 호출
		int addRow = rentalService.addRental(inventoryId, customerId);
		log.debug("addRental param(addRow) : " + addRow);
		
		return "redirect:/admin/getCustomerOne?customerId="+customerId+"&sum="+sum+"&name="+name+"&currentPage="+currentPage+"&sum="+storeId+"&active="+active;
	}
}
