package com.revature.core.java;

/**
 * 
 * @author johne
 * Q18. Write a program having a concrete subclass that inherits 
 * three abstract methods from a superclass.  
 * Provide the following three implementations in 
 * the subclass corresponding to the abstract methods in the superclass:
 * 
 
	1. 	Check for uppercase characters in a string, and return �true� or �false� depending if any are found.
	2. 	Convert all of the lower case characters to uppercase in the input string, and return the result.
	3. 	Convert the input string to integer and add 10, output the result to the console.
	Create an appropriate class having a main method to test the above setup.

 */
public class Q18_Test {

	public static void main(String[] args) {
		Q18_Class t1 = new Q18_Class("hEllO wOrlD");
		System.out.println("Check Uppercase Result: "+ t1.checkUpperCase(t1.getStr()));
		System.out.println("Lower Case Conversion Result: " + t1.convertToUpper(t1.getStr()));
		
		t1.setStr("43");
		System.out.println("Converted String to Integer and add 10 Result: " + t1.addToString(t1.getStr()));
		
	} //end of main

}//end of class
