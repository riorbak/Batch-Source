package com.revature;

/* Q18. Write a program having a concrete subclass that inherits three abstract methods from a superclass.  
 * Provide the following three implementations in the subclass corresponding to the abstract methods in the superclass:
 * 1. 	Check for uppercase characters in a string, and return ‘true’ or ‘false’ depending if any are found.
 * 2. 	Convert all of the lower case characters to uppercase in the input string, and return the result.
 * 3. 	Convert the input string to integer and add 10, output the result to the console.
 * Create an appropriate class having a main method to test the above setup. 
 */
public class Q18 extends SuperQ18 {

	
	
	public void runQ18()
	{
		String str = getUserInput();
		
		System.out.println("String has Upper Case? " + checkForUpperCase(str));
		
		System.out.println("String Upper Case Converstion: " + convLowerToUpper(str));
		
		System.out.println("Convert String to Int & add 10: " + convToIntAddTen(str));
		
	}
	
	public String getUserInput()
	{
		String temp;
		do
		{
			System.out.print("Enter a String: ");								//user input
			temp = Driver.sc.nextLine();
			
			if(temp.isEmpty())													//warn user
				System.out.println("INPUT ERROR! Please enter some data!");		
		}
		while (temp.isEmpty());													//ensures string isn't empty
		return temp;
	}
	
	@Override
	public boolean checkForUpperCase(String s) 
	{		
		for(int i = 0; i < s.length(); i++)			//check each character in string
		{
			if(Character.isUpperCase(s.charAt(i)))	//if its upper case,
				return true;						// return true
		}
		
		return false;								//otherwise, return false
	}

	@Override
	public String convLowerToUpper(String s) 			
	{
		String temp;
		temp = s.toUpperCase();							//convert string to upper case
		
		return temp;									// then return it
	}

	@Override
	public String convToIntAddTen(String s) 
	{
		try 
		{
			Integer x = Integer.valueOf(s);				//convert the string to an Integer obj
			x += 10;									// add 10
			return (x.toString());						// return it as a string
		}
		catch (NumberFormatException e)					//if String is not a valid integer, tell the user
		{
			return "\nERROR: NumberFormatException - String was not a valid integer.";
		}
		
	}

	
	
}
