package com.gd.sakila.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.PageParam;
import com.gd.sakila.vo.Staff;
import com.gd.sakila.vo.StaffList;

/*
 * @Component, @Repository, @Service, @Controller --> Bean(스프링이라는 컨테이너 안에 미리 객체를 생성해두는 곳) --> 1. spring.getBean(클래스타입), @Autowired <-- Dependency Injection
 * @Mapper : mybatis의 annotation --> @Repository의 역할 -> 맵퍼 + 인터페이스 --> 컴파일 시 구현클래스 자동으로 생성
 */

@Mapper // mapper.xml을 찾아서 메서드명과 mapper의 id 이름이 같으면 합쳐서 메서드 구현클래스 생성시 오버라이딩을 한다.
public interface StaffMapper {
	Staff selectStaffLogin(Staff staff);
	List<Staff> selectStaffAll(PageParam pageParam); // 아직 페이징할지 안할지 결정을 못했지만 일단 PageParam 형태로 입력받음
	StaffList selectStaffOne(int staffId);
}
