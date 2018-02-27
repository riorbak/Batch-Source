package com.revature.JDBCBank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

	public boolean login(String usrName, String pwd)
	{
		try {
			Connection con = ConnFactory.getConnection();
			
			PreparedStatement findUsr;
			
			findUsr = con.prepareStatement("select * from BankUsers where UserName = ? AND UserPassword = ?");
			
			findUsr.setString(1, usrName);
			findUsr.setString(2, pwd);
			
			ResultSet rs = findUsr.executeQuery();  
			
			if(!rs.wasNull())
			{  
				BankDBDriver.setCurrUser(rs.getInt(1));
				return true;
			}
			con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
		return false;
	}
	
	@Override
	public void viewAccounts(int userID) 
	{
		System.out.println("Current User Accounts:");
		
		try {
			Connection con = ConnFactory.getConnection();
			
			PreparedStatement vwAcc;
			
			vwAcc = con.prepareStatement("select * from Account where userID = ? OR jointID = ?");
		
			vwAcc.setInt(1, userID);        
			vwAcc.setInt(2, userID);
			
			ResultSet rs = vwAcc.executeQuery();  
			while(rs.next())
			{  
				System.out.println(rs.getInt(1) + " " + rs.getDouble(2) + " " +
						rs.getInt(3) + " " + rs.getInt(4) + " " +
						rs.getInt(5) + " " + rs.getInt(6));  			
			}
			
			con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}
	
	@Override
	public void openAccount(double startBal,int userID, int jointID, int isJoint) 
	{		
		try {
			Connection con = ConnFactory.getConnection();
			
			PreparedStatement opAcc;
			
			opAcc = con.prepareStatement("insert into Account values(IncAccNum.nextVal,?,?,?,?,?)");
		        
			opAcc.setDouble(2, startBal); 
			opAcc.setInt(3, userID);
			opAcc.setInt(4, 0);
			opAcc.setInt(5, isJoint);
			opAcc.setInt(6, jointID);
			
			int i=opAcc.executeUpdate();  
			BankDBDriver.log.info(i+" records inserted"); 
			
			con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}

	@Override
	public void deleteAccount(int accNum, int accBal, int userID) 
	{
		int an = 0;
		boolean testing = true;
		
		do
		{			
			System.out.println("Please enter the account number on the account you wish to close: ");
			an = BankDBDriver.checkIntInput();
			
			double ab = checkAccount(an);
			
			if(ab > 0)
				System.out.println("Can only close empty account. $");
			else
			{
				try {
					Connection con = ConnFactory.getConnection();
					
					PreparedStatement delAcc;
					
					delAcc = con.prepareStatement("delete from Account where Acc_Num=? AND Acc_Bal=? AND UserID = ?");
					
					delAcc.setInt(an, 1);
					delAcc.
					
					int i=delAcc.executeUpdate();  
					System.out.println(i+" records deleted"); 
					BankDBDriver.log.info(i+" records inserted");
					
					con.close();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			}
		}
		while(testing);
	}
	
	@Override
	public double checkAccount(int accNum) 
	{
		try {
			Connection con = ConnFactory.getConnection();
			
			PreparedStatement chAcc;
			
			chAcc = con.prepareStatement("select * from Account where Acc_Num = ?");
		
			chAcc.setInt(1, userID);        
			chAcc.setInt(2, userID);
			
			ResultSet rs = chAcc.executeQuery();  
			if(!rs.wasNull())
			{  
				System.out.println(rs.getInt(1) + " " + rs.getInt(2) + " " +
						rs.getInt(3) + " " + rs.getInt(4) + " " +
						rs.getInt(5) + " " + rs.getInt(6));  			
			}
			
			con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}

	@Override
	public void deposit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdraw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transfer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logout() 
	{
		//handled by driver
	}

}
