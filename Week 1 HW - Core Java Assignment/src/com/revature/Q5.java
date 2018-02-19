package com.revature;

import java.util.InputMismatchException;

//Q5. Write a substring method that accepts a string 'str' and an integer 'idx' 
// and returns the substring contained between 0 and idx-1 inclusive.  
//   Do NOT use any of the existing substring methods in the String, 
//   StringBuilder, or StringBuffer APIs.
public class Q5 
{

	public void workQ5()
	{
		String str;
		int idx;
		
		str = getStrInput();
		
		idx = getIntInput(str);		
			
		System.out.println("The Substring from 0 to " + idx + " is: " + indexString(str, idx));
	}
	
	public String indexString(String s, int x)
	{
		String subStr = s.substring(0, x);
		
		return subStr;
	}	
	
	String getStrInput()
	{
		String temp = null;
		
		do
		{
			System.out.println("Enter a String: ");
			temp = Driver.sc.nextLine();
			
			if(temp.isEmpty())
				System.out.println("INPUT ERROR! Please enter some data!");
		}
		while (temp.isEmpty());
		return temp;
	}
	
	int getIntInput(String s)
	{
		int test = s.length();
		int idx = 0;
		
		do
		{		
			try
			{
				System.out.println("Enter an integer: ");			
				idx = Driver.sc.nextInt();
			}
			catch(InputMismatchException exception)
			{
				System.out.println("TYPE MISMATCH! Please try again!");
			}		
			
			if(idx > test)
				System.out.println("OUT OF BOUNDS ERROR! Please try again!");
		}
		while (idx > test);
		
		Driver.sc.nextLine();
		
		return idx;
	}
}
