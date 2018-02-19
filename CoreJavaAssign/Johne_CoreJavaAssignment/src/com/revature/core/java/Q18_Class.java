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

public class Q18_Class extends Q18_AbstractClass {
	
	private String str;
	
	/**
	 * @param str
	 */
	public Q18_Class(String str) {
		this.str = str;
	}

	/**
	 * @return the str
	 */
	public String getStr() {
		return str;
	}

	/**
	 * @param str the str to set
	 */
	public void setStr(String str) {
		this.str = str;
	}

	/**
	 * loop through string and check each if character in String is an uppercase
	 */
	@Override
	public boolean checkUpperCase(String st) {
		int strLength = st.length() - 1;
		int strIndex = 0;
		boolean isUpperCase = false;
		while(strIndex <= strLength) {
			if(Character.isUpperCase(str.charAt(strIndex))) {
				isUpperCase = true; 
			}
			strIndex++;
		}
		return isUpperCase;
	}
	
	/**
	 * loop through string and check if each character in string is a lowercase and convert them
	 * to uppercases, otherwise keep the character as a lowercase
	 */
	
	@Override
	public String convertToUpper(String st) {
		int strLength = st.length() - 1;
		int counter = 0;
		String result = "";
		while(counter <= strLength) {
			if(Character.isLowerCase(st.charAt(counter))) {
				result += Character.toUpperCase((st.charAt(counter)));
			} else {
				result += st.charAt(counter);
			}
			counter++;
		}
		return result;
	}

	/**
	 * Convert the input string to integer and add 10, output the result to the console.
	 * Uses try catch blocks to handle NumberFormatException in case input is not an integer. 
	 */
	@Override
	public int addToString(String st) {
		int strInt = 0;
		int sum = 0;
		
		try {
			strInt = Integer.parseInt(st.trim() );
			sum = strInt + 10;
		} catch (NumberFormatException e) {
			System.out.println("Number Format Exception Message " + e.getMessage());
		}
		
		return sum;
	}

}	//end of class
