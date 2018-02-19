package com.revature;

import java.util.ArrayList;

//Q9. Create an ArrayList which stores numbers from 1 to 100 and prints out all the prime numbers to the console.
public class Q9 {
	
	ArrayList<Integer> numbers = new ArrayList<Integer>();
	
	//populate ArrayList from 1-100
	void generateNumberList()
	{
		for(int i = 1; i <= 100; i++)
		{
			numbers.add(new Integer(i));
		}
	}
	
	void findPrimeNum()
	{
		generateNumberList();
		
		ArrayList<Integer> prime = new ArrayList<Integer>();
		
		for(Integer x : numbers)
		{
			if(x == 1)									//1 is not prime 
				continue;
			else if(x == 2)								//2 is prime
				prime.add(x);			
			else if(x % 2 == 0)							//All other even nums can't be prime,
				continue;								// so skip em.
			else
			{
				boolean isPrime = true;					//determines if we add it to our prime list
				for(int i = 3; i < x; i+=2)				//checking all odd numbers, starting from 3
				{
					if(x % i == 0)						//if i is a multiple of x,
					{
						isPrime = false;				// its not prime,
						break;							// break out of loop
					}
				}
				
				if(isPrime)								//if its prime,
					prime.add(x);						// add to the list
			}
		}
		
		//output our findings
		System.out.println("Prime numbers from 1 - 100: " + prime.toString());
		
	}	
}