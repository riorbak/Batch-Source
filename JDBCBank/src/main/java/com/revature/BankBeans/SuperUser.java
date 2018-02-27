package com.revature.BankBeans;

import java.io.Serializable;

public class SuperUser implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
	public SuperUser()
	{
		super();
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
	
	
}
