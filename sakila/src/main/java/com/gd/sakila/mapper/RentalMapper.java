package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Rental;

@Mapper
public interface RentalMapper {
	List<Rental> selectRentalList(Map<String, Object> map);
	int selectRentalTotal(Map<String, Object> map);
	int updateRental(int inventoryId);
}
