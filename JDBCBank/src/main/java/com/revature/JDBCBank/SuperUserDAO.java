package com.revature.JDBCBank;

public interface SuperUserDAO 
{	
	public void createUser(String name, String phone, String userName, String password);
	
	public void viewUsers();
	
	public void updateUser();
	
	public void deleteUser(int UserID);
}
