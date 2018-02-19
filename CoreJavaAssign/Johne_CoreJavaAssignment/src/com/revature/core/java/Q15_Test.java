package com.revature.core.java;

import com.revature.core.java.q15.Operation;

/**
 * 
 * @author johne
 * 
 * Q15. Write a program that defines an interface having 
 * the following methods: addition, subtraction, multiplication, and division. 
 *  
 * Create a class that implements this interface and provides 
 * appropriate functionality to carry out the required operations. 
 * Hard code two operands in a test class having a main method that calls the implementing class.
 */
public class Q15_Test implements Operation{
	
	public static void main(String[] args) {
		Q15_Test q1 = new Q15_Test(65.23);
		
		System.out.println("Addition Result: "+ q1.addition(54.46));
		System.out.printf("Addition Result: %.2f", q1.division(43.12));
	}
	
	private double num1;
	
	/**
	 * @param num1
	 * 
	 */
	public Q15_Test(double num1) {
		this.num1 = num1;
	}

	/**
	 * @return the num1
	 */
	public double getNum1() {
		return num1;
	}

	/**
	 * @param num1 the num1 to set
	 */
	public void setNum1(double num1) {
		this.num1 = num1;
	}

	@Override
	public double addition(double n) {
		return num1 + n;
	}

	@Override
	public double subtraction(double n) {
		return num1 - n;
	}

	@Override
	public double multiply(double n) {
		return num1 * n;
	}

	@Override
	public double division(double n) {
		return num1 / n;
	}

	
	
}//end of class
