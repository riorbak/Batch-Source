package com.revature;

import java.util.InputMismatchException;

//Q6. Write a program to determine if an integer is even without using the modulus operator (%)
public class Q6 
{
	public void determineEvenNum()
	{
		Integer num = new Integer(getUserInput());					//Create new Integer 
			
		String str = num.toString();								//Convert Integer to String
				
		char check = str.charAt(str.length()-1);					//Get numeral character at end of string
		//System.out.println(str + " Last Digit: " + check);		//Debug Code      
		
		switch(check)												//Checking char value of "check"
		{
		case '0':
		case '2':
		case '4':
		case '6':
		case '8':	System.out.println(num + " is even."); 			//If the var is one of the cases,
					break;											//  the num is even and exit switch
		default:    System.out.println(num + " is odd.");			//Otherwise, var is odd.
					break;	
		}		
	}
	
	int getUserInput()
	{
		int n = 0;
		boolean running = true;			//loop variable
		
		do
		{
			try
			{
				System.out.println("Enter a valid integer: ");				//Request input from user
				n = Driver.sc.nextInt();									//Get input
				running = false;											//Exit loop (if no exception thrown)
			}
			catch(InputMismatchException exception)
			{
				System.out.println("INPUT INVALID! Please try again!");		//Tell user to try again
				Driver.sc.nextLine();										//Clear scanner
				continue;													//Repeat the loop
			}			
		}
		while (running);				
		
		return n;
	}
	
}
