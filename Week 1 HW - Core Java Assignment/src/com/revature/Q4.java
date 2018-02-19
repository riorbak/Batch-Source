package com.revature;

//Q4: Write a program to compute N factorial.
public class Q4 
{
	public int num = 5;	
	
	public void factorial()
	{
		System.out.println("Enter a number: ");
		num = Driver.sc.nextInt(); // Scans the next token of the input as an int. 
		
		//temp local value
		int temp = 0;
		
		//start at 1 because 0 would make all work moot
		// also, loop needs the max value of num
		for(int i = 1; i <= num; i++)
		{
			if(i == 1)		//if at the start of loop
				temp = i;	// initialize temp value (to 1);
			else			//else
				temp *= i;	// temp = temp * i			
		}		
		
		System.out.println(num + "! is: " + temp);	//Output the value;
		Driver.sc.nextLine();						//Clear the scanner
	}
}
