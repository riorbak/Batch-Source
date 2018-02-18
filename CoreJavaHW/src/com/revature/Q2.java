package com.revature;

public class Q2 {
	private int numFibs = 0; //keep track of how many times the fibonnacci sequence was done
	
	public void run() {
		System.out.print("First 25 Fibonacci numbers: ");
		fibonacci(0, 1, 25);	//start with 0, the next one is automatically 1, do it 25 times
		System.out.println();	//formatting
	}
	
	public void fibonacci(int first, int second, int repeats) {
		numFibs++;	//increase the counter
		System.out.print(first + " ");	//print the first number
		int next = second + first;	//next number will be the first plus the second
		if(numFibs <= repeats) {	//if not done it 25 (or whatever specified amount of repeats is) times yet
			fibonacci(second, next, repeats);	//call recursively to add the second and the next number and print
		}
	}
}
