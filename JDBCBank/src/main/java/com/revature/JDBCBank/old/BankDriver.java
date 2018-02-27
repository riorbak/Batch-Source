package com.revature.JDBCBank.old;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/*Build the application using Java 8
 * All interaction with the user should be done through the console using the Scanner class
 * Customers of the bank should be able to register with a username and password, and
 * apply to open an account.
 * 		-Customers should be able to apply for joint accounts
 * Once the account is open, customers should be able to withdraw, deposit, and transfer
 * funds between accounts
 * 		-All basic validation should be done, such as trying to input negative amounts,
 * 		overdrawing from accounts etc.
 * Employees of the bank should be able to view all of their customers information
 * 	-This includes:
 * 		-Account information
 * 		-Account balances
 * 		-Personal information
 * Employees should be able to approve/deny open applications for accounts
 * Bank admins should be able to view and edit all accounts
 *  -This includes:
 * 		-Approving/denying accounts
 * 		-Withdrawing, depositing, transferring from all accounts
 * 		-Canceling accounts
 * All information should be persisted using text files and serialization
 */

public class BankDriver 
{		
	public static final Scanner sc = new Scanner(System.in);
	
	static IO io = IO.getInstance();
	static JDBC dbConn = JDBC.getInstance();
	private static final String customerData = "files\\old\\CustomerData.txt";
	private static final String employeeData = "files\\old\\EmployeeData.txt";
	private static final String adminData = "files\\old\\AdminData.txt";
	
	private static ArrayList<Customer> customers = new ArrayList<>();
	private static ArrayList<Employee> employees = new ArrayList<>();
	private static ArrayList<Admin> admins = new ArrayList<>();
	
	private static boolean loggedIn,isCust, isEmp, isAdmin, progRunning, progTest;
	private static Customer currCustomer;
	private static Employee currEmployee;
	private static Admin currAdmin;
		
	public static void main(String[] args) 
	{		
		dbConn.startConnection();
		
		initializeBank();						//starts program loop and loads in data from files
		
		do
		{										//-log in loop
			while(!loggedIn && progRunning)		//while not logged in and program running
			{
				loginStartMenu();				// try to login
			}
			
			if(isCust)							//if logged in as customer
			{
				customerMenu();					// open customer menu
			}
			else if(isEmp)						// or if logged in as employee
			{
				employeeMenu();					// open employee menu
			}
			else if(isAdmin)					// otherwise
			{
				adminMenu();					// open admin menu
			}			
		}
		while(progRunning);
	}
	
	//Login/Logout Methods
	public static void loginStartMenu()
	{		
		int choice = 0;
		boolean logging = true;
		while(logging)
		{
			//prompt user for input selection
			System.out.print("\nAre you a customer or employee?"
					+ "\n  1) Customer Login"
					+ "\n  2) Employee Login"
					+ "\n  3) Exit Program");	
			//check for input mismatch exceptions
			choice = checkIntInput();
			
			
			switch(choice)						//for the user input given
			{
			case 1: 
				customerLoginMenu();			//if 1), go to customer login
				logging = false;
				break;
			case 2:
				employeeLoginMenu();			//if 2), go to employee login
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
	//determine if new user or returning customer
	public static void customerLoginMenu()
	{
		int choice = 0;
		boolean logging = true;
		while(logging)
		{	//prompt user for input
			System.out.print("\nAre you a new or returning customer?"
					+ "\n  1)New Customer"
					+ "\n  2)Returning Customer"
					+ "\n  3)Go Back");
			
			choice = checkIntInput();
			
			switch(choice)					//given the user input
			{
			case 1: 	
				logging = false;			//if 1, create new customer
				createNewCustomer();
				break;
			case 2:							//if 2, log in existing customer
				logging = false;
				customerLogin();
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
	//new customer creation
	public static void createNewCustomer()
	{
		String uName, password, rName, phNum;
		Customer c;
		
		//get user input for creating new Customer obj.
		System.out.print("Please enter your name: ");
		rName = sc.nextLine();
		
		System.out.print("Please enter your phone #: ");
		phNum = sc.nextLine();
		
		System.out.print("Please create a username: ");
		uName = sc.nextLine();
		
		System.out.print("Please create a password: ");
		password = sc.nextLine();
		
		System.out.println("\nYour username is: " + uName +
				"\nYour password is: " + password +
				"\n Please record this information for your records.");
		
		//customer constructor
		c = new Customer(rName, phNum, uName, password);
		
		//add new customer to arraylist of customers
		customers.add(c);
		
		
		currCustomer = c;			//set current customer to the new customer
		isCust = true;				//flag the user is a customer
		loggedIn = true;			//end the log in loop
		
	}	
	//customer id and password prompt
	public static void customerLogin()
	{
		//if no other customers exist
		if(customers.isEmpty() && customers != null)
		{	//tell user and create new customer
			System.out.println("\nWe apologize but there are no customers at this time. "
					+ "\nWe aren't very good at advertising. \n\nPlease create an account:\n");
			createNewCustomer();
		}	
		else	//otherwise
		{
			boolean searching = true;
			String n, p;
			
			do
			{	//get user inpt for name and pw
				System.out.print("Please enter your username: ");
				n = sc.nextLine();
				
				System.out.print("Please enter your password: ");
				p = sc.nextLine();
				
				//compare name and password for each customer that exists
				for (Customer c : customers)
				{
					if (n.equals(c.getUsername()))
					{						
						if(p.equals(c.getPassword()))
						{						//when match found
							currCustomer = c;	// set current customer to found customer
							
							isCust = true;		//flag user as a customer
							loggedIn = true;	//end log in loop
							searching = false;	//stop looking for customer
							break;
						}
					}		
				}					
				
				//if still looking for customer
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
	}	
	
	//determine if user is employee or admin
	public static void employeeLoginMenu()
	{
		int choice = 0;
		boolean logging = true;
		do
		{	//get user input
			System.out.print("\nAre you an Employee or an Admin?"
					+ "\n  1) Employee"
					+ "\n  2) Admin"
					+ "\n  3) Go Back");
			
			choice = checkIntInput();
			
			switch(choice)				//for user input
			{
			case 1: 
				logging = false;		// if 1, log in as employee
				employeePrompt();
				break;
			case 2:
				logging = false;		// if 2, log in as admin
				adminPrompt();
				break;
			case 3:
				logging = false;		// if 3, go back
				break;
			default:
				System.out.println("Invalid Selection. Please try again.");
			}
		}
		while(logging);
	}	
	//employee Id and pw prompt
	public static void employeePrompt()
	{
		String empID, empPW;
		
		boolean logging = true;
		do
		{	//get user id and pw
			System.out.print("Enter Employee ID: ");
			empID = sc.nextLine();
			
			System.out.print("Enter password: ");
			empPW = sc.nextLine();
			
			// match user id & pw to existing employee
			for(Employee emp : employees)
			{
				if(emp.getEmployID().equals(empID))
				{
					if(emp.getEmployPass().equals(empPW))
					{						//if found
						currEmployee = emp;	// set as current employee 
						isEmp = true;		// set user to employee
						loggedIn = true;	// end log in loop
						logging = false;
					}
				}
			}
			
			// if still trying to log in, ask user if they want to try again
			if(logging == true)
			{
				System.out.print("\nInvalid username or password. "
						+ "\n Try again? Y/N ");
				
				if(yesNoPrompt())
					continue;
				else
					logging = false;
			}
			
		}
		while(logging);
	}
	//admin user name and pw prompt
	public static void adminPrompt()
	{
		String admID, admPW;
		
		boolean logging = true;
		do
		{	//get user input
			System.out.print("Enter Admin ID: ");
			admID = sc.nextLine();
			
			System.out.print("Enter password: ");
			admPW = sc.nextLine();
			
			//compare input to all admins
			for(Admin ad : admins)
			{
				if(ad.getEmployID().equals(admID))
				{
					if(ad.getEmployPass().equals(admPW))
					{						//if found
						currAdmin = ad;		//set current admin to found one
						isAdmin = true;		//flag that admin is logged in
						loggedIn = true;	// end log in loop
						logging = false;
					}
				}
			}
			
			//if admin not found, ask user if they want to try again.
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
		loggedIn = isCust = isEmp = isAdmin = false;
	}
	
	//show & select customer menu options
	private static void customerMenu() 
	{
		boolean looping = true;
		int choice = 0;
		
		do
		{		
			//output options
			System.out.print("============================================="
					+ "\n --- Welcome, " + currCustomer.getUsername()+ " --- "
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
				currCustomer.printCustomerInfo();		//if 1, output customer info
				currCustomer.printAccounts();
				break;
			case 2:
				currCustomer.applyForAccount(false);	//if 1, apply for base account
				break;
			case 3:
				currCustomer.applyForJointAcc();		//if 3, apply for joint account				
				break;
			case 4:										//if 4, withdraw request
				currCustomer.withdraw(currCustomer.selectAccount());
				break;
			case 5:										//if 5, deposit request
				currCustomer.deposit(currCustomer.selectAccount());
				break;
			case 6:										//if 6, transfer request
				currCustomer.transfer(currCustomer.selectAccount());
				break;	
			case 7:										//if 7, close an account
				currCustomer.closeAccount();
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
	//show & select employee options
	private static void employeeMenu()
	{
		boolean looping = true;
		int choice = 0;
		
		do
		{		
			//show user options
			System.out.print("\nGet to work Drone #" + currEmployee.employNum + ": "
					+ "\nPlease make a selection: "
					+ "\n 1) Lookup Customer"
					+ "\n 2) Logout"
					+ "\n ");

			choice = checkIntInput();	
			
			switch(choice)
			{
			case 1: 					//find user
				lookupCustMenu();
				break;
			case 2:						// or logout
				logout();
				looping = false;
				break;
			default:
				System.out.println("Invalid Selection. Please try again.");
				break;
			}
		
		}while(looping);
		
	}
	//show and select admin options
	private static void adminMenu()
	{
		boolean looping = true;
		int choice = 0;
		
		do
		{		
			//output admin options
			System.out.print("\nAll hail the glorious one, " + currAdmin.getEmployID()+ "! "
					+ "\nWhat would you like to do, milord?: "
					+ "\n 1) Lookup Customer"
					+ "\n 2) Logout"
					+ "\n ");
			choice = checkIntInput();
			
			switch(choice)
			{
			case 1: 
				lookupCustMenu();			//find customer
				break;
			case 2:
				logout();					// or logout
				looping = false;
				break;
			default:
				System.out.println("Invalid Selection. Please try again.");
				break;
			}
		
		}while(looping);
		
	
	}
	//find a customer to change
	private static void lookupCustMenu()
	{
		currCustomer = null;
		boolean looping = true;
		int choice = 0;
		
		do
		{		
			//prompt search criteria
			System.out.print("\nHow do you want to look up customer? "
					+ "\n 1) By Name"
					+ "\n 2) By Account Number"
					+ "\n 3) Go Back"
					+ "\n ");
			choice = checkIntInput();
			
			switch(choice)
			{
			case 1: 
				currCustomer = lookupCustByName();		//search by name
				break;
			case 2:
				currCustomer = lookupCustByAcc();		//search by acc
				break;
			case 3:
				looping = false;						//or go back
				break;
			default:
				System.out.println("Invalid Selection. Please try again.");
				break;
			}
			
			//determine if user is employ or admin and open appropriate menu
			if(looping && currCustomer != null)
				if(isEmp)
					helpCust(currCustomer);
				else
					helpCustAdmin(currCustomer);
		
		}while(looping);
		
	}
	//find customer by their personal name
	private static Customer lookupCustByName()
	{
		Customer cust = null;
		boolean looping = true;
		
		String name;
		
		do
		{	//ask for input
			System.out.print("Enter customer's name: ");
			name = sc.nextLine();
			
			for (Customer c : customers)		//search each customer
			{
				if(c.getName().equals(name))	//if name matches
				{
					cust = c;					//return em.
					looping = false;
					break;
				}
			}
			
			//if customer not found, ask to keep going or go back
			if(cust == null)
			{	
				System.out.print("Unable to find " + name + "'s account. Please check the name."
						+ "\n Try again? Y/N :");
				
				if(!yesNoPrompt())
					looping = false;
			}
		
		}while(looping);
		
		return cust;
	}
	
	//same as above, but with account number instead
	private static Customer lookupCustByAcc()
	{
		Customer cust = null;
		boolean looping = true;
		
		int accNum;
		
		do
		{		
			System.out.print("Enter Acount Number: ");
			accNum = checkIntInput();
			
			for (Customer c : customers)
			{
				for(Account a : c.getAccounts())
				{
					if(a.getAccountNum() == accNum)
					{
						cust = c;
						looping = false;
						break;
					}
				}
			}
			
			if(cust == null)
			{
				System.out.print("Unable to find account. Please check the Account Number."
						+ "\n Try again? Y/N :");
				if(!yesNoPrompt())
				{
					looping = false;
				}
			}
		
		}while(looping);
		
		return cust;
	}
	
	//employee options to assist customer
	private static void helpCust(Customer c)
	{
		boolean looping = true;
		int choice = 0;
		
		if(c != null)
		{
			do
			{		
				//output options
				System.out.print("\nHelping " + c.getName()+ ": "
						+ "\nPlease make a selection: "
						+ "\n 1) View Customer Personal Info"
						+ "\n 2) View Customer Account Info"
						+ "\n 3) View Customer Account Balance"
						+ "\n 4) Approve/Deny Account Application"
						+ "\n ");
				choice = checkIntInput();	
			
				switch(choice)
				{
				case 1: 
					currCustomer.printCustomerInfo();	//show customer info
					break;
				case 2:
					currCustomer.printAccounts();		//show customer accounts
					break;
				case 3:
					currCustomer.printAccounts();		//show customer balance TODO: fix this repetition
					break;
				case 4:
					currEmployee.appOrDenyAcc(currCustomer);	//approve or deny account application
					break;
				default:
					System.out.println("Invalid Selection. Please try again.");
					break;
				}
			
			}while(looping);
		}
	}
	
	private static void helpCustAdmin(Customer c)
	{
		boolean looping = true;
		int choice = 0;
		
		if(c != null)
		{
			do
			{		
				//more options for the admin
				System.out.print("\nHelping the peasant, " + c.getName()+ ": "
						+ "\nPlease make a selection: "
						+ "\n 1) View Customer Personal Info"
						+ "\n 2) View Customer Account Info"
						+ "\n 3) View Customer Account Balance"
						+ "\n 4) Approve/Deny Account Application"
						+ "\n 5) Withdraw money"
						+ "\n 6) Deposit money"
						+ "\n 7) Transfer money"
						+ "\n 8) Cancel account!!!"
						+ "\n");
				choice = checkIntInput();	
				
				switch(choice)
				{
				case 1: 	
					currCustomer.printCustomerInfo();			//same base 4 as employee
					break;
				case 2:
					currCustomer.printAccounts();;
					break;
				case 3:
					currCustomer.printAccounts();
					break;
				case 4:
					currEmployee.appOrDenyAcc(currCustomer);
					break;
				case 5:
					currCustomer.withdraw(currCustomer.selectAccount());	//but admin can also withdraw, deposit
					break;													//	or transfer money, as well as cancel
				case 6:														//  accounts
					currCustomer.deposit(currCustomer.selectAccount());
					break;
				case 7:
					currCustomer.transfer(currCustomer.selectAccount());
					break;
				case 8:
					currAdmin.cancelAccount(currCustomer);
					break;
				default:
					System.out.println("Invalid Selection. Please try again.");
					break;
				}
			
			}while(looping);
		}
	}
	
	//find and return accounts by customer account num
	public static Account searchCustForAcc(int accNum)
	{
		Account acc = null;
		
		for(Customer c : customers)
		{
			for(Account a : c.getAccounts())
			{
				if (a.getAccountNum() == accNum)
					acc = a;
			}
		}
		
		return acc;
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
			{
				sc.nextLine();
				return dub;
			}
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
			
			sc.nextLine();
			
			return i;
		}
		while(true);
		
		return 0;		
	}
	
	//start main loop and check if testing
	private static void initializeBank()
	{
		progRunning = true;			//program running
		
		if(!progTest)				//test cases loaded in the 
		{
			loadData();
		}
		else
			testCases();	
		
		System.out.println("\n ------- Welcome to the Bank of Boot -------");
	}
	
	//save/load data to/from file
	@SuppressWarnings("unchecked")
	private static void loadData()
	{
		
		if(IO.checkForData(customerData))				//if customer data exists, load em up
		{
			customers = (ArrayList<Customer>) IO.readObject(customerData);
			System.out.println("\nLoaded customer data");
		}
		else											//else, start from scratch
			System.out.println("\nNo customers to load. Start an ad campaign or something!");	
		
		if(IO.checkForData(employeeData))				//if employee data exists, load em up
		{
			employees = (ArrayList<Employee>) IO.readObject(employeeData);
			System.out.println("Loaded employee data");
		}
		else											//otherwise, add default employee
		{
			System.out.println("Employee Data not found. Loading default emp.");
			Employee emp = new Employee("emp", "emp");
			employees.add(emp);
		}
		
		if(IO.checkForData(adminData))					//if admin data exists, load em up
		{
			admins = (ArrayList<Admin>) IO.readObject(adminData);
			System.out.println("Loaded admin data");
		}
		else											//otherwise, add default admin
		{
			System.out.println("Admin Data not found. Loading default admin.");
			Admin ad = new Admin("admin", "admin");
			admins.add(ad);			
		}			
	}
	private static void saveData()
	{
		IO.writeObject(admins, adminData);
		IO.writeObject(employees, employeeData);
		IO.writeObject(customers, customerData);
	}
	
	//exit program
	public static void exitProgram()
	{
		progRunning = false;		//end program running loop
		
		saveData();					//save data
									
		System.out.println("\nThank you for using Bank of Boot. Please come again.");
		
		sc.close();					//close scanner
	}
	
	//hard coded accounts to test functionality.
	//debug test code
	public static void testCases()
	{
		//ask if you want to run tests
		System.out.print("Run Test Case Scenario? Y/N :");
		
		// if yes, add em in.
		if(yesNoPrompt())
		{		
			//Create customer with no accounts active
			Customer c1 = new Customer("boot", "test", "boot", "test1");
			c1.setAccActive(false);
			
			//create customer with 2 accounts
			Customer c2 = new Customer("test2", "555-555-5555", "test2", "test2");
			Account a2 = new Account(50000);
			a2.setAccountNum(111111);
			Account a3 = new Account(4000);
			a3.setAccountNum(222222);
			c2.addAccount(a2);
			c2.addAccount(a3);
			
			//add them to the customers list
			customers.add(c1);
			//System.out.println(c1.toString());
			customers.add(c2);
			//System.out.println(c2.toString());
			
			//create new employee
			Employee emp = new Employee("emp", "emp");
			employees.add(emp);
			
			//create new admin
			Admin ad = new Admin("admin", "admin");
			admins.add(ad);
			
		}

	}
}
