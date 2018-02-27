package com.revature.JDBCBank;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.BankBeans.*;
import com.revature.JDBCBank.old.Account;
import com.revature.JDBCBank.old.BankDriver;
import com.revature.JDBCBank.old.Customer;

/*Build the application using Java 8
 * All interaction with the user should be done through the console using the Scanner class
 * Users of the bank should be able to register with a username and password, and
 * apply to open an account.
 * 		-Users should be able to apply for joint accounts
 * Once the account is open, users should be able to withdraw, deposit, and transfer
 * funds between accounts
 * 		-All basic validation should be done, such as trying to input negative amounts,
 * 		overdrawing from accounts etc.
 * Employees of the bank should be able to view all of their users information
 * 	-This includes:
 * 		-Account information
 * 		-Account balances
 * 		-Personal information
 * Employees should be able to approve/deny open applications for accounts
 * Bank superUsers should be able to view and edit all accounts
 *  -This includes:
 * 		-Approving/denying accounts
 * 		-Withdrawing, depositing, transferring from all accounts
 * 		-Canceling accounts
 * All information should be persisted using text files and serialization
 */

public class BankDBDriver 
{		
	public static final Scanner sc = new Scanner(System.in);
	
	static IO io = IO.getInstance();
	static ConnFactory dbConn = ConnFactory.getInstance();
	public static Logger log = Logger.getLogger(BankDBDriver.class.getName());
	
	private static UserDAOImpl userCmd;
	private static SuperUserDAOImpl adminCmd;
	
	private static int currUser;
	private static SuperUser currSuper;
	
	private static boolean loggedIn, isUser, isSuper, progRunning;
		
	public static void main(String[] args) 
	{				
		initializeBank();						//starts program loop and loads in data from files
		
		do
		{										//-log in loop
			while(!loggedIn && progRunning)		//while not logged in and program running
			{
				startMenu();				// try to login
			}
			
			if(isUser)							//if logged in as user
			{
				userMenu();					// open user menu
			}
			else if(isSuper)					// otherwise
			{
				superUserMenu();					// open superUser menu
			}			
		}
		while(progRunning);
	}
	
	//Login/Logout Methods
	public static void startMenu()
	{		
		int choice = 0;
		boolean logging = true;
		while(logging)
		{
			//prompt user for input selection
			System.out.print("\nAre you a user or employee?"
					+ "\n  1) User Login"
					+ "\n  2) Admin Login"
					+ "\n  3) Exit Program");	
			//check for input mismatch exceptions
			choice = checkIntInput();
					
			switch(choice)						//for the user input given
			{
			case 1: 
				userLoginMenu();				//if 1), go to user login
				logging = false;
				break;
			case 2:
				superUserLogin();				//if 2), go to superUser login
				logging = false;
				break;
			case 3:
				logging = false;				//if 3), exit the program.
				exitProgram();
				break;
			default:							//tell user when input not valid
				System.out.println("Invalid Selection. Please try again.");
				break;
			}
		}			
	}	
	
	//determine if new user or returning user
	public static void userLoginMenu()
	{
		int choice = 0;
		boolean logging = true;
		while(logging)
		{	//prompt user for input
			System.out.println("\nAre you a new or returning user?"
					+ "\n  1)New User"
					+ "\n  2)Returning User"
					+ "\n  3)Go Back");
			
			choice = checkIntInput();
			
			switch(choice)					//given the user input
			{
			case 1: 	
				logging = false;			//if 1, create new user
				createNewUser();
				break;
			case 2:							//if 2, log in existing user
				logging = false;
				userLogin();
				break;
			case 3:
				logging = false;			//if 3, go back
				break;
			default:
				System.out.println("Invalid Selection. Please try again.");
				break;
			}
		}
	}	
	//new user creation
	public static void createNewUser()
	{
		String uName, password, rName, phNum;
		
		//get user input for creating new User obj.
		System.out.print("Please enter your name: ");
		rName = sc.nextLine();
		
		System.out.print("Please enter your phone #: ");
		phNum = sc.nextLine();
		
		System.out.print("Please create a username: ");
		uName = sc.nextLine();
		
		System.out.print("Please create a password: ");
		password = sc.nextLine();
		
		System.out.println("Your username is: " + uName +
				"\nYour password is: " + password +
				"\n Please record this information for your records.");
		
		//user constructor
		adminCmd.createUser(rName, phNum, uName, password);
	
		isUser = true;				//flag the user is a user
		loggedIn = true;			//end the log in loop
		
	}	
	
	//user id and password prompt
	public static void userLogin()
	{
		boolean searching = true;
		String n, p;
		
		do
		{	//get user input for name and password
			System.out.print("Please enter your username: ");
			n = sc.next();
			
			System.out.print("Please enter your password: ");
			p = sc.next();
							
			searching = userCmd.login(n, p);
			
			//if still looking for user
			if(searching == true)		
			{
				//login info not found
				System.out.print("\nInvalid username or password. "
						+ "\n  Try again? Y/N ");
				
				if(yesNoPrompt())	//if user wants to try again
					continue;		// restart the loop
				else				//otherwise
					break;			//break from loop to go back.
			}
		}
		while (searching);
	}	
	
	//superUser user name and pw prompt
	public static void superUserLogin()
	{
		String admID, admPW;
		
		boolean logging = true;
		do
		{	//get user input
			System.out.print("Enter SuperUser ID: ");
			admID = sc.nextLine();
			
			System.out.print("Enter password: ");
			admPW = sc.nextLine();
			
			adminCmd.login(admID, admPW);
			
			//if superUser not found, ask user if they want to try again.
			if(logging == true)
			{
				System.out.print("\nInvalid username or password. " 
						+ "Try again? Y/N ");
				
				if(yesNoPrompt())
					continue;
				else
					logging = false;
			}
			
		}
		while(logging);
	}
	
	//logout current user
	public static void logout()
	{	
		//set all log in variables to false
		loggedIn = isUser = isSuper = false;
	}
	
	//show & select user menu options
	private static void userMenu() 
	{
		boolean looping = true;
		int choice = 0;
		
		do
		{		
			//output options
			System.out.print("============================================="
					+ "\n --- Welcome --- "
					+ "\nPlease make a selection: "
					+ "\n_______________________________________________________________"
					+ "\n| 1) Account Details               |  4) Withdraw Funds       |"
					+ "\n|                                  |  5) Deposit Funds        |"   
					+ "\n| 2) Apply for an account          |  6) Transfer Funds       |"
					+ "\n| 3) Apply for a Joint account     |  7) Close Account        |"
					+ "\n|                                                             |"
					+ "\n| 8) Logout                                                   |"
					+ "\n|_____________________________________________________________|"
					+ "\n");
			choice = checkIntInput();
			
			switch(choice)								//for user input
			{
			case 1: 
				userCmd.viewAccounts(getCurrUser());
				break;
			case 2:
				applyForAccount(false);
				break;
			case 3:
				applyForAccount(true);
				break;
			case 4:										//if 4, withdraw request
				
				break;
			case 5:										//if 5, deposit request
				
				break;
			case 6:										//if 6, transfer request
				
				break;	
			case 7:										//if 7, close an account
				
				break;
			case 8:
				logout();								//if 8, logout and go back to login top menu
				looping = false;
				break;
			default:
				System.out.println("Invalid Selection. Please try again.");
				break;
			}
		
		}while(looping);
		
	}
		
	private static void applyForAccount(boolean isJoint)
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
			
			BankDriver.sc.nextLine();
			
			System.out.print("You have requested a new account. "
					+ "\n Please enter the starting capital of the account: ");
			cash = BankDriver.checkDoubleInput();
			
			requestInfo = new Account(cash);
			
			if(isJoint)
			{
				BankDriver.sc.nextLine();
				
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
					+ "\n  We will inform you when we reach a decision. Thank you for your patience.\n");
		}
	}	

	private static void superUserMenu()
	{
		boolean looping = true;
		int choice = 0;
		
		do
		{		
			//more options for the superUser
			System.out.print("============================================="
					+ "\n --- Welcome, oh glorious one! --- "
					+ "\n What would you like to do, milord? "
					+ "\n_______________________________________________________________"
					+ "\n| 1) Create User                   |  4) Withdraw Funds       |"
					+ "\n| 2) View User Info                |  5) Deposit Funds        |"   
					+ "\n|                                  |  6) Transfer Funds       |"
					+ "\n| 3) Approve/Deny Application      |  7) Close Account        |"
					+ "\n|                                                             |"
					+ "\n| 8) Logout                                                   |"
					+ "\n|_____________________________________________________________|"
					+ "\n");
			choice = checkIntInput();	
			
			switch(choice)
			{
			case 1: 	
				
				break;
			case 2:
				
				break;
			case 3:
			
				break;
			case 4:
				
				break;
			case 5:
				
				break;													
			case 6:													
				
				break;
			case 7:
				
				break;
			case 8:
				
				break;
			default:
				System.out.println("Invalid Selection. Please try again.");
				break;
			}
		
		}while(looping);
	}
	
	
	
	
	
	
	
	//ensure user input is y or n and only those
	//loop statement that gets Y,y or N,n input
	public static boolean yesNoPrompt()
		{
			String ch;
			
			sc.nextLine();
			
			while(true)														//loops until recieves valid user input
			{
				//System.out.print(" Try Again? Y/N: ");						//prompt user to continue
				ch = sc.next();			

				switch (ch)
				{
				case "y":													
				case "Y":													//if yes,
					return true;											// return true -> keeps loop going.
				case "n":
				case "N":													//if no,
					return false;											// return false -> stops the loop
				default:													//all other options are invalid,
					System.out.println("INVALID INPUT! Please try again!");	// 
				}
			}
		}

	//ensure user input is a double
	//loop statement to get double value from user
	public static double checkDoubleInput()
	{
		double dub;
		do
		{
			try
			{
				System.out.print("\n Input: $");
				dub = sc.nextDouble();
			}
			catch(InputMismatchException exception)
			{
				System.out.print("TYPE MISMATCH! Try again? Y/N :");
				if(yesNoPrompt() == true)
					continue;
				else
					break;
			}
			
			if(dub > 0)
				return dub;
			else if(dub < 0)
				System.out.println("Can not enter a negative number. Please try again.");
			else
				break;
		}
		while(true);
		
		return 0;		
	}
	
	//ensure user input is an int
	//loop statement to get int value from user 
	public static int checkIntInput()
	{
		int i;
		do
		{
			try
			{
				System.out.print("\n Input: ");
				i = sc.nextInt();
			}
			catch(InputMismatchException exception)
			{
				System.out.print("TYPE MISMATCH! Try again? Y/N :");		
				if(yesNoPrompt())
					continue;
				else
					break;
			}			
			return i;
		}
		while(true);
		
		return 0;		
	}
	
	//start main loop and check if testing
	private static void initializeBank()
	{
		progRunning = true;			//program running
		
		System.out.println("\n ==== Welcome to the Bank of Boot ====\n");
	}
	
	//exit program
	public static void exitProgram()
	{
		progRunning = false;		//end program running loop
									
		System.out.println("\nThank you for using Bank of Boot. Please come again.");
		
		sc.close();					//close scanner
	}

	public static int getCurrUser() {
		return currUser;
	}

	public static void setCurrUser(int currUser) {
		BankDBDriver.currUser = currUser;
	}

	public static SuperUser getCurrSuper() {
		return currSuper;
	}

	public static void setCurrSuper(SuperUser currSuper) {
		BankDBDriver.currSuper = currSuper;
	}

	
	//getters and setters
	
	

}
