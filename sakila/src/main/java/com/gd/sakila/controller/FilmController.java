package com.gd.sakila.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.FilmService;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅
@Controller // 컴포넌트로 객체가 자동으로 만들어진다. 서블릿처럼 행동하는 클래스를 상속받음
@RequestMapping("/admin")
public class FilmController {

	// nullpointException이 발생 -> Autowired 에노테이션을 통해 객체를 주입 시켜준다
	@Autowired
	private FilmService filmService;

	@PostMapping("/modifyFilmActor")
	public String modifyFilmActor(@RequestParam(value = "filmId", required = true) int filmId,
									@RequestParam(value = "ck", required = true) int[] ck) {
		// 디버깅
		log.debug("modifyFilmActor param(filmId) : " + filmId);
		log.debug("modifyFilmActor param(ck) : " + ck.length);
		
		// modify
		filmService.modifyFilmActor(ck, filmId);
		
		// service - mapper
		// delete from film_actor where film_id =  #{filmId}
		// for문
		// insert into(actor_id, film_id) values(#{ck[0], #ck[1], .... )

		return "redirect:/admin/getFilmOne?filmId=" + filmId;
	}
	
	// getActorListByFilm 맵핑
	@GetMapping("/getActorListByFilm")
	public String getActorListByFilm(Model model, @RequestParam(value = "filmId", required = true) int filmId) {
		//디버깅
		log.debug("getActorListByFilm param(filmId) : " + filmId);
		
		List<Map<String, Object>> filmActorList = filmService.getActorListByFilm(filmId);
		log.debug("getActorListByFilm filmActorList size : " + filmActorList.size());
		
		model.addAttribute("filmActorList", filmActorList);
		model.addAttribute("filmId", filmId);
		
		return "getActorListByFilm";
	}
	
	// getFilmOne 맵핑
	@GetMapping("/getFilmOne")
	public String getFilmOne(Model model, @RequestParam(value = "filmId", required = true) int filmId,
			@RequestParam(name = "categoryName", required = false) String categoryName,
			@RequestParam(name = "price", required = false) Double price,
			@RequestParam(name = "title", required = false) String title,
			@RequestParam(name = "actor", required = false) String actor,
			@RequestParam(name = "rating", required = false) String rating,
			@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) {
		
		//디버깅
		log.debug("getFilmOne 안의 getFilmOne : " + filmId);
		log.debug("getFilmOne 안의 Param(categoryName) : " + categoryName);
		log.debug("getFilmOne 안의 Param(price) : " + price);
		log.debug("getFilmOne 안의 Param(title) : " + title);
		log.debug("getFilmOne 안의 Param(rating) : " + rating);
		log.debug("getFilmOne 안의 Param(actor) : " + actor);
		log.debug("getFilmOne 안의 Param(currentPage) : " + currentPage);
		log.debug("getFilmOne 안의 Param(rowPerPage) : " + rowPerPage);
		
		Map<String,Object> FilmeOne = filmService.getFilmOne(filmId);
		
		model.addAttribute("filmCount",FilmeOne.get("filmCount"));
		model.addAttribute("filmCount2",FilmeOne.get("filmCount2"));
		model.addAttribute("film", FilmeOne.get("film"));
		model.addAttribute("actor", actor);
		model.addAttribute("title", title);
		model.addAttribute("rating", rating);
		model.addAttribute("price", price);
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		
		return "getFilmOne";
	}

	// getFilmList2 맵핑
	@GetMapping("/getFilmList")
	public String getFilmList(Model model, @RequestParam(name = "categoryName", required = false) String categoryName,
			@RequestParam(name = "price", required = false) Double price, // null 때문에 Double 사용(double x)
			@RequestParam(name = "title", required = false) String title,
			@RequestParam(name = "actor", required = false) String actor,
			@RequestParam(name = "rating", required = false) String rating,
			@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) {
		
		// 카테고리 선택시 공백 -> null로 수정
		if (categoryName != null && categoryName.equals("")) {
			categoryName = null;
		}
		
		// 등급 선택시 공백 -> null로 수정
		if (rating != null && rating.equals("")) {
			rating = null;
		}

		// 가격 선택시 공백 -> null로 수정
		if (price != null && price == 0) {
			price = null;
		}

		// 디버깅
		log.debug("getFilmList method Param(categoryName) : " + categoryName);
		log.debug("getFilmList method Param(price) : " + price);
		log.debug("getFilmList method Param(title) : " + title);
		log.debug("getFilmList method Param(rating) : " + rating);
		log.debug("getFilmList method Param(actor) : " + actor);
		log.debug("getFilmList method Param(currentPage) : " + currentPage);
		log.debug("getFilmList method Param(rowPerPage) : " + rowPerPage);
		
		Map<String, Object> map = filmService.getFilmList(categoryName, rating, price, title, actor, currentPage, rowPerPage);
		log.debug("map : " + map.toString());

		model.addAttribute("actor", actor);
		model.addAttribute("title", title);
		model.addAttribute("rating", rating);
		model.addAttribute("price", price);
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("filmList", map.get("filmList"));
		model.addAttribute("categoryNameList", map.get("categoryNameList"));
		model.addAttribute("ratingList", map.get("ratingList"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		return "getFilmList";
	}

	// getFilmList 맵핑
}
