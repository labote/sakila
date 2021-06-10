package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Customer;

@Mapper
public interface CustomerMapper {
	Map<String, Object> selectCustomerOne(int customerId);
	int updateCustomerActiveByScheduler();
	List<Customer> selectCustomerList(Map<String,Object> map);
	int selectCustomerTotal(Map<String,Object> map);
}
