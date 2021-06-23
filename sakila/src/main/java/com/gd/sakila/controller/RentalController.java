package com.gd.sakila.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gd.sakila.service.CategoryService;
import com.gd.sakila.service.FilmService;
import com.gd.sakila.vo.Category;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅
@RestController
public class RentalController {
	
	@Autowired private CategoryService categoryService;
	@Autowired private FilmService filmService;
	
	// filmCategory 맵핑
	@GetMapping("/filmCategory")
	public List<Category> filmCategory(){
		return categoryService.getCategoryList();
	}
	
	@GetMapping("/filmTitle")
	public List<String> filmTitle(@RequestParam(value="categoryName", required = true) String categoryName){
		return filmService.getFilmListByCategory(categoryName);
	}
	
	@GetMapping("/inventory")
	public List<Integer> filmInventory(@RequestParam(value="filmTitle", required = true) String filmTitle){
		return "";
	}
}
