package com.revature.JDBCBank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SuperUserDAOImpl extends UserDAOImpl implements SuperUserDAO
{

	@Override
	public void createUser(String name, String phone, String userName, String password) 
	{
		try {
			Connection con = ConnFactory.getConnection();
			
			PreparedStatement newUser;
			
			newUser = con.prepareStatement("insert into BankUsers values(IncUserID.nextVal, ?, ?, ?, ?, 0)");
		        
			newUser.setString(1, name); 
			newUser.setString(2, phone);
			newUser.setString(3, userName);
			newUser.setString(4, password);
			
			newUser.executeUpdate();  
			BankDBDriver.log.info ("New user: " + name + " added");  //TODO log4j
			
			con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}
	
	@Override
	public void viewUsers() 
	{
		try {
			Connection con = ConnFactory.getConnection();
			
			PreparedStatement vwUsr;
			
			vwUsr = con.prepareStatement("select * from BankUsers");
			
			ResultSet rs = vwUsr.executeQuery();  
			while(rs.next())
			{  
				System.out.println("UserID: " + rs.getInt(1) + " Real Name: " + rs.getString(2) + 
						" Phone #: " + rs.getString(3) + " Username: " + rs.getString(4) + 
						" Password: " + rs.getString(5) + " Access Level: " + rs.getInt(6));  
			
			}
			con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}
	
	@Override
	public void viewUser(int userID) 
	{
		try {
			Connection con = ConnFactory.getConnection();
			
			PreparedStatement vwUsr;
			
			vwUsr = con.prepareStatement("select * from BankUsers where UserID = ?");
			
			vwUsr.setInt(1, userID);
			
			ResultSet rs = vwUsr.executeQuery();  
			
			if(!rs.next())
			{
				do
				{  
					System.out.println("UserID: " + rs.getInt(1) + " Real Name: " + rs.getString(2) + 
						" Phone #: " + rs.getString(3) + " Username: " + rs.getString(4) + 
						" Password: " + rs.getString(5) + " Access Level: " + rs.getInt(6));  
			
				}while(rs.next());
			}
			else
			{
				System.out.println("Invalid user ID.");
			}
			con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}

	@Override
	public void approveAccount(int userID)
	{		
		viewUser(userID);
		
		try {
			Connection con = ConnFactory.getConnection();
			
			PreparedStatement app;
			
			app = con.prepareStatement("update Accounts set Acc_Active = 1 Where UserID = ?");
			
			app.setInt(1, userID);
			
			app.executeUpdate();  
			
			
			
			System.out.println("Account Approved!");  
			
			con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	
	@Override
	public void updateUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(int userID) 
	{
		// TODO Auto-generated method stub
		
	}

}
