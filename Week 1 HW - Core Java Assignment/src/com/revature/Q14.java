package com.revature;

import java.util.InputMismatchException;
import java.util.Date;
import java.lang.Math;
import java.text.SimpleDateFormat;

/*Q14. Write a program that demonstrates the switch case. Implement the following functionalities in the cases:java 
Case 1: Find the square root of a number using the Math class method.
Case 2: Display today’s date.
Case 3: Split the following string and store it in a string array.
           	“I am learning Core Java”
*/
public class Q14 {	
	
	public void menuChoice()
	{
		int choice = 0;				//switch statement variable
		
		do
		{
			try
			{
				System.out.print("\nPlease make a selection: " + 			//propmt user input
						"\n1) Square Root" +
						"\n2) Display Today's Date" +
						"\n3) Split a String" +
						"\n  Your Choice: ");		
				choice = Driver.sc.nextInt();								//get int from user
				Driver.sc.nextLine();										//clear scanner
			}
			catch(InputMismatchException exception)							//if not an int,
			{
				System.out.println("TYPE MISMATCH! Please try again!");		//tell user
				continue;													// and try again
			}					
			
			switch(choice)												
			{
			case 1:
			{
				double num;
				try
				{
					System.out.println("\nPlease Enter a Float Value");		//prompt user for float
					num = Driver.sc.nextDouble();							//get float from user
					Driver.sc.nextLine();									//clear scanner
				}
				catch(InputMismatchException exception)						//if not an int
				{
					System.out.println("TYPE MISMATCH! Please try again!");	//tell user
					continue;												// and try again
				}
				sqrRoot(num);
				break;
			}
			case 2:
			{
				displayDate();												
				break;
			}				
			case 3:	
			{
				String str = "I am learning Core Java.";					//hard coded string to split
				splitStr(str);
				break;
			}
			default:   														//if not a valid selection
				System.out.println("INVALID INPUT! Please try again!");		//tell user
				break;
			}	
		}while (keepRunning());
	}
	
	//finds the square root of user's double and prints it
	void sqrRoot (double db)
	{
		System.out.println("The Square Root of " + db + " is: " + Math.sqrt(db));
	}

	//prints today's date in day-month-year format
	void displayDate()
	{
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("E dd MMMM yyyy G");
		System.out.println("Today's Date: " + df.format(today));
	}
	
	//splits string passed into it.
	void splitStr (String s)
	{
		String[] words = s.split(" ");
		
		System.out.print("Words in String: [");
		for(int i = 0; i < words.length; i++)
		{
			if(i == (words.length -1))				//if last word
				System.out.print(words[i] + "]");	// close the bracket
			else									//otherwise
				System.out.print(words[i] + ", ");	// use commas to separate
		}
		System.out.println();						//new line as spacer
	}	
	
	//asks user if they want to keep going to try other options
	boolean keepRunning()
	{
		String ch;
		
		while(true)														//loops until recieves valid user input
		{
			System.out.print("   Continue? Y/N: ");						//prompt user to continue
			ch = Driver.sc.next();
			Driver.sc.nextLine();			

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
}
