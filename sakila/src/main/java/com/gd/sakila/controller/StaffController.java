package com.gd.sakila.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.StaffService;
import com.gd.sakila.vo.StaffList;

import lombok.extern.slf4j.Slf4j;


@Slf4j // 디버깅
@Controller // 컴포넌트로 객체가 자동으로 만들어진다. 서블릿처럼 행동하는 클래스를 상속받음
@RequestMapping("/admin")
public class StaffController {
	
	@Autowired StaffService staffService;
	
	// 모든 직원 리스트 메서드 맵핑
	@GetMapping("/getStaffList")
	public String getStaffList(Model model, @RequestParam(value="searchWord", required = false) String searchWord) {
		// 디버깅
		log.debug("searchWord : " + searchWord);
		
		// Staff 정보 리스트 가져온다
		List<StaffList> staffList = staffService.getStaffList(searchWord);
		// 디버깅
		log.debug("staffList : " + staffList.toString());
		
		model.addAttribute("staffList", staffList);
		
		return "getStaffList";
	}
	
	// 한 직원의 정보 출력 메서드
	@GetMapping("/getStaffOne")
	public String getStaffOne(Model model, @RequestParam(value="staffId", required = true) int staffId) {
		// 디버깅
		log.debug("getStaffOne의 staffId : " + staffId);
		
		StaffList staffOne = staffService.getStaffOne(staffId);
		// 디버깅
		log.debug("staffOne : " + staffOne.toString());
		
		model.addAttribute("staffOne",staffOne);
		
		return "getStaffOne";
	}
}
