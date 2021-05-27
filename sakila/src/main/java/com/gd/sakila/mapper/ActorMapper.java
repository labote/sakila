package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Actor;
import com.gd.sakila.vo.PageParam;

@Mapper
public interface ActorMapper {
	int insertActor(Actor actor);
	int selectActorTotal(String searchWord);
	List<Map<String, Object>> selectActors(int filmId);
	List<Map<String, Object>> selectActorInfoList(PageParam pageParam);
}
