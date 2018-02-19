package com.revature.core.java;

import java.util.ArrayList;

/**
 * 
 * @author johne
 * Q19. Create an ArrayList and insert integers 1 through 10. 
 * Display the ArrayList. Add all the even numbers up and 
 * display the result. Add all the odd numbers up and display 
 * the result. Remove the prime numbers from the ArrayList and print out the remaining ArrayList.
 */
public class Q19_EvenOddPrime {

	public static void main(String[] args) {
		ArrayList<Integer> intList = new ArrayList<Integer>();
		
		/*
		 * create new Integer object and add into intList during loop
		 */
		for(int i = 1; i <= 10; i++) {
			Integer n = new Integer(i);
			intList.add(n);
		}
		
		/*
		 * display integer list
		 */
		System.out.print("Integer List: ");
		for(Integer i : intList) {
			System.out.print(i + " ");
		}
		
		/*
		 * find sum of even numbers and display the sum to sumEvenList
		 */
		int sumEven = 0;
		ArrayList<Integer> sumEvenList = new ArrayList<Integer>();
		for(Integer i : intList) {
			if (i % 2 == 0) {
				sumEven += i;
			}
		}
		
		/*
		 * find sum of odd numbers from intList and display the sum
		 */
		int sumOdd = 0;
		for(Integer i : intList) {
			if(i % 2 != 0) {
				sumOdd += i;
			}
		}
		
		/*
		 * loop through intList, remove the prime, and add composite number into 
		 * primeList.
		 * 
		 */
		ArrayList<Integer> compositeList = new ArrayList<Integer>();
		for(Integer i : intList) {
			if(!isPrime(i)) {
				compositeList.add(i);
			}
		}
		
		System.out.println("\nSum of even numbers: " + sumEven);
		System.out.println("Sum of odd numbers: " + sumOdd);
		System.out.print("Composite numbers: " );
		for(Integer i : compositeList) {
			System.out.print(i + " ");
		}
		
		
	}//end of main
	
	/**
	 * to test number 1. One does not have exactly two divisor 
	 * that is why one is not a prime. To add to that, a prime number is 
	 * a "number greater than 1" if its positive factors are one and  
	 * itself.
	 * 
	 * every composite number has a factor less than or equal to its square root and
	 * if sqrt(num) != a whole number, it is most likely a prime number.
	 * 
	 * as long as a factor is less than or equal to sqrt(num), then
	 * check if num is divisible by its factors. This is a simple check for
	 * prime factorization. 
	 * 
	 * @param num to check for isPrime
	 * @return boolean if number is prime
	 */
	public static boolean isPrime(int num) {
		boolean isPrime = true;
		
		if(num <= 1)
			isPrime = false;
		
		for(int factor = 2; factor <= Math.sqrt(num); factor++) {
			if(num % factor == 0) {
				isPrime = false;
			}
		}
		return isPrime;
	}//end of isPrime
}//	end of class
