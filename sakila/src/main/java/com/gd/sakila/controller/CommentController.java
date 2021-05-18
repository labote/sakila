package com.gd.sakila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.CommentService;
import com.gd.sakila.vo.Comment;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅
@Controller // 컴포넌트로 객체가 자동으로 만들어진다. 서블릿처럼 행동하는 클래스를 상속받음
@RequestMapping("/admin")
public class CommentController {
	
	// nullpointException이 발생 -> Autowired 에노테이션을 통해 객체를 주입 시켜준다
	@Autowired CommentService commentService;
	
	// addComment Mapping
	@GetMapping("/addComment")
	public String addComment(Comment comment, @RequestParam(value="boardId", required = true) int boardId) {
		// 디버깅
		log.debug("addComment의 boardId : " + boardId);
		log.debug("addComment의 comment : " + comment.toString());
		
		comment.setBoardId(boardId);
		
		int commentRow = commentService.addComment(comment);
		
		//디버깅
		log.debug("addComment의 commentRow" + commentRow);
		
		return "redirect:/admin/getBoardOne?boardId=" + boardId;
	}
	
	// deleteComment Mapping
	@GetMapping("/deleteComment")
	public String deleteComment(@RequestParam(value="commentId", required = true) int commentId, @RequestParam(value="boardId", required = true) int boardId) {
		
		// 디버깅
		log.debug("deleteComment의 boardId : " + boardId);
		log.debug("deleteComment의 commentId : " + commentId);
		
		int commentRow = commentService.removeComment(commentId);
		
		// 디버깅
		log.debug("deleteComment의 commentRow" + commentRow);
		
		return "redirect:/admin/getBoardOne?boardId=" + boardId;
	}
}
