package com.revature.ERS;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.ERS.beans.Reimbursement;
import com.revature.ERS.beans.User;

public interface UserDAO 
{
	//-- Shared --//
	public int login(String username, String password) throws SQLException;
	
	//-- Employee --//	
	public void makeRequest(String username, Reimbursement r) throws SQLException;
	
	public ArrayList<Reimbursement> viewPending(String username) throws SQLException;
	public ArrayList<Reimbursement> viewResolved(String username) throws SQLException;
	
	public User viewInfo(String username) throws SQLException;
	public void updateInfo(User u) throws SQLException;
	
	//-- Manager --//	
	public ArrayList<Reimbursement> viewAllPending() throws SQLException;
	public void apprDenyReq(String u, int i, int j) throws SQLException;
	
	public ArrayList<Reimbursement> viewAllResolved() throws SQLException;
	
	public ArrayList<User> viewEmployees() throws SQLException;
	
}