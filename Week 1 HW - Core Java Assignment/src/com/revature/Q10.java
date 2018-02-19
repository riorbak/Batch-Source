package com.revature;

//Q10. Find the minimum of two numbers using ternary operators.
public class Q10 {

	int a = 0;
	int b = 0;
	
	public void findMinVal()
	{
		System.out.println("Input first integer value: ");
		a = Driver.sc.nextInt();
		Driver.sc.nextLine();
		
		do
		{
			System.out.println("Input second integer value: ");
			b = Driver.sc.nextInt();
			Driver.sc.nextLine();
			
			if(a == b)
				System.out.println("INPUT ERROR! Please try again!");
		}
		while (a == b);
		System.out.println("The minimum of " + a + " & " +
				b + " is: " + (a < b ? a : b));
		
	}
	
}
