package com.revature.banking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;

/* Customers of the bank should be able to register with a username and password, and
 *  apply to open an account.
 * 		-Customers should be able to apply for joint accounts
 * Once the account is open, customers should be able to withdraw, deposit, and transfer
 *  funds between accounts
 * 		-All basic validation should be done, such as trying to input negative amounts,
 * 		 overdrawing from accounts etc.
 */
public class Customer implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//User info
	private String username;
	private String password;
	
	//Personal info
	private String name;
	private String phone;
	
	//Account info
	private boolean accActive;
	private ArrayList<Account> accounts;
	private Account requestInfo;
	
	//Account request status
	public boolean appForAcc;
	public boolean appForJoint;
	
	//constructors using constructor chaining
	public Customer()
	{
		super();
		accounts = new ArrayList<>();
	}
	public Customer(String name, String phone)
	{
		this();
		this.name = name;
		this.phone = phone;
	}	
	public Customer(String name, String phone, String username, String password)
	{
		this(name, phone);
		
		this.username = username;
		this.password = password;	
		accActive = true;
	}

	//apply for base acount (if isJoint == false)
	public void applyForAccount(boolean isJoint)
	{
		if(appForAcc == true)
		{
			System.out.print("You currently have an application pending. Please wait until the previous application"
					+ "\n   is processed. Thank you for your patience.");
		}
		else
		{		
			appForAcc = true;
			
			double cash;
			ArrayList<Customer> accountHolders = new ArrayList<>();
			accountHolders.add(this);
			
			System.out.print("You have requested a new account. "
					+ "\n Please enter the starting capital of the account: ");
			cash = BankDriver.checkDoubleInput();
			
			requestInfo = new Account(cash);
			
			if(isJoint)
			{
				String name, phone;
				System.out.print("This is a joint account application. Please provide information "
						+ "\n about the person you are sharing the account with:"
						+ "\n -Name: ");
				name = BankDriver.sc.nextLine();
				System.out.print(" -Phone: ");
				phone = BankDriver.sc.nextLine();
				
				Customer jointCust = new Customer(name, phone);
				
				accountHolders.add(this);
				accountHolders.add(jointCust);
								
				requestInfo = new Account(cash, accountHolders, isJoint);
				
			}
			
			System.out.println("Your request will be process within 3 buisness days."
					+ "\n  We will inform you when we reach a decision. Thank you for your patience.");
		}
	}	
	//force an application for new joint account
	public void applyForJointAcc()
	{
		applyForAccount(true);
	}	
	//check if customer has an active account
	public boolean checkForActiveAccounts(Customer c)
	{
		if(c.isAccActive())
			return true;
		else
		{
			System.out.println("You have no active accounts available.");
			return false;
		}
		
	}
	//add new account to customer's accountlist
	public void addAccount(Account acc)
	{
		accounts.add(acc);
	}
	
	//select account from list of accounts
	public Account selectAccount()
	{
		boolean gettingInput = true;
		int accNum;
		Account acc = null;
		
		if(checkForActiveAccounts(this))
		{
			printAccounts();
			
			do
			{	
				try
				{
					System.out.print("Enter the number of the account you wish to modify: "
							+ "\n Account Number: ");
					accNum = BankDriver.sc.nextInt();
					BankDriver.sc.nextLine();
				}
				catch(InputMismatchException exception)
				{
					System.out.println("TYPE MISMATCH! Please try again!");
					continue;
				}		
				
				for(Account a : accounts)
				{
					if(a.getAccountNum() == accNum)
					{
						acc = a;
						gettingInput = false;
					}
				}
				
				if(gettingInput)
				{
					System.out.print("Invalid account number. Try again? Y/N :");						//prompt user to continue
					
					if(!BankDriver.yesNoPrompt())
					{
						gettingInput = false;
					}						
				}
			}
			while(gettingInput);
		}
		
		return acc;
	}	
	//deposit to account
	public void deposit(Account a)
	{
		boolean gettingInput = true;
		double dep;
		
		Account acc = a;
				
		do
		{				
			System.out.print("Enter the deposit ammount: ");
			dep = BankDriver.checkDoubleInput();
			
			if(dep == 0)
			{
				System.out.println("--Operation aborted!--");
				break;
			}
			
			acc.setAccountBalance(acc.getAccountBalance() + dep);
			gettingInput = false;
			
			System.out.println("Deposited $" + dep +" into Account Num "
					+ acc.getAccountNum() + "."
					+ "Current Balance: $" + acc.getAccountBalance() + "." );					
		}
		while(gettingInput);
	}
	//withdraw from account
	public void withdraw(Account a)
	{
		boolean gettingInput = true;
		double wd;
		
		Account acc = a;
		
		do
		{							
			if(acc.getAccountBalance() > 0)
			{							
				System.out.print("Current balance: $" + acc.getAccountBalance()
						+ "\n Enter the withdrawl ammount: ");
				wd = BankDriver.checkDoubleInput();
				
				if(wd == 0)
				{
					System.out.println("--Operation aborted!--");
					break;
				}
				
				if(wd <= acc.getAccountBalance())
				{
					acc.setAccountBalance(a.getAccountBalance() - wd);
					gettingInput = false;
				}
				else
					System.out.println("Invalid withdrawl ammount. " 
							+ "\nPlease enter an ammount less than or equal to your total account balance.");
			}	
			else
			{
				System.out.println("Insufficient Funds. Withdrawl impossible at this time.");
			}
		}
		while(gettingInput);		
	}	
	
	//setup account transfer
	public void transfer(Account a)
	{
		boolean transferAborted = false;
		boolean gettingInput = true;
		int accNum;
		
		Account startAcc = a;
		double cash = transferWithdraw(startAcc);
		
		Account endAcc;
		
		if(cash != 0)
		{
			do
			{	
				try
				{
					System.out.print("Enter the number of the account you wish to transfer to: "
							+ "\n Account Number: ");
					accNum = BankDriver.sc.nextInt();
					BankDriver.sc.nextLine();
				}
				catch(InputMismatchException exception)
				{
					System.out.println("TYPE MISMATCH! Please try again!");
					continue;
				}		
				
				endAcc = BankDriver.searchCustForAcc(accNum);
				
				
				if(endAcc == null)
				{
					System.out.print("Invalid account number. Try again? Y/N :");						//prompt user to continue
					
					if(!BankDriver.yesNoPrompt())
					{
						gettingInput = false;
						transferAborted = true;
					}						
				}
				else
				{
					transferDeposit(endAcc, cash, transferAborted);
					gettingInput = false;
				}				
			}
			while(gettingInput);		
		}
		else
			transferAborted = true;
		
		if(transferAborted)
			transferDeposit(startAcc, cash, transferAborted);
	}
	//withdraw money as part of transfer
	public double transferWithdraw(Account a)
	{
		boolean gettingInput = true;
		double wd = 0;
		
		Account acc = a;
		
		do
		{							
			if(acc.getAccountBalance() > 0)
			{							
				System.out.print("Current balance: $" + acc.getAccountBalance()
						+ "\n Enter the transfer ammount (or 0 to abort): ");
				wd = BankDriver.checkDoubleInput();
				
				if(wd == 0)
				{
					System.out.println("--Transfer aborted!--");
					break;
				}				
				else if(wd <= acc.getAccountBalance())
				{
					acc.setAccountBalance(acc.getAccountBalance() - wd);
					gettingInput = false;
				}
				else
					System.out.println("Invalid withdrawl ammount. " 
							+ "\nPlease enter an ammount less than or equal to your total account balance.");
			}	
			else
			{
				System.out.println("Insufficient Funds. Transfer impossible at this time.");
			}
		}
		while(gettingInput);
		
		return wd;	
	}
	//deposit money as part of transfer
	public void transferDeposit(Account a, double amt, boolean transferAborted)
	{
		boolean gettingInput = true;
		
		Account acc = a;
				
		do
		{	
			if(!transferAborted)
			{
				acc.setAccountBalance(acc.getAccountBalance() + amt);
				gettingInput = false;
				
				System.out.println("Transfered $" + amt +" into Account Num "
						+ acc.getAccountNum() + "."
						+ "Current Balance: $" + acc.getAccountBalance() + "." );
			}
			else
				System.out.println("Transfer was aborted. Funds returned.");
		}
		while(gettingInput);
	}
	
	//out put account info
	public void printAccounts()
	{
		if(checkForActiveAccounts(this))
		{
			System.out.println("Account Details for " + this.getName() + ": ");
			for(Account a : accounts)
			{
				a.printAccountDetails(this.getName());		
			}
		}
	}
	
	//output customer info
	public void printCustomerInfo()
	{
		String data = "\nName: " + this.getName() + 
				"\nPhone: " + this.getPhone();
		
		System.out.println(data);;
	}
	
	//setters and getters
	//getters and setters
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public boolean isAppForAcc() {
		return appForAcc;
	}
	public void setAppForAcc(boolean appForAcc) {
		this.appForAcc = appForAcc;
	}
	
	public boolean isAccActive() {
		return accActive;
	}
	public void setAccActive(boolean accActive) {
		this.accActive = accActive;
	}

	

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

			
	public Account getRequestInfo() {
		return requestInfo;
	}
	public void setRequestInfo(Account requestInfo) {
		this.requestInfo = requestInfo;
	}
	
	public boolean isAppForJoint() {
		return appForJoint;
	}
	public void setAppForJoint(boolean appForJoint) {
		this.appForJoint = appForJoint;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Customer [username=" + username + ", password=" + password + ", name=" + name + ", phone=" + phone
				+ ", accActive=" + accActive + ", accounts=" + accounts + ", appForAcc=" + appForAcc + ", appForJoint="
				+ appForJoint + "]";
	}
	
	
	
	
}
