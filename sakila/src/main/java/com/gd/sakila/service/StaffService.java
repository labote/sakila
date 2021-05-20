package com.gd.sakila.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.StaffMapper;
import com.gd.sakila.vo.Staff;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class StaffService {
	
	@Autowired private StaffMapper staffMapper; // spring에는 countryMapper에 객체 주입하는 기능이 있음(의존성 주입 = Dependency Injection), 없으면 NullPointException 발생
	
	public Staff login(Staff staff) {
		log.debug("login Service 안의 staff Param : " + staff);
		return staffMapper.selectStaffLogin(staff); // null 또는 staff 객체 리턴
	}
}
