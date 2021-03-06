package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.CategoryMapper;
import com.gd.sakila.mapper.FilmMapper;
import com.gd.sakila.vo.Category;
import com.gd.sakila.vo.Film;
import com.gd.sakila.vo.FilmForm;
import com.gd.sakila.vo.PageParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅
@Service // 붙어있어야 객체가 만들어짐
@Transactional // spring에 트랜잭션기능이 있다. 어떤 메서드를 실행하다가 에러가뜨면 그 메서드가 있는 서비스 롤백
public class FilmService {
	
	@Autowired private FilmMapper filmMapper; // spring에는 Mapper에 객체 주입하는 기능이 있음(의존성 주입 = Dependency Injection)
	@Autowired private CategoryMapper categoryMapper;
	
	/*
	 *  param : film입력폼
	 *  return : 입력된 filmId 값
	 */
	// Film 추가
	public int addFilm(FilmForm filmForm) {
		Film film = filmForm.getFilm();
		
		// filmId 생성된 후 film.setFilmId(생성된 값) 호출
		filmMapper.insertFilm(film);
		Map<String, Object> addFilmMap = new HashMap<>();
		addFilmMap.put("categoryId", filmForm.getCategory().getCategoryId());
		addFilmMap.put("filmId",film.getFilmId());
		
		filmMapper.insertFilmCategory(addFilmMap);
		
		return film.getFilmId();
	}
	
	// Film Rating 찾기 위한 메서드
	public String getFilmRating(int filmId) {
		return filmMapper.selectFilmRating(filmId);
	}
	
	// getFilmListByCategory
	public List<Map<String,Object>> getFilmListByCategory(String categoryName){
		return filmMapper.selectFilmListByCategory(categoryName);
	}
	
	// Delete + Insert
	public void modifyFilmActor(int[] actorId, int filmId) {
		
		int deleteRow = filmMapper.deleteActorListByFilm(filmId);
		log.debug("FilmActor deleteRow : " + deleteRow);
		
		for(int i=0;i<actorId.length;i++) {
			int insertRow = filmMapper.insertActorListByFilm(actorId[i], filmId);
			log.debug("FilmActor insertRow : " + insertRow);
		}
		
	}
	
	// Actor By Film 출력 메서드
	public List<Map<String, Object>> getActorListByFilm(int filmId){
		return filmMapper.selectActorListByFilm(filmId);
	}
	
	// FilmOne 출력 메서드
	// map <-- film, filmCount
	public Map<String, Object> getFilmOne(int filmId){
		// store1
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("filmId", filmId);
		paramMap.put("storeId", 1);
		int filmCount = 0;
		paramMap.put("filmCount", filmCount);
		List<Integer> selectFilmlist = filmMapper.selectFilmInStock(paramMap);
		
		// store2
		Map<String, Object> paramMap2 = new HashMap<String, Object>();
		paramMap2.put("filmId", filmId);
		paramMap2.put("storeId", 2);
		int filmCount2 = 0;
		paramMap2.put("filmCount", filmCount2);
		List<Integer> selectFilmlist2 = filmMapper.selectFilmInStock(paramMap2);
		
		
		Film film = filmMapper.selectFilmOne(filmId);
		
		// 디버깅
		log.debug("store1의 filmCount : " + paramMap.get("filmCount"));
		log.debug("selectFilmlist : " + selectFilmlist);
		log.debug("store2의 filmCount  : " + paramMap2.get("filmCount"));
		log.debug("selectFilmlist2 : " + selectFilmlist2);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		returnMap.put("film", film);
		returnMap.put("filmCount", paramMap.get("filmCount"));
		returnMap.put("filmCount2", paramMap2.get("filmCount"));
		
		return returnMap;
	}
	
	// FilmList 서비스
	// 페이징 작업
	// FilmList와 Pageparam을 사용해야하기 때문에 Map을 사용
	/*
	 * public Map<String, Object> getFilmList2(int currentPage, int rowPerPage,
	 * String searchWord){
	 * 
	 * // pageParam 선언 및 초기화 int beginRow = (currentPage -1) * rowPerPage; PageParam
	 * pageParam = new PageParam(); pageParam.setBeginRow(beginRow);
	 * pageParam.setRowPerPage(rowPerPage); pageParam.setSearchWord(searchWord);
	 * 
	 * // dao 호출 List<FilmList> filmList = filmMapper.selectFilmList2(pageParam);
	 * int filmTotal = filmMapper.selectFilmTotal();
	 * 
	 * // lastPage int lastPage = (int)Math.ceil((double)filmTotal/rowPerPage);
	 * 
	 * // 디버깅 log.debug("beginRow : " + beginRow); log.debug("lastPage : " +
	 * lastPage); log.debug("filmTotal : " + filmTotal); log.debug("filmList : " +
	 * filmList.toString());
	 * 
	 * Map<String, Object> filmMap = new HashMap<String, Object>();
	 * filmMap.put("filmList", filmList); filmMap.put("lastPage", lastPage);
	 * 
	 * return filmMap; }
	 */
	
	// FilmList 서비스
	// 페이징 작업
	// FilmList와 Pageparam을 사용해야하기 때문에 Map을 사용
	public Map<String, Object> getFilmList(String categoryName, String rating, Double price, String title, String actor, int currentPage, int rowPerPage){
		
		// debug
		log.debug("getFilmList method param(categoryName) : " + categoryName);
		log.debug("getFilmList method param(rating) : " + rating);
		log.debug("getFilmList method param(price) : " + price);
		log.debug("getFilmList method param(title) : " + title);
		log.debug("getFilmList method param(actor) : " + actor);
		log.debug("getFilmList method param(currentPage) : " + currentPage);
		log.debug("getFilmList method param(rowPerPage) : " + rowPerPage);
		
		// pageParam 선언 및 초기화
		int beginRow = (currentPage -1) * rowPerPage;
		PageParam pageParam = new PageParam();
		pageParam.setBeginRow(beginRow);
		pageParam.setRowPerPage(rowPerPage);
		
		// 여러 param을 받기 위해 Map 사용
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("categoryName", categoryName);
		paramMap.put("rating", rating);
		paramMap.put("price", price);
		paramMap.put("title", title);
		paramMap.put("actor", actor);
		paramMap.put("beginRow", pageParam.getBeginRow());
		paramMap.put("rowPerPage", pageParam.getRowPerPage());
		
		// dao 호출
		List<Map<String,Object>> filmList = filmMapper.selectFilmList(paramMap);
		List<Category> categoryNameList = categoryMapper.selectCategoryNameList();
		List<String> ratingList = filmMapper.selectRatingList();
		int filmTotal = filmMapper.selectFilmTotal(paramMap);
		
		log.debug("FilmService의 filmList : " + filmList.toString());
		log.debug("FilmService의 categoryNameList : " + categoryNameList.toString());
		log.debug("FilmService의 filmTotal : " + filmTotal);
		
		// lastPage
		int lastPage = (int)Math.ceil((double)filmTotal/rowPerPage);
		
		// Map을 이용해 두 리스트를 Map 안에 넣어준다.
		Map<String,Object> returnMap = new HashMap<String, Object>();
		
		returnMap.put("lastPage", lastPage);
		returnMap.put("filmList", filmList);
		returnMap.put("ratingList", ratingList);
		returnMap.put("categoryNameList", categoryNameList);
		
		return returnMap;
	}
}
