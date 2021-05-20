package com.gd.sakila.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gd.sakila.service.StaffService;
import com.gd.sakila.vo.Staff;

import lombok.extern.slf4j.Slf4j;

@Slf4j // Logger log = LoggerFactory.getLogger(this.getClass()); // (HomeController.class) 자동으로 만들어준다
@Controller
public class HomeController {
	// private final Logger log = LoggerFactory.getLogger(this.getClass()); // (HomeController.class)
	
	@Autowired private StaffService staffService;
	
	@GetMapping({"/", "/home", "/index"})
	public String home() {
		// System.out.println("home...");
		log.debug("test");
		return "home";
	}
	
	@GetMapping("/admin/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login(HttpSession session, Staff staff) { // 매개변수로 들어간건 스프링이 넣어주어야한다. , servlet 세션을 직접 사용, Controller 메서드의 매개변수는 DI대상이다.
		
		log.debug("login 메서드에 들어온 staff param : " + staff.toString());
		
		// 받은 staff 객체를 이용해 login 시도
		Staff loginStaff = staffService.login(staff);
		log.debug("login 한 loginStaff 정보 : " + loginStaff);
		
		// 로그인 성공
		if(loginStaff != null) {
			log.debug("login 한 loginStaff 정보 : " + loginStaff.toString());
			session.setAttribute("loginStaff", loginStaff);
		}
		
		return "redirect:/";
	}
}
