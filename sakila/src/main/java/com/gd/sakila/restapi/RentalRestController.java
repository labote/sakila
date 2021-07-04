package com.gd.sakila.restapi;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gd.sakila.service.CategoryService;
import com.gd.sakila.service.FilmService;
import com.gd.sakila.service.InventoryService;
import com.gd.sakila.vo.Category;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅
@RestController
public class RentalRestController {
	
	@Autowired private CategoryService categoryService;
	@Autowired private FilmService filmService;
	@Autowired private InventoryService inventoryService;
	
	// filmCategory 맵핑
	@GetMapping("/filmCategory")
	public List<Category> filmCategory(){
		return categoryService.getCategoryList();
	}
	
	@GetMapping("/filmTitle")
	public List<Map<String,Object>> filmTitle(@RequestParam(value="categoryName", required = true) String categoryName){
		log.debug("ajax-> RentalController param(categoryName) : " + categoryName);
		return filmService.getFilmListByCategory(categoryName);
	}
	
	@GetMapping("/inventory")
	public List<Map<String, Object>> filmInventory(@RequestParam(value="filmId", required = true) int filmId){
		log.debug("ajax-> RentalController param(filmId) : " + filmId);
		return inventoryService.getInventoryByFilmId(filmId);
	}
}
