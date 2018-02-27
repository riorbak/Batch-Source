package com.revature.JDBCBank;

public interface UserDAO {

	public boolean login(String usrName, String pwd);
	
	public void openAccount(double startBal, int userID, int jointID, int isJoint);
	public void viewAccounts(int userID);			
	public void deleteAccount(int accNum, int accBal, int userID);
	
	public void deposit();
	public void withdraw();	
	public void transfer();
	
	//handled by driver
	public void logout();
	
}
