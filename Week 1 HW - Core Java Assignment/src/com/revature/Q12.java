package com.revature;

import java.util.ArrayList;

//Q12. Write a program to store numbers from 1 to 100 in an array. 
// Print out all the even numbers from the array. Use the enhanced 
// FOR loop for printing out the numbers.
public class Q12 {
	
	ArrayList<Integer> numbers = new ArrayList<Integer>();		//ArrayList of Integer objects
	
	//Populate ArrayList from 1 - 100
	void generateNumberList()
	{
		for(int i = 1; i <= 100; i++)				
		{
			numbers.add(new Integer(i));
		}
	}
	
	void findEvenNumbers()
	{		
		generateNumberList();
		
		System.out.print("Even Numbers: ");
		
		for(Integer i : numbers)						//for every Integer obj in 'numbers':
		{
			if(i % 2 == 0)								// if it's divisible by 2,
				System.out.print(i.intValue() + " ");	//	output it.
		}
	}

}
