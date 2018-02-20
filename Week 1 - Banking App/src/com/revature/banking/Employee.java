package com.revature.banking;

import java.io.Serializable;

/* Employees of the bank should be able to view all of their customers information
 * 	-This includes:
 * 		-Account information
 * 		-Account balances
 * 		-Personal information
 * Employees should be able to approve/deny open applications for accounts
 */
public class Employee implements Serializable
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String employID;
	private String employPass;
	
	Employee()
	{
		super();
	}
	public Employee(String employID, String employPass) 
	{
		super();
		this.employID = employID;
		this.employPass = employPass;
	}	
	
	//apply or deny account application method
	public void appOrDenyAcc(Customer c)
	{
		Account accRev;
		
		if(c.isAppForAcc())
		{
			accRev = c.getRequestInfo();
			c.printCustomerInfo();
			c.printAccounts();
			
			accRev.printRequestDetails(c.getName());
			
			for(Customer cust : accRev.getAccountHolders())
			{
				if(!cust.isAccActive())
				{
					System.out.println(cust.getName() + 
							" does not have an account with us.");
				}
			}
			
			System.out.print("Would you like to approve this account? Y/N :");
			if(BankDriver.yesNoPrompt())
			{
				accRev.setAccountNum();
				c.setAccActive(true);
				
				for(Customer cust : accRev.getAccountHolders())
				{
					cust.addAccount(accRev);
				}				
			}
			else
			{
				c.setRequestInfo(null);
			}
		}
		else
		{
			System.out.println("No account activation requests present.");			
		}
	}
	
	//getters / setters
	public String getEmployID() 
	{
		return employID;
	}
	public void setEmployID(String employID) 
	{
		this.employID = employID;
	}

	public String getEmployPass() 
	{
		return employPass;
	}
	public void setEmployPass(String employPass) 
	{
		this.employPass = employPass;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Employee [employID=" + employID + ", employPass=" + employPass + "]";
	}
	
	
	
	
	
}
