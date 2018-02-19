package com.revature;

/*Q16. Write a program to display the number of characters for a string input. 
 * The string should be entered as a command line argument using (String [ ] args). 
 */
public class Q16 {

	public static void main(String[]args)
	{
		int sum = 0;
		
		for(int i = 0; i < args.length; i++) 
		{
			System.out.print(args[i] + " ");
            sum += args[i].length();
		}
		
		System.out.println("\nNumber of Characters: " + sum);
		

	}
	
}
