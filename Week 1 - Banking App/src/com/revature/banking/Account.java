package com.revature.banking;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable
{
	private static final long serialVersionUID = 1L;

	static int folio = 10000;
	
	private int accountNum;
	private double accountBalance;	
	private boolean isJoint;

	private ArrayList<Customer> accountHolders;
	
	//constructors, with chaining
	Account()
	{
		super();
		accountHolders = new ArrayList<>();
		accountNum = 0;
	}
	public Account(double accountBalance)
	{
		this();
		this.accountBalance = accountBalance;
	}

	public Account(double accountBalance, ArrayList<Customer> cust, boolean isJoint) 
	{
		this(accountBalance);
		this.isJoint = isJoint;
		this.accountHolders = cust;
	}
	
	//drop cash from account balance to give to customer
	public double closeAccount()
	{
		double cash = accountBalance;
		accountBalance = 0;
		accountHolders = null;
		
		return cash;
	}
	
	
	//getters / setters
	public int getAccountNum() {
		return accountNum;
	}
	public void setAccountNum() {
		this.accountNum = ++folio;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public boolean isJoint() {
		return isJoint;
	}
	public void setJoint(boolean isJoint) {
		this.isJoint = isJoint;
	}
	public ArrayList<Customer> getAccountHolders() {
		return accountHolders;
	}
	public void setAccountHolders(ArrayList<Customer> accountHolders) {
		this.accountHolders = accountHolders;
	}

	//output methods
	public void printAccountDetails(String name) 
	{
		String data = "\nAccount No: " + accountNum +
				"\nBalance: " + accountBalance;
		
		if(isJoint)
		{		
			data = data + "\n-This is a joint account shared with: ";
			for(Customer c : accountHolders)
			{
				if(c.getName() != name)					
					data = data + "\n   *" + c.getName();
			}
		}
		
		System.out.println(data); 
	}
	
	public void printRequestDetails(String name) 
	{
		String data = "\nNew Account Starting Balance: " + accountBalance;
		
		if(isJoint)
		{		
			data = data + "\n-This is a joint account shared with: ";
			for(Customer c : accountHolders)
			{
				if(c.getName() != name)
				{
					data = data + "\n   *" + c.getName();
					
					if(!c.isAccActive())
						data = data + " -- Not a current customer.";
				}
			}
		}
		
		System.out.println(data); 
	}
	@Override
	public String toString() {
		return "Account [accountNum=" + accountNum + ", accountBalance=" + accountBalance + ", isJoint=" + isJoint
				+ ", accountHolders=" + accountHolders + "]";
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
