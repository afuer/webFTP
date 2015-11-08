package com.shaah.vouch_status.common.dtos;

import java.math.BigDecimal;

public class UserDto {

	private BigDecimal id;
	private String userName;
	private BigDecimal type;
	private String password;
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public BigDecimal getType() {
		return type;
	}
	public void setType(BigDecimal type) {
		this.type = type;
	}
	
}
