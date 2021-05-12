package com.gd.sakila.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Country;
import com.gd.sakila.vo.PageParam;

@Mapper // 실행되면 Mapper로 에노테이션된 인터페이스를 찾고 selectCountryList라는 쿼리를 찾기 시작함 -> CountryMapper.xml(namespace가 같아야함)에서 찾아서 CountrtyMapper 인터페이스의 자식 클래스를 자동으로 만들어준다(spring) -> CountryList 생성
public interface CountryMapper {
	List<Country> selectCountryList(PageParam pageParam);
	int selectCountryTotal();
}
