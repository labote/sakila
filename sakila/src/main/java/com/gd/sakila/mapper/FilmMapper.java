package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Film;

@Mapper
public interface FilmMapper {
	List<Integer> selectFilmInStock(Map<String,Object> map);
	List<Map<String, Object>> selectFilmList(Map<String, Object> map);
	Film selectFilmOne(int filmId);
	int selectFilmTotal(Map<String, Object> map);
	List<String> selectRatingList();
	List<Map<String, Object>> selectActorListByFilm(int filmId);
	int deleteActorListByFilm(int filmId);
	int insertActorListByFilm(int actorId, int filmId);
	int insertFilm(Film film);
	int insertFilmCategory(Map<String, Object> map);
	List<Map<String,Object>> selectFilmListByCategory(String categoryName);
	String selectFilmRating(int filmId);
}
