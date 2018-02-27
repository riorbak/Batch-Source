package com.revature.JDBCBank;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

	@Override
	public boolean login(String usrName, String pwd)
	{
		try {
			Connection con = ConnFactory.getConnection();
			
			PreparedStatement findUsr;
			
			findUsr = con.prepareStatement("select * from BankUsers where UserName = ? AND UserPwd = ?");
			
			findUsr.setString(1, usrName);
			findUsr.setString(2, pwd);
			
 
			ResultSet rs = findUsr.executeQuery();
			
			while(rs.next())
			{  
				BankDBDriver.setCurrUser(rs.getInt(1));
				BankDBDriver.log.info ("UserID: " + rs.getInt(1) + " UserName: " + usrName + " logged in"); 
				
				con.close();
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
		try {
			Connection con = ConnFactory.getConnection();
			
			PreparedStatement vwAcc;
			
			vwAcc = con.prepareStatement("select * from Accounts where UserID = ?");
		
			vwAcc.setInt(1, userID);        
			
			vwAcc.executeQuery(); 
			ResultSet rs = vwAcc.getResultSet();
			
			if(!rs.next())
			{
				System.out.println("User has no active accounts");
			}
			else
			{
				System.out.println("Current User Accounts:");
				do
				{  
					System.out.println("Account Num: " + rs.getInt(1) + " | Account Bal: $" + rs.getDouble(2) 
							+ " | Account Active: " + rs.getBoolean(4) );			
				}
				while(rs.next());
				
				BankDBDriver.log.info ("UserID: " + userID + " viewed accounts."); 
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
			
			opAcc = con.prepareStatement("insert into Accounts values(IncAccNum.nextVal,?,?,?,?,?)");
		        
			opAcc.setDouble(1, startBal); 
			opAcc.setInt(2, userID);
			opAcc.setInt(3, 0);
			opAcc.setInt(4, isJoint);
			opAcc.setInt(5, jointID);
			
			opAcc.executeUpdate();  
			BankDBDriver.log.info("UserID: " + userID + " applied for an account."); 
			
			con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}

	@Override
	public void deleteAccount(int userID) 
	{
		int an = 0;
		boolean testing = true;
		
		do
		{			
			System.out.println("Please enter the account number on the account you wish to close: ");
			an = BankDBDriver.checkIntInput();
			
			double bal = checkBalance(an);
			
			if(bal > 0)
			{
				System.out.println("Can only close an empty account. $");
				testing = false;
			}
			else
			{
				try {
					Connection con = ConnFactory.getConnection();
					
					PreparedStatement delAcc;
					
					delAcc = con.prepareStatement("delete from Accounts where Acc_Num = ?");
					
					delAcc.setInt(1, an);
					
					int i = delAcc.executeUpdate(); 
					
					System.out.println(i+" records deleted"); 
					BankDBDriver.log.info("Account: " + an + " deleted");
					
					con.close();
					
					testing = false;
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			}
		}
		while(testing);
	}
	
	@Override
	public double checkBalance(int accNum) 
	{
		double bal;
		try {
			Connection con = ConnFactory.getConnection();
			
			PreparedStatement chAcc;
			
			chAcc = con.prepareStatement("select Acc_Bal from Accounts where Acc_Num = ?");
		
			chAcc.setInt(1, accNum);        
			
			ResultSet rs = chAcc.executeQuery();  
			
			if(rs.next())
			{  
				bal = (double)rs.getInt(1);
				con.close();
				return bal;
			}			
			else
				con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;  
	}
	
	@Override
	public boolean checkAccountStatus(int userID)
	{
		try {
			Connection con = ConnFactory.getConnection();
			
			PreparedStatement chAcc;
			
			chAcc = con.prepareStatement("select * from Accounts where UserID = ?");
		
			chAcc.setInt(1, userID);        
			
			ResultSet rs = chAcc.executeQuery();  			

			if(!rs.next())
			{
				System.out.println("User has no active accounts");
				con.close();
			}
			else
			{
				while(rs.next())
				{
					if(rs.getInt(4) == 0)
					{
						con.close();
						return true;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;  		
	}
	
	@Override
	public int getAccNum()
	{
		boolean testing = true;
		int ac = 0;
		
		do
		{
			System.out.print("Please enter account number: ");
			ac = BankDBDriver.checkIntInput();
			
			try {
				Connection con = ConnFactory.getConnection();
				
				PreparedStatement chAcc;
				
				chAcc = con.prepareStatement("select * from Accounts where Acc_Num = ?");
			
				chAcc.setInt(1, ac);        
				
				ResultSet rs = chAcc.executeQuery();  			

				if(!rs.next())
				{
					System.out.println("Invalid Account Number");
					con.close();
				}
				else
				{
					con.close();
					testing = false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}while(testing);
		
		return ac;
	}
	
	@Override
	public void deposit() 
	{
		int an = getAccNum();
		double depAmt = 0;
		
		if(checkAccountStatus(BankDBDriver.getCurrUser()))
		{
			System.out.print("Please enter the ammont you would like to deposit: $");
			depAmt = BankDBDriver.checkDoubleInput();
			
			try {
				Connection con = ConnFactory.getConnection();
				
				CallableStatement dep = con.prepareCall("{call deposit (?, ?}");
				
				dep.setInt(1, an);		
				dep.setDouble(2, depAmt);        
				
				dep.execute();  
				con.close();
				BankDBDriver.log.info ("UserID: " + BankDBDriver.getCurrUser() + " deposited $" + depAmt + " into Account Num: " + an); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		else
			System.out.println("No active accounts at this time. Please contact an admin.");
	}

	@Override
	public void withdraw() 
	{
		int an = getAccNum();
		double wdAmt = 0;

		boolean gettingInput = true;
	
		double currBal = checkBalance(an);
		
		if(checkAccountStatus(BankDBDriver.getCurrUser()))
		{
			do		
			{							
				if(currBal > 0)
				{							
					System.out.print("Current balance: $" + currBal
							+ "\n Enter the withdrawl ammount: ");
					wdAmt = BankDBDriver.checkDoubleInput();
					
					if(wdAmt == 0)
					{
						System.out.println("--Operation aborted!--");
						break;
					}
					
					if(wdAmt <= currBal)
					{
						try 
						{
							Connection con = ConnFactory.getConnection();
							
							CallableStatement dep = con.prepareCall("{call withdrawl(?, ?)}");
							
							dep.setInt(1, an);		
							dep.setDouble(2, wdAmt);        
							
							dep.execute();  
							con.close();
							
							BankDBDriver.log.info ("UserID: " + BankDBDriver.getCurrUser() + " withdrew $" + wdAmt + " from Account Num: " + an); 			
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}						
						
						gettingInput = false;
					}
					else
						System.out.println("Invalid withdrawl ammount. " 
								+ "\nPlease enter an ammount less than or equal to your total account balance.\n");
				}	
				else
				{
					System.out.println("Insufficient Funds. Withdrawl impossible at this time.");
					gettingInput = false;
				}
			}
			while(gettingInput);	
		}
		else
			System.out.println("No active accounts at this time. Please contact admin.");
	}

	@Override
	public void transfer() 
	{
		
		
	}

}
