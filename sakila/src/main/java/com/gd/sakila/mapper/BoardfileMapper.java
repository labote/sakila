package com.gd.sakila.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Boardfile;

@Mapper
public interface BoardfileMapper {
	int insertBoardfile(Boardfile boardfile);
	int deleteBoardfileByBoardId(int boardId);
	int deleteBoardfileOne(int boardfileId);
	List<Boardfile> selectboardfileByBoardId(int boardId);
}
