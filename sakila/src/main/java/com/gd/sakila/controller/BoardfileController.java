package com.gd.sakila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gd.sakila.service.BoardfileService;
import com.gd.sakila.vo.Boardfile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class BoardfileController {
	
	@Autowired private BoardfileService boardfileService;
	
	// boardfile 추가 맵핑
	@GetMapping("/addBoardfile")
	public String addBoardfile(Model model, @RequestParam(value = "boardId", required = true) int boardId) {
		// 디버깅
		log.debug("addBoardfile 파라미터(boardId) : " + boardId);
		
		model.addAttribute("boardId", boardId);
	
		return "addBoardfile";
	}
	
	@PostMapping("/addBoardfile")
	public String addBoardfile(MultipartFile multipartFile, @RequestParam(value = "boardId", required = true) int boardId) {
		// 디버깅
		log.debug("addBoardfile 파라미터(multipartFile) : " + multipartFile.toString());
		log.debug("addBoardfile 파라미터(boardId) : " + boardId);
		
		int addBoardfileRow = boardfileService.addBoardfileOne(multipartFile, boardId);
		log.debug("addBoardfileRow : " + addBoardfileRow);
		
		return "redirect:/admin/getBoardOne?boardId=" + boardId;
	}
	
	
	// boardfile 삭제 맵핑
	@GetMapping("/removeBoardfile")
	public String removeBoardfile(Boardfile boardfile) {
		// staff 누구나 삭제 가능
		boardfileService.removeBoardfileOne(boardfile);
		
		return "redirect:/admin/getBoardOne?boardId=" + boardfile.getBoardId();
	}
}
