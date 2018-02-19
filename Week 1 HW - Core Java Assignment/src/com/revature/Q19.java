package com.revature;

import java.util.ArrayList;
import java.util.Iterator;

/*Q19. Create an ArrayList and insert integers 1 through 10. Display the ArrayList. 
 *  Add all the even numbers up and display the result. Add all the odd numbers up 
 *   and display the result. Remove the prime numbers from the ArrayList and print 
 *   out the remaining ArrayList.
 */
public class Q19 
{
	ArrayList<Integer> numbers = new ArrayList<Integer>();
	
	
	void runQ19()
	{
		populateNumbers();
		
		displayNumbers(numbers);
		
		addNumbers(false);
		addNumbers(true);
		
		removePrimes();
		
		displayNumbers(numbers);		
	}	
	
	//add numbers to ArrayList
	void populateNumbers()
	{
		for(int i = 1; i <= 10; i++)
			numbers.add(new Integer(i));
	}
	
	//display method for our ArrayList
	void displayNumbers(ArrayList<Integer> n)
	{
		System.out.print("ArrayList<Integer> = [");
		
		for(int i = 0; i < n.size(); i++)
		{
			//text formating
			if(i == n.size() - 1)								
				System.out.print(n.get(i).intValue() + "]");	
			else
				System.out.print(n.get(i).intValue() + ", ");
		}
		System.out.println();	//new line as spacer
	}
	
	//add all odd or all even numbers together and output answer
	// (passed boolean used to determine odd or even)
	void addNumbers(boolean odd)
	{
		int sum = 0, temp;
		
		for(Integer i : numbers)
		{
			temp = i.intValue();
						
			if(temp % 2 == 0 && !odd)			//if divisible by 2 AND odd == false
				sum += temp;					//  its even
			else if (temp % 2 != 0 && odd)		//if not divisible by 2 AND odd == true
				sum += temp;					//  its odd
		}
		
		if(odd)
			System.out.println("The sum of the odd numbers: " + sum);
		else
			System.out.println("The sum of the even number: " + sum);
	}
	
	//determine if int is prime and remove it if so.
	void removePrimes()
	{
		//int counter = 0;										//Debug Code;
		
		Iterator<Integer> iter = numbers.iterator();	//iterator used to remove obj from ArrayList
		
		while(iter.hasNext())							//while there is something left to iterate
		{
			//System.out.print(counter);						//Debug Code;
			
			int x = (Integer)iter.next();				//set x to the next iteration
			
			if(x == 1)									//1 is not prime 
				continue;
			else if(x == 2)								//2 is prime
				iter.remove();							// remove from the list
			else if(x % 2 == 0)							//All other even nums can't be prime,
				continue;								// so skip em.
			else
			{
				boolean isPrime = true;					//determines if we remove from list
				for(int i = 3; i < x; i+=2)				//checking all odd numbers, starting from 3
				{
					if(x % i == 0)						//if i is a multiple of x,
					{
						isPrime = false;				// its not prime,
						break;							// break out of loop
					}
				}
				
				if(isPrime)								//if its prime,
					iter.remove();						// remove from the list
			}
			
			//counter++;										//Debug Code;										
		}
	}
}
