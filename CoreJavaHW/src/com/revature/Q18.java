package com.revature;

public class Q18 extends Q18b {
	
	public void run() {
		String s1 = "hi peOple!";
		String s2 = "hey people!";		//backslashing out of " to print them
		System.out.println("hasUppercaseLetters(\""+s1+"\") = " + hasUppercaseLetters(s1));
		System.out.println("hasUppercaseLetters(\""+s2+"\") = " + hasUppercaseLetters(s2));
		System.out.println("convertToUppercase(\""+s1+"\") = " + convertToUppercase(s1));
		System.out.println("convertToUppercase(\""+s2+"\") = " + convertToUppercase(s2));
		printToIntPlusTen(s1);
		printToIntPlusTen(s2);
	}

//	1. 	Check for uppercase characters in a string, and return �true� or �false� depending if any are found.
	@Override
	public boolean hasUppercaseLetters(String s) {
		for(char c : s.toCharArray()) {	//go through each character in the String
			if(Character.isUpperCase(c)) {	//check if the character is uppercase
				return true;	//if it found an uppercase, return true
			}
		}
		return false;	//if it gets through the whole for loop, return false
	}

//	2. 	Convert all of the lower case characters to uppercase in the input string, and return the result.
	@Override
	public String convertToUppercase(String s) {
		return s.toUpperCase();	//function in String class that just caps-locks it
	}

//	3. 	Convert the input string to integer and add 10, output the result to the console.
	@Override
	public void printToIntPlusTen(String s) {
		int intValue = 0;
		for(char c : s.toCharArray()) {	//convert s to a char array, go through each char
			intValue += (int) c;	//cast c to its ASCII value (I assume this is what you'd want?) and add to the int
		}
		System.out.println("\"" + s + "\" converted to int and adding 10: " + (intValue+10));	//print the int + 10
	}
}