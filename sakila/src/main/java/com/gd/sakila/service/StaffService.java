package com.gd.sakila.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.StaffMapper;
import com.gd.sakila.vo.PageParam;
import com.gd.sakila.vo.Staff;
import com.gd.sakila.vo.StaffList;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅
@Service // 붙어있어야 객체가 만들어짐
@Transactional // spring에 트랜잭션기능이 있다. 어떤 메서드를 실행하다가 에러가뜨면 그 메서드가 있는 서비스 롤백
public class StaffService {
	
	@Autowired private StaffMapper staffMapper; // spring에는 countryMapper에 객체 주입하는 기능이 있음(의존성 주입 = Dependency Injection), 없으면 NullPointException 발생
	
	// 로그인 메서드
	public Staff login(Staff staff) {
		log.debug("login Service 안의 staff Param : " + staff);
		return staffMapper.selectStaffLogin(staff); // null 또는 staff 객체 리턴
	}
	
	// 모든 직원 출력 메서드
	public List<StaffList> getStaffList(String searchWord){
		// 디버깅
		log.debug("getStaffList의 파라미터(searchWord) : " + searchWord);
		
		PageParam pageParam = new PageParam();
		pageParam.setSearchWord(searchWord);
		
		return staffMapper.selectStaffAll(pageParam);
	}
	
	// 한 직원 정보 출력 메서드
	public StaffList getStaffOne(int staffId) {
		
		// 디버깅
		log.debug("getStaffOne의 파라미터(staffId) : " + staffId);
		
		return staffMapper.selectStaffOne(staffId);
	}
}
