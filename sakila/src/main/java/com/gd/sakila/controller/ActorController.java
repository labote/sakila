package com.gd.sakila.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.ActorService;
import com.gd.sakila.vo.Actor;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅
@Controller // 컴포넌트로 객체가 자동으로 만들어진다. 서블릿처럼 행동하는 클래스를 상속받음
@RequestMapping("/admin")
public class ActorController {
	
	@Autowired private ActorService actorService;
	
	// insert 맵핑
	@GetMapping("/addActor")
	public String addActor(Model model, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
							@RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage,
							@RequestParam(value = "searchWord", required = false) String searchWord) {
		
		log.debug("addActor(Controller) currentPage : " + currentPage);
		log.debug("addActor(Controller) rowPerPage : " + rowPerPage);
		log.debug("addActor(Controller) searchWord : " + searchWord);
		
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		
		return "addActor";
	}
	
	// insert 맵핑
	@PostMapping("/addActor")
	public String addActor(Actor actor) {
		
		log.debug("addActor param(actor) : " + actor.toString());
		
		actorService.addActor(actor);
		
		return "redirect:/admin/getActorList";
	}
	
	// getActorList 맵핑
	@GetMapping("/getActorList") // actor_view
	public String getActorList(Model model, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage, 
											@RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage,
											@RequestParam(value = "searchWord", required = false) String searchWord) {
		
		// debug
		log.debug("getActorList(Controller) currentPage : " + currentPage);
		log.debug("getActorList(Controller) rowPerPage : " + rowPerPage);
		log.debug("getActorList(Controller) serachWord : " + searchWord);
		
		// Service에서 Map형태로 가져오기 때문에 Map으로 받는다.
		Map<String, Object> ActorMap = actorService.getActorList(searchWord, currentPage, rowPerPage);
		log.debug("getActorList(Controller) ActorMap : " + ActorMap.toString());
		
		// addAttribute 함수를 통해 파라미터들을 view에 넘겨준다.
		model.addAttribute("lastPage", ActorMap.get("lastPage"));
		model.addAttribute("actorList", ActorMap.get("actorList"));
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("currentPage", currentPage);
		
		return "getActorList";
	}
	
}
