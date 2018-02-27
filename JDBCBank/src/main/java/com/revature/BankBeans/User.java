package com.revature.BankBeans;

import java.io.Serializable;

public class User implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	//Personal info
	private String name;
	private String phone;

	//User info
	private String username;
	private String password;
		
	//Account info
	private boolean accActive;
	
	public User()
	{
		super();
	}	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	public boolean isAccActive() {
		return accActive;
	}
	public void setAccActive(boolean accActive) {
		this.accActive = accActive;
	}
	
	
	
}
