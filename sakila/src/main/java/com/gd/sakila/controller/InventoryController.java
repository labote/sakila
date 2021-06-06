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

import com.gd.sakila.service.InventoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅
@Controller // 컴포넌트로 객체가 자동으로 만들어진다. 서블릿처럼 행동하는 클래스를 상속받음
@RequestMapping("/admin")
public class InventoryController {
	
	@Autowired private InventoryService inventoryService;
	
	// addInventory 맵핑
	@GetMapping("addInventory")
	public String addInventory(@RequestParam(value ="filmId", required = true) int filmId,
								@RequestParam(value = "title", required = true) String title,
								@RequestParam(value = "storeId", required = true) int storeId) {
		
		// 디버깅
		log.debug("addInventory param(filmId) : " + filmId);
		log.debug("addInventory param(title) : " + title);
		log.debug("addInventory param(storeId) : " + storeId);
		
		// Service 호출
		int insertRow = inventoryService.addInventory(filmId, storeId);
		log.debug("insertRow : " + insertRow);
		
		return "redirect:/admin/getInventoryOne?filmId="+filmId+"&title="+title;
	}
	
	// removeInventory 맵핑
	@GetMapping("removeInventory")
	public String removeInventory(@RequestParam(value ="inventoryId", required = true) int inventoryId,
									@RequestParam(value ="filmId", required = true) int filmId,
									@RequestParam(value = "title", required = true) String title) {
		
		// 디버깅
		log.debug("removeInventory param(inventoryId) : " + inventoryId);
		log.debug("removeInventory param(filmId) : " + filmId);
		log.debug("removeInventory param(title) : " + title);
		
		// Service 호출
		int removeRow = inventoryService.removeInventory(inventoryId);
		log.debug("removeRow : " + removeRow);
		
		return "redirect:/admin/getInventoryOne?filmId="+filmId+"&title="+title;
	}
	
	// getInventoryOne 맵핑
	@GetMapping("getInventoryOne")
	public String getInventoryOne(Model model, @RequestParam(value ="currentPage", defaultValue = "1") int currentPage,
											@RequestParam(value ="filmId", required = true) int filmId,
											@RequestParam(value = "searchWord", required = false) String searchWord,
											@RequestParam(value = "title", required = true) String title) {
		
		log.debug("getInventoryOne param(currentPage) : " + currentPage);
		log.debug("getInventoryOne param(filmId) : " + filmId);
		log.debug("getInventoryOne param(searchWord) : " + searchWord);
		log.debug("getInventoryOne param(title) : " + title);
		
		// Service 호출
		List<Map<String, Object>> inventoryOne = inventoryService.getInventoryOne(filmId);
		log.debug("getInventoryOne param(inventoryOne) : " + inventoryOne.toString());
		
		int store1 = 1;
		int store2 = 2;
		
		// model
		model.addAttribute("store1", store1);
		model.addAttribute("store2", store2);
		model.addAttribute("inventoryOne", inventoryOne);
		model.addAttribute("title",title);
		model.addAttribute("filmId",filmId);
		model.addAttribute("searchWord",searchWord);
		model.addAttribute("currentPage",currentPage);
		
		return "getInventoryOne";
	}
	
	// getInventoryInfoList 맵핑
	@GetMapping("getInventoryInfoList")
	public String getInventoryInfoList(Model model, @RequestParam(value ="currentPage", defaultValue = "1") int currentPage, 
													@RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage,
													@RequestParam(value = "searchWord", required = false) String searchWord) {
		// 디버깅
		log.debug("getInventoryInfoList param(currentPage) : " + currentPage);
		log.debug("getInventoryInfoList param(rowPerPage) : " + rowPerPage);
		log.debug("getInventoryInfoList param(searchWord) : " + searchWord);
		
		// Service 호출
		Map<String, Object> inventroyMap = inventoryService.getInventoryInfoList(currentPage, rowPerPage, searchWord);
		
		// model
		model.addAttribute("lastPage", inventroyMap.get("lastPage"));
		model.addAttribute("inventoryList", inventroyMap.get("inventoryList"));
		//model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("filmInStock", inventroyMap.get("filmInStock"));
		
		return "getInventoryInfoList";
	}
}
