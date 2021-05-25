package com.gd.sakila.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.CommentMapper;
import com.gd.sakila.vo.Comment;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅
@Service // 붙어있어야 객체가 만들어짐
@Transactional // spring에 트랜잭션기능이 있다. 어떤 메서드를 실행하다가 에러가뜨면 그 메서드가 있는 서비스 롤백
public class CommentService {
	// spring에는 Mapper에 객체 주입하는 기능이 있음(의존성 주입 = Dependency Injection)
	@Autowired private CommentMapper commentMapper;
	
	// comment 추가 메서드
	public int addComment(Comment comment) {
		// 디버깅
		log.debug("addComment의 comment 객체 확인 : " + comment.toString());
		
		return commentMapper.insertComment(comment);
	}
	
	// comment 삭제 메서드
	public int removeComment(int commentId) {
		// 디버깅
		log.debug("removeComment의 commentId 확인 : " + commentId);
		
		return commentMapper.deleteCommentByCommentId(commentId);
	}
}
