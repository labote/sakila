package com.gd.sakila.vo;

import lombok.Data;

@Data
public class Customer {
	private int customerId;
	private int storeId;
	private String name;
	private String email;
	private int addressId;
	private int active;
	private String createDate;
	private String lastUpdate;
	private int cnt;
	private int sum;
}
