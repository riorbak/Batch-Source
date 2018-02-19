package com.revature;

//Q1: Perform a bubble sort on the following integer array: 1,0,5,6,3,2,3,7,9,8,4
public class Q1 {

	// Int array given as part of the question
	private static int[] toBeBubbled = {1,0,5,6,3,2,3,7,9,8,4};	
		
	
	//Bubble Sort Method;
	public void bubbleSort()
	{	
		//orginally passed in an int[] named 'ar', but shifted function call
		// to driver class. This declaration is to prevent renaming.
		// Plus, 'ar' is easier to say than 'toBeBubbled'.
		int[] ar = toBeBubbled;
		
		//print the array before sorting
		System.out.println("Before sort: ");
		printArray(ar);
		
		//length of the array 
		int len = ar.length;
		
		//1st loop starts at the front and moves through array
		//  for comparison
		for (int i = 0; i < (len-1); i++) 
		{
			//2nd loop checks the rest of the values in the array
			for (int j = 0; j < (len-1 - i); j++)
			{
				int temp;				//stores value to allow swapping
				
				if (ar[j] > ar[j + 1])	//if the value of an int is bigger than the int to its right; 
				{						
					temp = ar[j];		// swap the values
					ar[j] = ar[j+1];	
					ar[j + 1] = temp;
				}
				
			}
		}
		
		//print the array after sorting
		System.out.println("\nAfter sort: ");
		printArray(ar);
		//new line separator for next question
		System.out.println();
		
	}
	
	//simple loop to print output
	public void printArray(int[] ar)
	{
		for(int i : ar)
		{
			System.out.print(i + " ");
		}
	}

}
