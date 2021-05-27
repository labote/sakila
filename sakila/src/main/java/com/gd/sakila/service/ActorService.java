package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.ActorMapper;
import com.gd.sakila.vo.Actor;
import com.gd.sakila.vo.PageParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅
@Service // 붙어있어야 객체가 만들어짐
@Transactional // spring에 트랜잭션기능이 있다. 어떤 메서드를 실행하다가 에러가뜨면 그 메서드가 있는 서비스 롤백
public class ActorService {
	
	@Autowired private ActorMapper actorMapper;
	
	// addActors method
	public List<Map<String, Object>> getActors(int filmId) {
		// 디버깅 
		log.debug("addActor method param(filmId) : " + filmId);
		
		return actorMapper.selectActors(filmId);
	}
	
	// addActor method
	public int addActor(Actor actor) {
		// 디버깅
		log.debug("addActor method param(actor) : " + actor.toString());
		
		return actorMapper.insertActor(actor);
	}
	
	// ActorList method
	public Map<String, Object> getActorList(String searchWord, int currentPage, int rowPerPage){
		
		// 디버깅
		log.debug("getActorList(Service) searchWord : " + searchWord);
		log.debug("getActorList(Service) currentPage : " + currentPage);
		log.debug("getActorList(Service) rowPerPage : " + rowPerPage);
		
		// beginRow, pageParam 선언 및 초기화
		int beginRow = (currentPage-1) * rowPerPage;
		PageParam pageParam = new PageParam();
		pageParam.setBeginRow(beginRow);
		pageParam.setRowPerPage(rowPerPage);
		pageParam.setSearchWord(searchWord);
		
		// dao 호출
		int actorTotal = actorMapper.selectActorTotal(searchWord);
		
		// lastPage
		int lastPage = (int)Math.ceil((double)actorTotal/rowPerPage);
		List<Map<String,Object>> actorList = actorMapper.selectActorInfoList(pageParam);
		
		// 디버깅
		log.debug("getActorList(Service) lastPage : " + lastPage);
		log.debug("getActorList(Service) actorList : " + actorList.toString());
		
		Map<String, Object> returnActorList = new HashMap<>();
		returnActorList.put("lastPage", lastPage);
		returnActorList.put("actorList", actorList);
		
		return returnActorList;
	}
}
