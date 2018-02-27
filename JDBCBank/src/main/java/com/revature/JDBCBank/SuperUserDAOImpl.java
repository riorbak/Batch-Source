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
			
			newUser = con.prepareStatement("insert into BankUsers values(IncUserID.nextVal,?,?,?,?,0)");
		        
			newUser.setString(2, name); 
			newUser.setString(3, phone);
			newUser.setString(4, userName);
			newUser.setString(5, password);
			
			int i = newUser.executeUpdate();  
			System.out.println(i+" records inserted");  //TODO log4j
			
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
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " +
						rs.getString(3) + " " + rs.getString(4) + " " +
						rs.getString(5) + " " + rs.getInt(6));  
			
			}
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
