package com.revature.BankBeans;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int accountNum;
	private double accountBalance;	
	private boolean isJoint;

	private ArrayList<User> accountHolders;
	
	public Account()
	{
		super();
		accountHolders = new ArrayList<>();
	}

	public int getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
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

	public ArrayList<User> getAccountHolders() {
		return accountHolders;
	}

	public void setAccountHolders(ArrayList<User> accountHolders) {
		this.accountHolders = accountHolders;
	}
	
	
}
