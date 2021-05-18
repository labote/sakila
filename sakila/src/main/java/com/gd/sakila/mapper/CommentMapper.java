package com.gd.sakila.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Comment;

@Mapper
public interface CommentMapper {
	// Board에 맞는 comment 출력 메서드 선언
	List<Comment> selectCommentListByBoard(int boardId);
	// comment 삽입 메서드 선언
	int insertComment(Comment comment);
	// commentId에 맞는 delete 메서드 선언
	int deleteCommentByCommentId(int commentId);
	// boadId에 맞는 delete 메서드 선언
	int deleteCommentByBoardId(int boardId);
}
