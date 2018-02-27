package com.revature.model;

public enum UserType {
	ADMIN(0), EMPLOYEE(1), CUSTOMER(2);
	
	private int id;
	
	private UserType(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public static UserType fromId(int id) {
		UserType ut = null;
		
		if(id == ADMIN.getId()) {
			ut = ADMIN;
		} else if(id == EMPLOYEE.getId()) {
			ut = EMPLOYEE;
		} else if(id == CUSTOMER.getId()) {
			ut = CUSTOMER;
		}
		
		return ut;
	}
	
	public static String toString(UserType t) {
		String str = "";
		
		switch(t) {
		case ADMIN:
			str = "admin";
			break;
		case EMPLOYEE:
			str = "employee";
			break;
		case CUSTOMER:
			str = "customer";
			break;
		}
		
		return str;
	}
	
	public static UserType fromString(String str) {
		if(str.equalsIgnoreCase("admin")) {
			return ADMIN;
		} else if(str.equalsIgnoreCase("employee")) {
			return EMPLOYEE;
		} else if(str.equalsIgnoreCase("customer")) {
			return CUSTOMER;
		}
		
		return null;
	}
}
