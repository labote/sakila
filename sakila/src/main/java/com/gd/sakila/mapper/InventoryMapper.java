package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Inventory;
import com.gd.sakila.vo.PageParam;

@Mapper
public interface InventoryMapper {
	List<Map<String, Object>> selectInventoryInfoList(PageParam pageParam);
	List<Map<String, Object>> selectInventoryOne(int fiflmId);
	List<Integer> selectFilmInStock(Map<String, Object> map);
	List<Map<String,Object>> selectInventoryByFilmId(int filmId);
	int selectInventoryTotal(String searchWord);
	int insertInvetory(Inventory inventory);
	int deleteInventory(int inventoryId);
	int selectStaffIdByInventory(int inventoryId);
}
