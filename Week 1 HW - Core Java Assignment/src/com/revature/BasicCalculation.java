package com.revature;

/*Q15. Write a program that defines an interface having the following methods: 
 * addition, subtraction, multiplication, and division.  
 * Create a class that implements this interface and provides appropriate 
 *  functionality to carry out the required operations. 
 * Hard code two operands in a test class having a main method that 
 *  calls the implementing class. 
 */
public interface BasicCalculation {
	
	int addition(int x, int y);
	
	int subtraction(int x, int y);
	
	int multiplication(int x, int y);
	
	double division (int x, int y);
	
	
	
}
