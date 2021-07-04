package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.InventoryMapper;
import com.gd.sakila.mapper.RentalMapper;
import com.gd.sakila.vo.PageParam;
import com.gd.sakila.vo.Rental;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅
@Service // 붙어있어야 객체가 만들어짐
@Transactional // spring에 트랜잭션기능이 있다. 어떤 메서드를 실행하다가 에러가뜨면 그 메서드가 있는 서비스 롤백
public class RentalService {
	
	@Autowired RentalMapper rentalMapper;
	@Autowired InventoryMapper inventoryMapper;
	// update
	public int modifyRental(int inventoryId) {
		return rentalMapper.updateRental(inventoryId);
	}
	
	// addRental
	public int addRental(int inventoryId, int customerId) {
		
		// dao 호출
		int staffId = inventoryMapper.selectStaffIdByInventory(inventoryId);
		
		Map<String, Object> rentalMap = new HashMap<String, Object>();
		rentalMap.put("inventoryId", inventoryId);
		rentalMap.put("customerId", customerId);
		rentalMap.put("staffId", staffId);
		
		return rentalMapper.insertRental(rentalMap);
	}
	
	// RentalList
	public Map<String, Object> selectListRentalList(int currentPage, int rowPerPage, String searchWord, int customerId){
		
		// pageParam 선언 및 초기화
		int beginRow = (currentPage -1) * rowPerPage;
		PageParam pageParam = new PageParam();
		pageParam.setBeginRow(beginRow);
		pageParam.setRowPerPage(rowPerPage);
		pageParam.setSearchWord(searchWord);
		
		// 여러 param을 받기 위해 Map 사용
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("customerId", customerId);
		paramMap.put("searchWord", pageParam.getSearchWord());
		paramMap.put("beginRow", pageParam.getBeginRow());
		paramMap.put("rowPerPage", pageParam.getRowPerPage());
				
		// dao 호출
		List<Rental> rentalList = rentalMapper.selectRentalList(paramMap);
		int rentalTotal = rentalMapper.selectRentalTotal(paramMap);
			
		log.debug("seleListRentalList의 rentalList : " + rentalList.toString());
		log.debug("seleListRentalList의 rentalTotal : " + rentalTotal);
		
		// lastPage
		int lastPage = (int)Math.ceil((double)rentalTotal/rowPerPage);
				
		// Map을 이용해 두 리스트를 Map 안에 넣어준다.
		Map<String,Object> returnMap = new HashMap<String, Object>();
				
		returnMap.put("lastPage", lastPage);
		returnMap.put("rentalList", rentalList);
				
		return returnMap;
	}
}
