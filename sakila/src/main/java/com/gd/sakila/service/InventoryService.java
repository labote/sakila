package com.gd.sakila.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.InventoryMapper;
import com.gd.sakila.vo.Inventory;
import com.gd.sakila.vo.PageParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅
@Service // 붙어있어야 객체가 만들어짐
@Transactional // spring에 트랜잭션기능이 있다. 어떤 메서드를 실행하다가 에러가뜨면 그 메서드가 있는 서비스 롤백
public class InventoryService {

	@Autowired
	private InventoryMapper inventoryMapper; // 객체 주입

	// selectInventoryByFilmId
	public List<Map<String,Object>> getInventoryByFilmId(int filmId){
		return inventoryMapper.selectInventoryByFilmId(filmId);
	}
	
	// INSERT
	public int addInventory(int filmId, int storeId) {
		
		// 전처리
		Inventory inventory = new Inventory();
		inventory.setFilmId(filmId);
		inventory.setStoreId(storeId);
		
		return inventoryMapper.insertInvetory(inventory);
	}
	
	// DELETE
	public int removeInventory(int inventoryId) {
		return inventoryMapper.deleteInventory(inventoryId);
	}
	
	// InventoryOne
	public List<Map<String, Object>> getInventoryOne(int filmId){
		
		return inventoryMapper.selectInventoryOne(filmId);
	}
	
	// InventoryList
	public Map<String, Object> getInventoryInfoList(int currentPage, int rowPerPage, String searchWord) {

		// 디버깅
		log.debug("getInventoryInfoList param(currentPage) : " + currentPage);
		log.debug("getInventoryInfoList param(rowPerPage) : " + rowPerPage);
		log.debug("getInventoryInfoList param(searchWord) : " + searchWord);

		// beginRow
		int beginRow = (currentPage - 1) * rowPerPage;

		// PageParam 선언 및 초기화
		PageParam pageParam = new PageParam();
		pageParam.setBeginRow(beginRow);
		pageParam.setRowPerPage(rowPerPage);
		pageParam.setSearchWord(searchWord);

		// dao 호출
		int inventoryTotal = inventoryMapper.selectInventoryTotal(searchWord);
		List<Map<String, Object>> inventoryList = inventoryMapper.selectInventoryInfoList(pageParam);

		// stock 저장
		// inventoryList에서 storeId와 filmId를 가져오기 위해 사용
		List<Integer> filmInStock = new ArrayList<>();

		for (int i = 0; i < inventoryList.size(); i++) {

			int filmId = Integer.parseInt(inventoryList.get(i).get("filmId").toString());
			int storeId = Integer.parseInt(inventoryList.get(i).get("storeId").toString());
			int filmCount = 0;

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("filmId", filmId);
			paramMap.put("storeId", storeId);
			paramMap.put("filmCount", filmCount);
			List<Integer> selectFilmInStock = inventoryMapper.selectFilmInStock(paramMap);
			
			log.debug("selectFilmInStock : " + selectFilmInStock.toString());
			filmInStock.add((Integer) paramMap.get("filmCount"));

		}

		/*
		 * for (int i = 0; i < 10; i++) { int ex =
		 * Integer.parseInt(inventoryList.get(i).get("filmId").toString());
		 * log.debug("ex : " + ex); }
		 */
		/*
		 * for(int i=0; i<inventoryList.size(); i++) { int filmId =
		 * Integer.parseInt(inventoryList.get(i).get("filmId").toString()); int storeId
		 * = Integer.parseInt(inventoryList.get(i).get("storeId").toString()); } int
		 * filmId = Integer.parseInt(inventoryList.get(0).get("filmId").toString()); int
		 * storeId = Integer.parseInt(inventoryList.get(0).get("storeId").toString());
		 * //String ex = inventoryList.get(0).get("filmId").toString();
		 * log.debug("ex : " + filmId + " " + storeId);
		 */

		log.debug("filmInStock : " + filmInStock.toString());
		log.debug("inventoryTotal : " + inventoryTotal);

		// lastPage
		int lastPage = (int) Math.ceil((double) inventoryTotal / rowPerPage);
		log.debug("lastPage : " + lastPage);

		// List와 lastPage를 같이 넘기기 위한 Map
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("inventoryList", inventoryList);
		returnMap.put("lastPage", lastPage);
		returnMap.put("filmInStock", filmInStock);

		return returnMap;
	}
}
