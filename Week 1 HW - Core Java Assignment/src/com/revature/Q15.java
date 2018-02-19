package com.revature;

/*Q15. Write a program that defines an interface having the following methods: 
 * addition, subtraction, multiplication, and division.  
 * Create a class that implements this interface and provides appropriate 
 *  functionality to carry out the required operations. 
 * Hard code two operands in a test class having a main method that 
 *  calls the implementing class. 
 */
public class Q15 implements BasicCalculation{

	int a = 6, b = 2;
	
	public void runQ15()
	{
		System.out.println("Addition: " + a + " + " + b + 
				" = " + addition(a, b));
		
		System.out.println("Subtraction: " + a + " - " + b + 
				" = " + subtraction(a, b));
		
		System.out.println("Multiplication: " + a + " * " + b + 
				" = " + multiplication(a, b));
		
		System.out.println("Division: " + a + " / " + b + 
				" = " + division(a, b));
		
	}
	
	
	@Override
	public int addition(int x, int y) {
		
		return x + y;
	}

	@Override
	public int subtraction(int x, int y) {
		
		return x - y;
	}

	@Override
	public int multiplication(int x, int y) {
		
		return x * y;
	}

	@Override
	public double division(int x, int y) {
		
		if(y == 0)
		{
			System.out.println("ERROR: Division by Zero. Returning 0");
			return 0;
		}
		else
			return (double) x / (double) y;
	}
}
