package com.gd.sakila.vo;

import lombok.Data;

@Data
public class Rental {
	private int rentalId;
	private String rentalDate;
	private String returnDate;
	private int customerId;
	private int inventoryId;
	private int staffId;
	private String lastUpdate;
	private String title;
}
