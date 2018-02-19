package com.revature.core.java;

import java.util.Scanner;

/**
 * 
 * @author johne
 * Q13. Display the triangle on the console as follows using any type of loop.  
 * Do NOT use a simple group of print statements to accomplish this.
    0
    1 0
    1 0 1
    0 1 0 1

 */
public class Q13_Triangle {
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter a number: ");
		int num = input.nextInt();
		
		for(int i = 0; i <= num; i++) {
			System.out.println("");
			for(int j = 0; j <= i; j++) {
				System.out.print(j + " ");
			}
		}
	}
}
