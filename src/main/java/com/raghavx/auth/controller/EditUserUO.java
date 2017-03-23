package com.raghavx.auth.controller;

import lombok.Data;

@Data
public class EditUserUO {

	private String userName, password;
	
	private boolean disable, rental, api;
	
	private long currentBalance;
	
	private long incrementBy;
}
