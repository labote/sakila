package com.gd.sakila.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.CountryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller // 컴포넌트로 객체가 자동으로 만들어진다. 서블릿처럼 행동하는 클래스를 상속받음, new ControllerMapper -> new CountryService
@RequestMapping("/admin")
public class CountryController {
	
	@Autowired // nullpointException이 발생 -> Autowired 에노테이션을 통해 객체를 주입 시켜준다
	private CountryService countryService;
	
	@GetMapping("/countryList")
	public String countryList(Model model, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage, @RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage) { // setAttribute 역할 : Model, 문자열 형변환 -> spring이 대신 해줌
		Map<String, Object> map = countryService.getCountryList(currentPage, rowPerPage);
		// 디버깅
		System.out.println("map : " + map.toString());
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage",currentPage);
		return "countryList"; // 디폴트값이 포워딩으로 되어있음
	}
}
