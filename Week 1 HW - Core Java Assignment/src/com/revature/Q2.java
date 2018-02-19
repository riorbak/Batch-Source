package com.revature;

//Q2. Write a program to display the first 25 Fibonacci numbers beginning at 0.
public class Q2 {

	//constant variable declaration for array size.
	private static final int SIZE = 25;
	
	public void findFibonacci()
	{
		int[] fib = new int[25];
		
		//loop to calculate the fibonacci sequence
		for(int i = 0; i < SIZE; i++)
		{
			if(i == 0)			
				fib[i] = 0;						//first number is always 0
			else if(i == 1)		
				fib[i] = 1;						//second number is always 1
			else
				fib[i] = fib[i-1] + fib[i-2];	//all others are the sum of the previous two numbers
		}
		
		for(int i = 0; i < SIZE; i++)
			System.out.println((i+1) + ") " + fib[i]);
		
	}
	
}
