package com.gd.sakila.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j // Logger log = LoggerFactory.getLogger(this.getClass()); // (HomeController.class) 자동으로 만들어준다
@Controller
public class HomeController {
	// private final Logger log = LoggerFactory.getLogger(this.getClass()); // (HomeController.class)
	
	@GetMapping({"/", "/home", "/index"})
	public String home() {
		// System.out.println("home...");
		log.debug("test");
		return "home";
	}
}
