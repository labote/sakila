package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Customer;
import com.gd.sakila.vo.CustomerList;

@Mapper
public interface CustomerMapper {
	CustomerList selectCustomerOne(int customerId);
	int updateCustomerActiveByScheduler();
	int insertCustomer(Customer customer);
	List<Customer> selectCustomerList(Map<String,Object> map);
	int selectCustomerTotal(Map<String,Object> map);
}
