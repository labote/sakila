package com.gd.sakila.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.BoardService;
import com.gd.sakila.vo.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅
@Controller // 컴포넌트로 객체가 자동으로 만들어진다. 서블릿처럼 행동하는 클래스를 상속받음
public class BoardController {
	
	@Autowired // nullpointException이 발생 -> Autowired 에노테이션을 통해 객체를 주입 시켜준다
	private BoardService boardService;
	
	// 동일한 이름의 맵핑이 두개가 생기면 부팅이 안됨 -> 하지만 Get과 Post는 가능
	// 메서드 이름이 같지만 오버로딩이라 상관이 없다
	// 추가
	@GetMapping("/addBoard")
	public String addBoard() {
		return "addBoard";
	}
	
	@PostMapping("/addBoard")
	public String addBoard(Board board) { // inputType이 필드명과 같아야한다. 커맨드객체라고 부른다.
		boardService.addBoard(board);
		// return "getBoardOne"; // 포워딩 된다.
		return "redirect:/getBoardList";
	}
	
	// 리턴타입 뷰이름 문자열 C -> V
	// 삭제
	@GetMapping("/removeBoard")
	public String removeBoard(Model model, @RequestParam(value="boardId", required = true) int boardId) {
		// 디버깅
		log.debug("param : " + boardId);
		
		model.addAttribute("boardId", boardId); // 중복이지만, 받을때 공통된 이름으로 받을 수 있어서 model로 해결
		return "removeBoard";
	}
	
	// C -> M -> redirect(C)
	@PostMapping("/removeBoard")
	public String removeBoard(Board board) {
		int row = boardService.removeBoard(board);
		log.debug("" + row);
		if(row == 0) {
			return "redirect:/getBoardOne?boardId=" + board.getBoardId();
		}
		return "redirect:/getBoardList";
	}
	
	// BoardOne Mapping
	@GetMapping("/getBoardOne")
	public String getBoardOne(Model model, @RequestParam(value="boardId", required = true) int boardId) { // View가 있으면 모델이 존재
		Map<String, Object> boardOne = boardService.getBoardOne(boardId);
		// 디버깅
		System.out.println("boardOne : " + boardOne.toString());
		
		model.addAttribute("boardOne", boardOne);
		return "getBoardOne";
	}
	
	// List Mapping
	@GetMapping("/getBoardList")
	public String getBoardList(Model model, @RequestParam(value="currentPage", defaultValue = "1") int currentPage, @RequestParam(value="rowPerPage", defaultValue = "10") int rowPerPage, @RequestParam(value="searchWord", required = false) String searchWord) {
		
		// 디버깅
		System.out.println(currentPage + "<-- currentPage");
		System.out.println(rowPerPage + "<-- rowPerPage");
		System.out.println(searchWord + "<-- searchWord");
		
		Map<String,Object> map = boardService.getBoardList(currentPage, rowPerPage, searchWord);
		// 디버깅
		System.out.println("map : " + map.toString());
		// view 작업의 편의성을 위해 풀어서 보내준다
		// model.addAttribute("map", map);
		model.addAttribute("boardList", map.get("boardList"));
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		
		return "getBoardList";
	}
}
