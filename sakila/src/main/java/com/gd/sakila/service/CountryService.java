package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.CountryMapper;
import com.gd.sakila.vo.Country;
import com.gd.sakila.vo.PageParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅
@Service // 붙어있어야 객체가 만들어짐
@Transactional // spring에 트랜잭션기능이 있다. 어떤 메서드를 실행하다가 에러가뜨면 그 메서드가 있는 서비스 롤백
public class CountryService {
	
	// 안만들어진 클래스가 존재하고 사용해야한다면 생성자로 인해 기다려야한다.
	@Autowired
	private CountryMapper countryMapper; // spring에는 countryMapper에 객체 주입하는 기능이 있음(의존성 주입 = Dependency Injection)
	
	// spring 부팅 -> Mapper라는 에노테이션이 붙어 있으니 CountryMapper의 서브 클래스를 만듬 -> 서브 클래스의 객체를 만듬 -> 서비스 에노테이션이 있으므로 자동으로 CountryService 객체를 만드는데 그 와중에 Autowired가 있으므로 countryMapper를 먼저 찾아서(없으면 만듬) 객체 주입(bean이라고 부름)  
	
	public Map<String, Object> getCountryList(int currentPage, int rowPerPage){
		// CountryMapper countryMapper = new CountryMapper(); // 인터페이스 객체 생성 불가
		
		/*
		 * CountryMapper countryMapper = null; List<Country> list =
		 * countryMapper.selectCountryList(); // nullpointException
		 */
		
		// beginRow
		// 컨트롤러와 dao에서 준비함(서비스, 서비스에서 하는일 + 트랜잭션처리)
		// 컨트롤러에서 넘어온 매개값들(파라미터)을 가공하여 dao에 전달
		int beginRow = (currentPage - 1 ) * rowPerPage;
		PageParam pageParam = new PageParam(); // mybatis는 하나밖에 못받기 때문에 여러개를 받기 위해 이렇게 사용한다.
		pageParam.setBeginRow(beginRow);
		pageParam.setRowPerPage(rowPerPage);
		
		// dao 호출
		List<Country> boardlist = countryMapper.selectCountryList(pageParam);
		int total = countryMapper.selectCountryTotal();
		
		// dao의 반환값을 가공
/*		int lastPage = total/rowPerPage;
		if(total % rowPerPage != 0) {
			lastPage += 1;
		} */
		int lastPage = (int)(Math.ceil((double)total/rowPerPage));
		
		// 디버깅
		System.out.println("beginRow : " + beginRow);
		System.out.println("lastPage : " + lastPage);
		System.out.println("total : " + total);
		System.out.println("boardlist : " + boardlist.toString());
		
		Map<String, Object> map = new HashMap<String, Object>();
		// 디버깅
		System.out.println("map : " + map.toString());
		map.put("boardlist", boardlist);
		map.put("lastPage", lastPage);
		
		return map;
	}
}
