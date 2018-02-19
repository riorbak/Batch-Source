package com.revature;

import java.text.DecimalFormat;
import java.util.InputMismatchException;

/*Q17. Write a program that calculates the simple interest on the principal, 
 *  rate of interest and number of years provided by the user. 
 *  Enter principal, rate and time through the console using the Scanner class.
 * Interest = Principal* Rate* Time 
 */
public class Q17 {
	
	//used to limit decimal places to 2
	private static DecimalFormat df2 = new DecimalFormat(".##");			

	public void calcIntrest()
	{
		//variable declarations
		double principal, rate, time;								
		
		//get user input for variables
		principal = getPrinc();				
		rate = getRate();
		time = getTime();
		
		//show interest calculation
		System.out.println("The Intrest is: $" + df2.format(principal * rate * time));
				
	}
	
	public double getPrinc()
	{
		double p;
		do
		{
			try																//check for user input type 
			{
				System.out.print("Please enter the principal: $");			
				p = Driver.sc.nextDouble();
				Driver.sc.nextLine();
				
				return p;													//return input
			}
			catch(InputMismatchException exception)							//if not a double,
			{
				System.out.println("TYPE MISMATCH! Please try again!");		//tell user
				continue;													// and try again
			}			
		
		}while(true);
		
	}
	
	public double getRate()
	{
		double r;
		do
		{
			try																//check for user input type
			{
				System.out.print("Please enter the rate: ");
				r = Driver.sc.nextDouble();
				Driver.sc.nextLine();
				
				return r;													//return input
			}
			catch(InputMismatchException exception)							//if not a double,
			{
				System.out.println("TYPE MISMATCH! Please try again!");		//tell user
				continue;													// and try again
			}			
		
		}while(true);
	}
	
	public double getTime()
	{
		double yr;
		do
		{
			try																//check user input type
			{
				System.out.print("Please enter the num of years: ");
				yr = Driver.sc.nextDouble();
				Driver.sc.nextLine();
				
				return yr;													//return input
			}
			catch(InputMismatchException exception)							//if not a double,
			{
				System.out.println("TYPE MISMATCH! Please try again!");		//tell user
				continue;													// and try again
			}			
		
		}while(true);
	}
	
}
